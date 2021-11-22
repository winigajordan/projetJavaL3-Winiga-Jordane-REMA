/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.ConsultationDto;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private TableColumn<ConsultationDto, Integer> tblcId;
    @FXML
    private TextField code;
    @FXML
    private TableView<ConsultationDto> tbvlConsultation;
    
    ObservableList<ConsultationDto> obConsultation;
    
    Service service =  new Service();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        laodTableView();
    }    

    @FXML
    private void handleShowRdvDetails(MouseEvent event) {
    }
    
    private void laodTableView()
    {
        List <ConsultationDto> consultations = service.showConsultationToPatient(
                ConnexionPageController.getCtrl().getUser().getNci()
        );
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcService.setCellValueFactory(new PropertyValueFactory<>("service"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        obConsultation = FXCollections.observableArrayList(consultations);
        tbvlConsultation.setItems(obConsultation);
    }

    @FXML
    private void handleGetId(MouseEvent event) {
        
        code.setText(String.valueOf(tbvlConsultation.getSelectionModel().getSelectedItem().getId()));
        /*
        if (tbvlConsultation.getSelectionModel().getSelectedItem() != null)
        {
            code.setT
        }*/
    }
    
}
