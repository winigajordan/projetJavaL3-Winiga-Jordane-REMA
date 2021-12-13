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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
    @FXML
    private Button btnAnnulation;
    
    private ObservableList<java.sql.Date> obDate; 
    @FXML
    private ComboBox<java.sql.Date> cboDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        laodTableViewPrestation(service.showPrestationToRp());
        btnValidation.setVisible(false);
        btnAnnulation.setVisible(false);
        loadCboDate();
    }    
    
    public void laodTableViewPrestation( List <PrestationDto> prestations){

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
                btnAnnulation.setVisible(true);
                btnValidation.setVisible(true);
            }
            else
            {
                btnAnnulation.setVisible(false);
                btnValidation.setVisible(false);
            }
            
        }
        
    }
    
    public void loadCboDate(){
        obDate = FXCollections.observableArrayList(service.returnDate());
        cboDate.setItems(obDate);
        
    }

    @FXML
    private void handleFilterByDate(ActionEvent event) {
        java.sql.Date date = cboDate.getSelectionModel().getSelectedItem();
        laodTableViewPrestation(service.showPrestationToRpByDate(date));
    }

    @FXML
    private void handleShowAllPrestations(MouseEvent event) {
        laodTableViewPrestation(service.showPrestationToRp());
    }

    @FXML
    private void handleAnnulation(MouseEvent event) {
        if (showDiaolg("Voullez-vous annuler cette prestation ?")){
            service.annulationPrestation(tblvPrestations.getSelectionModel().getSelectedItem().getId());
            laodTableViewPrestation(service.showPrestationToRp());
            btnAnnulation.setVisible(false);
            btnValidation.setVisible(false);
        }
    }

    private boolean showDiaolg(String message)
    {
        boolean confirmation = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation d'annulation");
        //alert.setHeaderText("");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            confirmation = true;
        } 
        return confirmation;
    }

    @FXML
    private void handleValidatePrestation(MouseEvent event) {
        
        if(lblResultat.getText().equals("--")){
            showAlert("Veuillez saisir le resultat de la prestation");
        }
        else{
            if(showDiaolg("Voullez vous valider et importer les résultats de cette prestation ?"))
            {
                service.validatePrestation(tblvPrestations.getSelectionModel().getSelectedItem().getId(), lblResultat.getText());
                laodTableViewPrestation(service.showPrestationToRp());
                btnValidation.setVisible(false);
                btnAnnulation.setVisible(false);
            }
        }
        
    }
    
    
    
    
}
