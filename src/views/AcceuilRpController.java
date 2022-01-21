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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilRpController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private TextField txtNciRp;
    @FXML
    private TextField txtNomRp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNomRp.setText(String.valueOf(ConnexionPageController.getCtrl().getUser().getNomComplet()));
        txtNciRp.setText(String.valueOf(ConnexionPageController.getCtrl().getUser().getNci()));
        try {
            // TODO
            loadView("v_rp_prestations");
        } catch (IOException ex) {
            Logger.getLogger(AcceuilRpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleLogout(MouseEvent event) {
         AnchorPane root;
        try
        {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
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
    
    public void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }
    
    
}
