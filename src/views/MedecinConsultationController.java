/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.RdvDto;
import entities.Consultation;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    User user = ConnexionPageController.getCtrl().getUser();
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
    private ComboBox<?> cboTypePrestation;
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
    private Button btnAllConsultation;
    @FXML
    private Button btnDelConsultation;
    

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
        loddTableView(user.getNci());
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
    }
    
  
  
    private void loddTableView(int medecinNci)
    {
        List <Consultation> consultations = service.showConsultationToMedecin(medecinNci);
        tblcNciPatient.setCellValueFactory(new PropertyValueFactory<>("patientNci"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
        obConsultation = FXCollections.observableArrayList(consultations);
        tblvConsultations.setItems(obConsultation);
    }

    @FXML
    private void loadInfos(MouseEvent event) {
        clearField();
        if(tblvConsultations.getSelectionModel().getSelectedItem()==null)
        {
            showAlert("Veuillez selectionner une consultation");
        }
        else
        {
            Consultation consultation = tblvConsultations.getSelectionModel().getSelectedItem();
            dateConsultation.setText(String.valueOf(consultation.getDate()));
            nciPatient.setText(String.valueOf(consultation.getPatientNci()));
        }    
    }
    
  
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consultation");
        alert.setContentText(message);
        alert.show();
    }
    
    private void clearField()
    {
        txtTemperature.setText("");
        txtPoids.setText("");
         txtTension.setText("");
    }
}
