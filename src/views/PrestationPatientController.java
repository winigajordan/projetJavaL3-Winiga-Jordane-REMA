/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.PrestationDto;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PrestationPatientController implements Initializable {
    
    private PrestationPatientController ctrl;
   

    @FXML
    private TableColumn<PrestationDto, Date> tblcDate;
    @FXML
    private TableColumn<PrestationDto, String> tblcPrestation;
    @FXML
    private TableColumn<PrestationDto, String> tblcStatut;
    @FXML
    private TableColumn<PrestationDto, String> tblcResultat;
    ObservableList<PrestationDto> obRdv;
    
    Service service = new Service();
    @FXML
    private TableView<PrestationDto> tblvPrestation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(service.showPrestationToPatient(778179211));
        // TODO
        laodTableView();
    }    
    
    public void laodTableView()
    {
        List<PrestationDto> prestations = service.showPrestationToPatient(ConnexionPageController.getCtrl().getUser().getNci());
        //System.out.println(service.showPrestationToPatient(ConnexionPageController.getCtrl().getUser().getNci()));
        
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcPrestation.setCellValueFactory(new PropertyValueFactory<>("prestation"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
       tblcResultat.setCellValueFactory(new PropertyValueFactory<>("resultat"));
       obRdv = FXCollections.observableArrayList(prestations);
       tblvPrestation.setItems(obRdv);
    }
}
