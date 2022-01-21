/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilPatientController implements Initializable {

    @FXML
    private Text userName;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Button btnRdv;
    @FXML
    private Button btnConsultation;
    @FXML
    private Button btnPrestation;

    ConsultationPatientController ctrl = new ConsultationPatientController();
    @FXML
    private Text txtDeconnexion;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            loadView( "v_rdv");
            //btnRdv.setStyle("-fx-background-color :  #5DCD93");
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AcceuilPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        userName.setText(String.valueOf(ConnexionPageController.getCtrl().getUser().getNomComplet()));
    }    


    @FXML
    private void handleShowRdvView(MouseEvent event) throws IOException {
        loadView( "v_rdv");
    }

    @FXML
    private void handleShowMyConsultations(MouseEvent event) throws IOException {
        loadView( "v_consultation_patient");
    }

    @FXML
    private void handleShowMyPrestation(MouseEvent event) throws IOException {
        loadView( "v_prestation_patient");
    }

    public void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }

    @FXML
    private void logout(MouseEvent event) {
        AnchorPane root;
        try
        {
            Stage stage = (Stage) txtDeconnexion.getScene().getWindow();
            ctrl.setNullController();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_main.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 =  new Stage();
            stage1.setScene(scene);
            stage1.show();
                    
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
}
