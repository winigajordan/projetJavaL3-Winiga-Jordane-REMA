/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.MedicamentDto;
import dto.RdvDto;
import entities.Constantes;
import entities.Consultation;
import entities.Medicament;
import entities.OrdMed;
import entities.Ordonnance;
import entities.Prestation;
import entities.Rdv;
import entities.Specialite;
import entities.TypePrestation;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextInputDialog;
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
    private static MedecinConsultationController ctrl;
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
    private ComboBox<Medicament> cboMedicaments;
    @FXML
    private TableView<MedicamentDto> tblvMedicaments;
    @FXML
    private TableColumn<MedicamentDto, String> tblcMedCode;
    @FXML
    private TableColumn<MedicamentDto, String> tblcMedNom;
    @FXML
    private TableColumn<MedicamentDto, String> tblcMedPosologie;
    ObservableList <Medicament> obMed;
    ObservableList<MedicamentDto> obMedDto;
    List <MedicamentDto> listMed = new ArrayList() ;
    
    
    
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
        btnValidation.setVisible(false);
        
        //Chargement des medicaments
        loadCboMed();
        
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
        btnValidation.setVisible(true);
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
            else if (c.getStatut().equals("Fait")){
                btnDelConsultation.setVisible(false);
                btnValidation.setVisible(false);
            }
            else
            {
            btnDelConsultation.setVisible(true);
            }
        }    
    }
    
  
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consultation");
        alert.setHeaderText("");
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
       //System.out.println(listMed);
    }

    @FXML
    private void handleVoidConsultation(MouseEvent event) {        
        btnDelConsultation.setVisible(true);
        Consultation c = tblvConsultations.getSelectionModel().getSelectedItem();
        if (c.getStatut().equals("Annule"))
        {
            showAlert(String.valueOf("Consultation annulée"));
        }  
        else
        {
            if (showDiaolg()){
                service.deleteConsultation(c.getId());
                showAlert(String.valueOf("Consultation annulée"));
                loddTableView(service.showConsultationToMedecin(user.getNci()));             
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

    @FXML
    private void handleMakeConsultation(MouseEvent event) {
        
        Consultation c = tblvConsultations.getSelectionModel().getSelectedItem();
        String temperature = txtTemperature.getText();
        String poids = txtPoids.getText();
        String tension = txtTension.getText();
        String dateNewPrestation = datePrestation.getText();
        //validation des constantes
         if(temperature.isEmpty() || poids.isEmpty() || tension.isEmpty())      
        {
            showAlert("Tous les champs sont obligatoires");
        }
         else
         {
             try{
                int intTemperature = Integer.parseInt(temperature); 
                int intPoids = Integer.parseInt(poids);
                int intTension = Integer.parseInt(tension);
                
                //verification si les bouttons sont visible (si le boutton d'ordonnace est 
                //visible alors on prescrit une prestation) et inversement
                if(btnOrdonnance.isVisible())
                 {
                     TypePrestation type = cboTypePrestation.getSelectionModel().getSelectedItem();
                     //verification de la date et le type ont été choisis;
                     if (dateNewPrestation.isEmpty() || type == null)
                     {
                         showAlert("Veuillez renseigner tous les champs du formulaire de prescription");
                     }
                     else{
                         //on entre dans le esle quand les champs sont renseignés
                         //verifier si la date est au bon format
                         try{
                             //Tout les champs sont valide
                            // 1 - Insertion des constantes prises 
                            Constantes constante = new Constantes(
                                intTemperature, intPoids,intTension, c.getId()
                            );
                            int idConstanteGenere = service.insertConstantes(constante);
                            //showAlert("Id constante : " + String.valueOf(idConstanteGenere));


                            // 2- creation de rdv pour pour la prestation
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateX = new Date(sdf.parse(dateNewPrestation).getTime());
                             Rdv rdv = new Rdv(
                                dateX,c.getPatientNci(),"En Cours",0,type.getId()
                                );
                                int idRdvDemande = service.askRdv(rdv);
                                //showAlert("Demande de Rdv envoyé");

                            //3 - mise à jour du statut de la consultation et insertion de l'id des constantes
                            service.saveConsultation(c.getId(), idRdvDemande, idConstanteGenere );

                            // 4 - mise à jour de la tblv
                            loddTableView(service.showConsultationToMedecin(user.getNci()));
                            btnValidation.setVisible(false);



                         }
                         catch (Exception ex)
                         {
                            showAlert("Format de date invalide");
                         }

                     }
                 }
                 else
                 {
                     //Prescription d'ordonnance
                     //verifier si la liste des médicaments est vide
                     if (listMed.size()==0){
                        showAlert("Veuillez prescrire un medicament");
                     }
                     else{
                         Ordonnance ord = new Ordonnance(c.getId());
                         //creation de l'ordonnance
                         int idOrdGenere = service.createOrdonance(ord);
                         showAlert(String.valueOf(idOrdGenere));
                         for (MedicamentDto med : listMed)
                        {
                            //creation de  la table intermediaire
                            OrdMed ordmed = new OrdMed(med.getId(), med.getPosologie(),idOrdGenere );
                            int idOrdMedGenere = service.insertOrdMedList(ordmed);
                            
                        }
                         
                     }
                     
                     //showAlert("prescription d'ordonnance");
                     
                 }
 
             } 
             catch (Exception ex)
             {
                 showAlert("Valeurs de constantes incorrectes");
             }
            
         }

    }
    
    
    //chargement de la liste des medicaments
    private void loadCboMed()
    {
      List<Medicament> medicaments = service.findAllMedocs();
      obMed = FXCollections.observableArrayList(medicaments);
      cboMedicaments.setItems(obMed);
    }
    
    //fonction de dialogue pour demander au medecin de saisir la posologie
    
    private String dialogue()
    {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Prescription de medicament");
        dialog.setHeaderText("Veuillez saisir la pososgie:");
        dialog.setContentText("Posologie : ");
        String result = dialog.showAndWait().get();
        return result;
    }
    
    private MedicamentDto addPosologie (Medicament med){ 
        String posologie = dialogue();
        while(posologie.isEmpty())
        {
            //showAlert("Veuillez saisir la posologie du medicament choisi");
            posologie = dialogue();
        }
        MedicamentDto dto = new MedicamentDto(med.getId(), med.getCode(), med.getNom(), posologie);
        return dto;
    }
    
    
    
    private void loadTableViewMed(List <MedicamentDto> med)
    {
        
        //consultations = service.showConsultationToMedecin(user.getNci());
        tblcMedCode.setCellValueFactory(new PropertyValueFactory<>("code"));
       tblcMedNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       tblcMedPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        obMedDto = FXCollections.observableArrayList(med);
        tblvMedicaments.setItems(obMedDto);
    }
    

    @FXML
    private void handleAddMedoc(MouseEvent event) {
        Medicament med = cboMedicaments.getSelectionModel().getSelectedItem();
        if(med==null){
            showAlert("Veuillez choisir un médicament à prescrir");
        }
        else
        {
            MedicamentDto medDto = addPosologie(med);
            listMed.add(medDto);
            loadTableViewMed(listMed);   
        }
            
    }

}