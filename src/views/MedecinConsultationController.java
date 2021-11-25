/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MedecinConsultationController implements Initializable {

    @FXML
    private TextField dateConsultation;
    @FXML
    private TextField nciPatient;
    @FXML
    private TextField tktTemperature;
    @FXML
    private TextField txtPoids;
    @FXML
    private TextField txtTension;
    @FXML
    private Button btnOrdonnance;
    @FXML
    private Button btnPrestation;
    @FXML
    private TextField datePrestation;
    @FXML
    private ComboBox<?> cboTypePrestation;
    @FXML
    private ComboBox<?> cboMedicaments;
    @FXML
    private TableView<?> tblvMedicaments;
    @FXML
    private TableColumn<?, ?> tblcMedCode;
    @FXML
    private TableColumn<?, ?> tblcMedNom;
    @FXML
    private TableColumn<?, ?> tblcMedPosologie;
    @FXML
    private Button btnAddProduct;
    @FXML
    private ComboBox<?> cboListDate;
    @FXML
    private Text lblTexteDate;
    @FXML
    private Text lblType;
    @FXML
    private Button btnOrdonnance1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnOrdonnance.setVisible(false);
        lblTexteDate.setVisible(false);
        datePrestation.setVisible(false);
        lblType.setVisible(false);
        
        //
        btnPrestation.setVisible(true);
        cboMedicaments.setVisible(true);
        btnAddProduct.setVisible(true);
        tblvMedicaments.setVisible(true);
        
    }    

   

    @FXML
    private void handleOrdonnance(MouseEvent event) {
      btnOrdonnance.setVisible(false);
        lblTexteDate.setVisible(false);
        datePrestation.setVisible(false);
        lblType.setVisible(false);
        
        //
        btnPrestation.setVisible(true);
        cboMedicaments.setVisible(true);
        btnAddProduct.setVisible(true);
        tblvMedicaments.setVisible(true);
    }

    @FXML
    private void handlePrestation(MouseEvent event) {
        btnOrdonnance.setVisible(true);
        lblTexteDate.setVisible(true);
        datePrestation.setVisible(true);
        lblType.setVisible(true);
        
        //
        btnPrestation.setVisible(false);
        cboMedicaments.setVisible(false);
        btnAddProduct.setVisible(false);
        tblvMedicaments.setVisible(false);
    }

    
}
