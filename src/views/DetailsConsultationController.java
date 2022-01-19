/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.ConsultationDto;
import dto.OrdonnanceDto;
import entities.Constantes;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    ConsultationDto consultation = DossierMedicalController.getCtrl().getConsultation();
    Service service = new Service();
    Constantes c = service.getConstante(consultation.getId());
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
        laodFielConstantes();
        laodOrdonnance();
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

    @FXML
    private void handleShowPrestation(MouseEvent event) {
        
    }
    
}

/*

textOrdonnance.setText("Code : " + String.valueOf(ord.getCode()) + '\t' +
                                   "Nom : " + String.valueOf(ord.getNom()) + '\t' + 
                                   "Posologie : " + String.valueOf(ord.getPosologie()) + '\n' );

*/