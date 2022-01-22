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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;



/**
 * FXML Controller class
 *
 * @author user
 */
public class ConnexionPageController implements Initializable {

    @FXML
    private AnchorPane anchorConnexionContent;
    @FXML
    private TextField txtfLogin;
    @FXML
    private TextField txtfPassword;
    @FXML
    private Text txtError;
    
    private User user;
    private final Service service = new Service();
    
    private static ConnexionPageController ctrl;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl = this;
        // TODO
    }    

    
    
    @FXML
    private void handleConnexion(MouseEvent event) throws IOException {
        
        String login = txtfLogin.getText().trim();
        String password = txtfPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
            txtError.setText("Login et mot de passe obligatoire");
            txtError.setVisible(true);
        }
        else 
        {
            user = service.login(login, password);
            if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
            else
            {
                //Connexion établie
                
                if (user.getRole().equals("ROLE_PATIENT"))
                {
                    laodPage("v_acceuil_patient");
                }
                 if (user.getRole().equals("ROLE_MEDECIN"))
                {
                    laodPage("v_medecin_acceuil");
                }
                if (user.getRole().equals("ROLE_SECRETAIRE"))
                {
                    laodPage("v_acceuil_secretaire");
                }
                if (user.getRole().equals("ROLE_RP"))
                {
                    laodPage("v_acceuil_rp");
                }
                if (user.getRole().equals("ADMIN"))
                {
                    laodPage("v_admin");
                }
            }
        }
    }

    public User getUser() {
        return user;
    }

    public static ConnexionPageController getCtrl() {
        return ctrl;
    }
    
    public void laodPage(String page){
        this.txtError.getScene().getWindow().hide();
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
 
}
