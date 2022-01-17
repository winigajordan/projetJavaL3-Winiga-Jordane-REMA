/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Admin;
import entities.Medecin;
import entities.Rp;
import entities.Secretaire;
import entities.Specialite;
import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> cboRole;
    ObservableList <String> obRole;
    @FXML
    private ComboBox<Specialite> cboSpecialite;
    ObservableList <Specialite> obSpecialite;

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
        laodCboRole();
        cboSpecialite.setVisible(false);
        loadCboSpecialites();
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
            cboRole.setValue(user.getRole());
            cboRole.setEditable(false);
            cboSpecialite.setVisible(false); 
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
        btnAdd.setVisible(true);
        btnDel.setVisible(false);
        lblNci.clear();
        lblNomComplet.clear();
        lblLogin.clear();
        lblPassword.clear();
                
    }
    
    public void laodCboRole(){
        List<String> roles = new ArrayList();
        roles.add("ROLE_MEDECIN");
        roles.add("ROLE_SECRETAIRE");
        roles.add("ROLE_RP");
        roles.add("ADMIN");
        obRole = FXCollections.observableArrayList(roles);
        cboRole.setItems(obRole);
        
    }
    
    
    public void loadCboSpecialites()
    {
        List <Specialite> spe = service.showAllSpecialisation();
        obSpecialite = FXCollections.observableArrayList(spe);
        cboSpecialite.setItems(obSpecialite);
        
    }

    @FXML
    private void handleGetRole(ActionEvent event) {
        String role = cboRole.getSelectionModel().getSelectedItem();
        //showAlert(role);
        if (role.equals("ROLE_MEDECIN")){
            cboSpecialite.setVisible(true);
        }
        else
        {
            cboSpecialite.setVisible(false);
        } 
    }

    @FXML
    public void dandleAddAccount(MouseEvent event) {
        String nci = lblNci.getText();
        String nomComplet = lblNomComplet.getText();
        String role = cboRole.getSelectionModel().getSelectedItem();
        Specialite spe = cboSpecialite.getSelectionModel().getSelectedItem();
        String login = lblLogin.getText();
        String password = lblPassword.getText();
        
        //verifications si les libellés ont été remplis
        if(nci.isEmpty() || nomComplet.isEmpty() || login.isEmpty() || password.isEmpty()) {
            showAlert("Veuillez remplir tous les champs");
        }
        else
            //verifier si la longeur du nci == 8
            if(nci.length()==8)
            {
                //verifier si nci est un entier
                int num=0;
                
                    try
                    {
                        num = Integer.parseInt(nci);
                        
                    }
                    catch(Exception ex) {
                        
                    }
                //verifier si le nci a été changé en entier
                if (num == 0){
                    showAlert("Le NCI doit etre un entier"); 
                }
                else{
                     //verifier si le rôle a été choisis
                    if (role == null){
                    showAlert("Veuillez definir le role du nouvel utilisateur");
                    }
                    else
                    {
                        //verification des roles
                        // verifier si c'est un medecin
                        if(role.equals("ROLE_MEDECIN")){
                            //verifier si la spécialite a été choisie 
                            if(spe == null)
                            {
                                showAlert("Veuillez choisir une spécialité");
                            }
                            else
                            {
                                Medecin med = new Medecin(num ,nomComplet, login, password, spe.getId() );
                                int idGen = service.addMed(med);
                                showAlert(String.valueOf(idGen));
                                
                            }
                        }
                        
                        //ajout d'un secretaire
                        if (role.equals("ROLE_SECRETAIRE"))
                        {
                            Secretaire sc = new Secretaire(num ,nomComplet, login, password);
                            int idGen = service.addSecretaire(sc);
                            showAlert(String.valueOf(idGen));
                            
                        }
                        
                        //ajout d'un rp
                        if (role.equals("ROLE_RP"))
                        {
                            Rp rp = new Rp(num ,nomComplet, login, password);
                            int idGen = service.addRp(rp);
                            showAlert(String.valueOf(idGen));
                            
                        }
                        if (role.equals("ADMIN"))
                        {
                            Admin admin = new Admin(num ,nomComplet, login, password);
                            int idGen = service.addAdmin(admin);
                            showAlert(String.valueOf(idGen));
                            
                        }
                        
                        loadTableView(service.showUsers());
                    } 
                     
                }
            }
            else {
                showAlert("Le NCI doit avoir 8 charactères");
            }
    }

    @FXML
    private void handleDeleteUser(MouseEvent event) {
        User user = tblvComptes.getSelectionModel().getSelectedItem();
        if (user == null){
            showAlert("Veuillez selectionner un utilisateur");
        }
        else
        {
            showAlert(String.valueOf(user.getId()));
        }
        
    }

}

