/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.Text;
//import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class V_connexionController implements Initializable {

    //private Text txtError;
    
    //private User user;
    //private final Service service = new Service();
    @FXML
    private AnchorPane anchorConnexionContent;
    @FXML
    private Button buttonCreation;
    @FXML
    private Button buttonConnexion;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonConnexion.setVisible(false);
        try {
            loadView("v_ConnexionPage");
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(V_connexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorConnexionContent.getChildren().clear();
        anchorConnexionContent.getChildren().add(root);
    }

    @FXML
    private void showAccountCreationPage(MouseEvent event) throws IOException {
        buttonCreation.setVisible(false);
        buttonConnexion.setVisible(true);
        loadView("v_accountCreation");
  
    }

    @FXML
    private void showConnexionPage(MouseEvent event) throws IOException {
        buttonCreation.setVisible(true);
        buttonConnexion.setVisible(false);
        loadView("v_ConnexionPage");
    }
    
    
    
    
}
