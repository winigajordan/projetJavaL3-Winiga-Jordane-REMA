/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.RdvDto;
import entities.Consultation;
import entities.Prestation;
import entities.Specialite;
import entities.TypePrestation;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MedecinConsultationController implements Initializable {

    Service service = new Service();
    //InfoConsultation
   
    @FXML
    private TextField dateConsultation;
    @FXML
    private TextField nciPatient;
    
    
    //constantes
    @FXML
    private TextField txtTemperature;
    @FXML
    private TextField txtPoids;
    @FXML
    private TextField txtTension;
    
    //prestation
    @FXML
    private TextField datePrestation;
    @FXML
    private ComboBox<TypePrestation> cboTypePrestation;
    @FXML
    private Text lblTexteDate;
    @FXML
    private Text lblType;
    
    
    //Ordonnance
    @FXML
    private ComboBox<?> cboMedicaments;
    @FXML
    private TableView<?> tblvMedicaments;
    @FXML
    private TableColumn<?, ?> tblcMedCode;
    @FXML
    private TableColumn<?, ?> tblcMedNom;
    @FXML
    private TableColumn<?, ?> tblcMedPosologie;
    
    
    //Lister les consultations
    @FXML
    private ComboBox<Date> cboListDate; 
    @FXML
    private TableColumn<Consultation, Integer> tblcNciPatient;
    @FXML
    private TableColumn<Consultation, java.sql.Date> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcEtat;
    @FXML
    private TableView<Consultation> tblvConsultations;
    ObservableList <Consultation> obConsultation;
    
    
    //Button
    @FXML
    private Button btnOrdonnance;
    @FXML
    private Button btnPrestation;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnValidation;
    @FXML
    private Button btnDelConsultation;
    

    //autres 
    private ObservableList<java.sql.Date> obDate; 
    private ObservableList<TypePrestation> obPrestation;
    User user = ConnexionPageController.getCtrl().getUser();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnOrdonnance.setVisible(false);
        lblTexteDate.setVisible(false);
        datePrestation.setVisible(false);
        cboTypePrestation.setVisible(false);
        lblType.setVisible(false);
        
        //
        btnPrestation.setVisible(true);
        cboMedicaments.setVisible(true);
        btnAddProduct.setVisible(true);
        tblvMedicaments.setVisible(true);
        
        //Chargement de la liste des consultation du medecin
        List <Consultation> consultations = service.showConsultationToMedecin(user.getNci());
        loddTableView(consultations);
        
        //chargement des dates des consultations dans le cbo
        loadComboBoxDate(user.getNci());
    }    

   

    @FXML
    private void handleOrdonnance(MouseEvent event) {
      btnOrdonnance.setVisible(false);
        lblTexteDate.setVisible(false);
        datePrestation.setVisible(false);
        cboTypePrestation.setVisible(false);
        lblType.setVisible(false);
        
        //
        btnPrestation.setVisible(true);
        cboMedicaments.setVisible(true);
        btnAddProduct.setVisible(true);
        tblvMedicaments.setVisible(true);
    }

    @FXML
    private void handlePrestation(MouseEvent event) {
        btnOrdonnance.setVisible(true);
        lblTexteDate.setVisible(true);
        datePrestation.setVisible(true);
        cboTypePrestation.setVisible(true);
        lblType.setVisible(true);
        
        //
        btnPrestation.setVisible(false);
        cboMedicaments.setVisible(false);
        btnAddProduct.setVisible(false);
        tblvMedicaments.setVisible(false);
        loadComboBoxPrestation();
    }
    
  
  
    private void loddTableView(List <Consultation> consultations)
    {
        
        //consultations = service.showConsultationToMedecin(user.getNci());
        tblcNciPatient.setCellValueFactory(new PropertyValueFactory<>("patientNci"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
        obConsultation = FXCollections.observableArrayList(consultations);
        tblvConsultations.setItems(obConsultation);
    }

    @FXML
    private void loadInfos(MouseEvent event) {
        Consultation c = tblvConsultations.getSelectionModel().getSelectedItem();
        if(c==null)
        {
            showAlert("Veuillez selectionner une consultation");
        }
        else
        {
            dateConsultation.setText(String.valueOf(c.getDate()));
            nciPatient.setText(String.valueOf(c.getPatientNci()));
            if (c.getStatut().equals("Annule"))
            {
                btnValidation.setVisible(false);
                
            }
            else if (c.getStatut().equals("En cours")){
                btnValidation.setVisible(true);
            }
            
            
        }    
    }
    
  
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consultation");
        alert.setContentText(message);
        alert.show();
    }
    
   
    @FXML
    private void handleFilterByDate(ActionEvent event) {
        //clearField();
        //System.out.println(cboListDate.getValue());
        List <Consultation> consultations = service.showConsultationToMedecin(user.getNci());
        List <Consultation> consul = new ArrayList();
        for (Consultation c : consultations)
        {
            if(cboListDate.getValue().equals(c.getDate()))
            {
                consul.add(c);
                //System.out.println(c.getDate());
            }
        }
        loddTableView(consul);
    }
    
    
    private void loadComboBoxDate(int medecinNci)
    {
      List<Date> listeDate = new ArrayList();
      List<Consultation> consultations = service.showConsultationToMedecin(medecinNci);
      for (Consultation c:consultations)
      {
            if (!listeDate.contains(c.getDate()))
            {
                listeDate.add(c.getDate());
            }
            
      }
      obDate = FXCollections.observableArrayList(listeDate);
      cboListDate.setItems(obDate);
    }
    
    
    private void loadComboBoxPrestation()
    {
      obPrestation = FXCollections.observableArrayList(service.showAllType());
       cboTypePrestation.setItems(obPrestation);
    }

    @FXML
    private void handleResetTblvConsultation(MouseEvent event) {
        
       loddTableView(service.showConsultationToMedecin(user.getNci()));
      
    }

    @FXML
    private void handleVoidConsultation(MouseEvent event) {
        //System.out.println(); 
        btnDelConsultation.setVisible(true);
        Consultation c = tblvConsultations.getSelectionModel().getSelectedItem();
        if (c.getStatut().equals("Annule"))
        {
            showAlert(String.valueOf("Consultation annulée"));
        }
        else if (c.getStatut().equals("Fait")){
            btnDelConsultation.setVisible(false);
        }
        else
        {
            if (showDiaolg()){
                service.deleteConsultation(c.getId());
                showAlert(String.valueOf("Consultation annulée"));
                loddTableView(service.showConsultationToMedecin(user.getNci()));
              //showAlert("");
            }
        }
        
    }
    
    private boolean showDiaolg()
    {
        boolean confirmation = false;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation d'annulation");
        //alert.setHeaderText("");
        alert.setContentText("Voullez-vous annuler cette consultation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            confirmation = true;
        } 
        return confirmation;
    }
    
    
    
}
