/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class V_mainController implements Initializable {

    @FXML
    private Button btnCreation;
    @FXML
    private Button btnConnexion;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private AnchorPane mainAnchorContent;
   
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnConnexion.setVisible(false);
        try {
            loadView( "v_ConnexionPage");
            
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(V_connexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }

    @FXML
    private void handleConnexionPage(MouseEvent event) throws IOException {
        btnCreation.setVisible(true);
        btnConnexion.setVisible(false);
        loadView("v_ConnexionPage");
    }

    @FXML
    private void handleAccountCreationPage(MouseEvent event) throws IOException {
        System.out.println("OOKK");
        btnCreation.setVisible(false);
        btnConnexion.setVisible(true);
        loadView( "v_accountCreation");
        
    }

    
}
