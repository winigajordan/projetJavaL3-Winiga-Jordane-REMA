/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.ConsultationDto;
import dto.PrestationDto;
import entities.Patient;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DossierMedicalController implements Initializable {

    Service service = new Service ();
    private static DossierMedicalController ctrl;
    private ConsultationDto consultation;

    public ConsultationDto getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationDto consultation) {
        this.consultation = consultation;
    }
    
    @FXML
    private TableView<Patient> tblvUser;
    @FXML
    private TableColumn<Patient, Integer> tblcNci;
    @FXML
    private TableColumn<Patient, String> tblcUserName;
    @FXML
    private TableColumn<Patient, String> tblcAnt;
    ObservableList <Patient> obPatient;
    @FXML
    private TableView<PrestationDto> tblvPrestation;
    @FXML
    private TableColumn<PrestationDto, java.sql.Date> tblcPrestationDate;
    @FXML
    private TableColumn<PrestationDto, String> tblcPrestationEtat;
    @FXML
    private TableColumn<PrestationDto, String> tblcPrestationLibelle;
    ObservableList <PrestationDto> obPrestation;
    @FXML
    private Button btnDetailPrestation;
    @FXML
    private TableView<ConsultationDto> tblvConsultation;
    @FXML
    private TableColumn<ConsultationDto, java.sql.Date> tblcConsultationDate;
    @FXML
    private TableColumn<ConsultationDto, String> tblcConsultationEtat;
    @FXML
    private TableColumn<ConsultationDto, String> tblcConsultationLibelle;
    @FXML
    private Button btnDetailConsultation;
    ObservableList obConsultation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ctrl = this;
        laodTblvPatient();
        btnDetailPrestation.setVisible(false);
        btnDetailConsultation.setVisible(false);
    }

     @FXML
    private void handleLaodMedicalFile(MouseEvent event) {
        laodTblvPrestation();
        laodTblvConsultation();
    }
    
    public void showAlert( String message, String x){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dossier médical");
        alert.setHeaderText(x);
        alert.setContentText(message);
        alert.show();
    }
    
    public void laodTblvPatient()
    {
        List <Patient> patients = service.findAllPatiens();
       tblcNci.setCellValueFactory(new PropertyValueFactory<>("nci"));
       tblcUserName.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
       tblcAnt.setCellValueFactory(new PropertyValueFactory<>("antecedents"));
       obPatient = FXCollections.observableArrayList(patients);
       tblvUser.setItems(obPatient);
    }
    
    public void laodTblvPrestation(){
        Patient patient = tblvUser.getSelectionModel().getSelectedItem();
        if (patient == null){
            showAlert("Veuillez selectionner un patient pour afficher son dossier médical", "Info");
        }
        else
        {
            List  <PrestationDto> prestations = service.showPrestationToPatient(patient.getNci());
            tblcPrestationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tblcPrestationEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
            tblcPrestationLibelle.setCellValueFactory(new PropertyValueFactory<>("prestation"));
            obPrestation = FXCollections.observableArrayList(prestations);
            tblvPrestation.setItems(obPrestation);
        }
    }

    @FXML
    private void handleShowPrestationDetails(MouseEvent event) {
        PrestationDto prestation = tblvPrestation.getSelectionModel().getSelectedItem();
        if(prestation == null){
            showAlert("Veuillez sélectionner une prestation puis cliquez sur Resultat pour afficher les resultats", "Info");
        }
        else{
            if (prestation.getStatut().equals("Faite")){
                btnDetailPrestation.setVisible(true);
            }else{
                btnDetailPrestation.setVisible(false);
            }
        }
        
    }

    @FXML
    private void handleShowPrestationResult(MouseEvent event) {
        PrestationDto prestation = tblvPrestation.getSelectionModel().getSelectedItem();
        showAlert(prestation.getResultat(), "Resultat de la prestation");
    }

   public void laodTblvConsultation(){
       Patient patient = tblvUser.getSelectionModel().getSelectedItem();
        if (patient == null){
            showAlert("Veuillez selectionner un patient pour afficher son dossier médical", "Info");
        }
        else
        {
            List <ConsultationDto> consultations = service.showConsultationToPatient(patient.getNci());
            tblcConsultationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tblcConsultationEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            tblcConsultationLibelle.setCellValueFactory(new PropertyValueFactory<>("service"));
            obConsultation = FXCollections.observableArrayList(consultations);
            tblvConsultation.setItems(obConsultation);
            
        }
   
   }

   
   
   
   
    @FXML
    private void handleSetVisibleBtnDetails(MouseEvent event) {
        consultation = tblvConsultation.getSelectionModel().getSelectedItem();
        if(consultation == null){
            showAlert("Veuillez sélectionner une consultation puis cliquez sur Resultat pour afficher les resultats", "Info");
        }
        else{
            if (consultation.getEtat().equals("Fait")){
                btnDetailConsultation.setVisible(true);
            }else{
                btnDetailConsultation.setVisible(false);
            }
        }
    }

    @FXML
    private void handleShowConsultationDetails(MouseEvent event) {
        laodPage("v_details_consultation");
    }

    public void laodPage(String page){
        
         AnchorPane root = null;
              
              try {
                  root = FXMLLoader.load(getClass().getResource("/views/"+page+".fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(ConnexionPageController.class.getName()).log(Level.SEVERE, null, ex);
              }      
    }
   
   
    public static DossierMedicalController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(DossierMedicalController ctrl) {
        DossierMedicalController.ctrl = ctrl;
    }

   
   
}
