/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.ConsultationDto;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class V_consultation_patientController implements Initializable {

    @FXML
    private TableColumn<ConsultationDto, Date> tblcDate;
    @FXML
    private TableColumn<ConsultationDto, String> tblcService;
    @FXML
    private TableColumn<ConsultationDto, String> tblcEtat;
    //private TableColumn<ConsultationDto, Integer> tblcId;
    
    @FXML
    private TableView<ConsultationDto> tbvlConsultation;
    
    ObservableList<ConsultationDto> obConsultation;
    
    Service service =  new Service();
    private ConsultationDto consultation = null;
    private static V_consultation_patientController ctrl = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        laodTableView();
        ctrl = this;
    }    

    public void showAlert(String message, String x){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dossier médical");
        alert.setHeaderText(x);
        alert.setContentText(message);
        alert.show();
    }
    
    private void laodTableView()
    {
        List <ConsultationDto> consultations = service.showConsultationToPatient(
                ConnexionPageController.getCtrl().getUser().getNci()
        );
        //tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcService.setCellValueFactory(new PropertyValueFactory<>("service"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        obConsultation = FXCollections.observableArrayList(consultations);
        tbvlConsultation.setItems(obConsultation);
    }

    @FXML
    private void handleGetId(MouseEvent event) {
        
       
        
    }

    @FXML
    private void handleShowConsultationDetails(MouseEvent event) {
         consultation = tbvlConsultation.getSelectionModel().getSelectedItem();
        if (consultation == null) {
            showAlert("Veuillez selectionner une consultatation pour afficher les details", "Consultations");
        }
        else
        {
            laodPage("v_details_consultation");
        }
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

    public ConsultationDto getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationDto consultation) {
        this.consultation = consultation;
    }

    public static V_consultation_patientController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(V_consultation_patientController ctrl) {
        V_consultation_patientController.ctrl = ctrl;
    }
    
    public void setNullController(){
        ctrl = null;
    }
    
    
}


