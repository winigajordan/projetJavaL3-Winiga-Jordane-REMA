/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.ConsultationDto;
import dto.OrdonnanceDto;
import dto.PrestationDto;
import entities.Constantes;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DetailsConsultationController implements Initializable {

    
    
    @FXML
    private TextField txtTemperature;
    @FXML
    private TextField txtPoids;
    @FXML
    private TextField txtTension;
    
    
    //ConsultationDto patientConsultation = ConsultationPatientController.getCtrl().getConsultation();
    //ConsultationDto consultationDossier = DossierMedicalController.getCtrl().getConsultation();
    //DossierMedicalController x = DossierMedicalController.getCtrl();
    ConsultationDto consultation = null;
    Service service = new Service();
    Constantes c =  null;
    @FXML
    private TableView<OrdonnanceDto> tblvOrdonnance;
    @FXML
    private TableColumn<OrdonnanceDto, String> tblcCode;
    @FXML
    private TableColumn<OrdonnanceDto, String> tblcNom;
    @FXML
    private TableColumn<OrdonnanceDto, String> tblcPosologie;
    ObservableList obOrdonnance;
    @FXML
    private Button btnPrestation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (ConsultationPatientController.getCtrl() != null){
            consultation = ConsultationPatientController.getCtrl().getConsultation();
        }
        if (DossierMedicalController.getCtrl() != null){
            consultation = DossierMedicalController.getCtrl().getConsultation();
            
        }
        
        c = service.getConstante(consultation.getId());
        //V_consultation_patientController a = ConsultationPatientController.getCtrl();
        
        
        laodFielConstantes();
        laodOrdonnance();
        System.out.println(consultation.getId());
    }    
    
    
    public void laodFielConstantes(){
        txtTemperature.setText(String.valueOf(c.getTemperature()));
        txtPoids.setText(String.valueOf(c.getPoids()));
        txtTension.setText(String.valueOf(c.getTension()));
    }
    
    public void laodOrdonnance(){
       List <OrdonnanceDto> consultations = service.getOrdonnance(consultation.getId());
            tblcCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            tblcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
            obOrdonnance = FXCollections.observableArrayList(consultations);
            tblvOrdonnance.setItems(obOrdonnance);
    }

    public void showAlert( String message, String x){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dossier médical");
        alert.setHeaderText(x);
        alert.setContentText(message);
        alert.show();
    }
    
    @FXML
    private void handleShowPrestation(MouseEvent event) {
        String message = "";
        
        if (consultation.getPrestationId() == 0){
            message = "Aucune prestation prescrite lors de cette consultation";
        }
        
        else
        {
            PrestationDto prestation = service.getPrestationByConsultation(consultation.getPrestationId());
            if (prestation == null){
                message = "Rdv pour pas encore validé";
            } else {
                message = "Date : " + String.valueOf(prestation.getDate()) + '\n'
                       + "Type de prestation : " + prestation.getPrestation() + '\n'
                       + "Statut : " + prestation.getStatut() + '\n'
                       + "Resultat : " + prestation.getResultat()
                       ;
            }
            
        }
       
        showAlert(message, "Detail prestation prescrite");
        
    }
    
}
