/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.PrestationDto;
import entities.Patient;
import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DossierMedicalController implements Initializable {

    Service service = new Service ();
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        laodTblvPatient();
        btnDetailPrestation.setVisible(false);
    }

     @FXML
    private void handleLaodMedicalFile(MouseEvent event) {
        laodTblvPrestation();
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

   
    
}
