/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.PrestationDto;
import dto.RdvDto;
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
public class ResponsablePrestationController implements Initializable {

    Service service = new Service();
    
    
    @FXML
    private TableView<PrestationDto> tblvPrestations;
    @FXML
    private TableColumn<PrestationDto, Integer> tblcNciPatient;
    @FXML
    private TableColumn<PrestationDto, java.sql.Date> tblcDate;
    @FXML
    private TableColumn<PrestationDto, String> tblcType;
    @FXML
    private TableColumn<PrestationDto, String> tblcEtat;
    ObservableList <PrestationDto> obPrestation;
    @FXML
    private TextField lblNciPatient;
    @FXML
    private TextField lblType;
    @FXML
    private TextField lblDate;
    @FXML
    private TextArea lblResultat;
    @FXML
    private Button btnValidation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        laodTableViewPrestation();
        btnValidation.setVisible(false);
    }    
    
    public void laodTableViewPrestation(){
        List <PrestationDto> prestations = service.showPrestationToRp();
       
       tblcNciPatient.setCellValueFactory(new PropertyValueFactory<>("patientNci"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcType.setCellValueFactory(new PropertyValueFactory<>("prestation"));
       tblcEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
       obPrestation = FXCollections.observableArrayList(prestations);
       tblvPrestations.setItems(obPrestation);
    }
    
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prestation");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void loadInformations(MouseEvent event) {
        PrestationDto p = tblvPrestations.getSelectionModel().getSelectedItem();
        if (p==null)
        {
            showAlert("Veuillez selectionner une prestation");
        }
        else
        {
            lblNciPatient.setText(String.valueOf(p.getPatientNci()));
            lblDate.setText(String.valueOf(p.getDate()));
            lblType.setText(p.getPrestation());
            lblResultat.setText(p.getResultat());
            
            if(p.getStatut().equals("En cours")){
                btnValidation.setVisible(true);
            }
            else
            {
                btnValidation.setVisible(false);
            }
            
        }
        
    }
    
}
