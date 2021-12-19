/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AdminController implements Initializable {

    Service service = new Service();
    
    
    @FXML
    private Text txtNom;
    @FXML
    private Text txtNci;
    @FXML
    private TableView<User> tblvComptes;
    @FXML
    private TableColumn<User, Integer> tblcNci;
    @FXML
    private TableColumn<User, String> tblcNomComplet;
    @FXML
    private TableColumn<User, String> tblcRole;
    ObservableList <User> obUser;
    @FXML
    private TextField lblNci;
    @FXML
    private TextField lblNomComplet;
    @FXML
    private TextField lblLogin;
    @FXML
    private PasswordField lblPassword;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDel;
    @FXML
    private Button bntReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNom.setText(String.valueOf(ConnexionPageController.getCtrl().getUser().getNomComplet()));
        txtNci.setText(String.valueOf(ConnexionPageController.getCtrl().getUser().getNci()));
        loadTableView(service.showUsers());
        btnDel.setVisible(false);
    }    

    @FXML
    private void handleLogout(MouseEvent event) {
        AnchorPane root;
        try
        {
            Stage stage = (Stage) txtNom.getScene().getWindow();
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
    
    public void loadTableView (List<User> user){
       tblcNci.setCellValueFactory(new PropertyValueFactory<>("nci"));
       tblcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
       tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
       obUser = FXCollections.observableArrayList(user);
       tblvComptes.setItems(obUser);
    
    }

    @FXML
    private void handleLoadUserInformations(MouseEvent event) {
        User user = tblvComptes.getSelectionModel().getSelectedItem();
        if (user == null){
            showAlert("Veuillez selectionner un compte pour afficher ses données");
        }
        else {
            btnAdd.setVisible(false);
            btnDel.setVisible(true);
            lblNci.setText(String.valueOf(user.getNci()));
            lblNomComplet.setText(user.getNomComplet());
            lblLogin.setText(user.getLogin());
            lblPassword.setText(user.getPassword());
        }
        
    }
    
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des comptes");
        //alert.setHeaderText("");
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void handleReset(MouseEvent event) {
    }
    
}
