/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
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
public class V_accountCreationController implements Initializable {

    @FXML
    private TextField txtNci;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtfPassword;
    @FXML
    private TextArea txtAntecedents;
    @FXML
    private Text txtError;

    
    private User user;
    private final Service service = new Service();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        // TODO
    }    

    @FXML
    private void handleCreateAccount(MouseEvent event) {
        String nci = txtNci.getText().trim();
        String nomComplet = txtNomComplet.getText().trim();
        String login = txtLogin.getText().trim();
        String password = txtfPassword.getText().trim();
        String ant = txtAntecedents.getText();
        
        if (nci.isEmpty() || nomComplet.isEmpty() || login.isEmpty() || password.isEmpty())
        {
            txtError.setText("Tous les champs sont obligatoire");
            txtError.setVisible(true);
        }
        else
        {
            
            int resultat = service.searchByNci(Integer.parseInt(nci));
            if (resultat != 0)
            
            {
                txtError.setText("Ce Nci existe deja");
                txtError.setVisible(true);
            }
            else {
                
                Patient patient = new Patient(Integer.parseInt(nci), nomComplet, login, password, ant);
                System.out.println("NCI patient : " + patient.getNci());
                //service.addPatient();
                int x  = service.addPatient(patient);
                txtError.setText(String.valueOf(x));
                txtError.setVisible(true);
                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prise de rendez-vous");
                alert.setContentText("Compte crée avec succès, retournez à la page de conneion pour vous connecter");
                alert.show();
            }
            
            
            
        }
        
        
    }

    
}
