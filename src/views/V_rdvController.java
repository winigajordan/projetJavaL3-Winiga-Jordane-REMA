 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dto.RdvDto;
import entities.Rdv;
import entities.Specialite;
import entities.TypePrestation;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class V_rdvController implements Initializable {

    private static V_rdvController ctrl;
    
    
    
    
    @FXML
    private TextField txtDate;
    @FXML
    private ComboBox<String> cboService;
    private ComboBox<?> cboType;
    
    private ObservableList<TypePrestation> obType;
    
    
    private ObservableList<Specialite> obs; 
    private ObservableList<TypePrestation> obp;
    private ObservableList<Specialite> obSpecialite;
    
    Service service = new Service();
    @FXML
    private Text txtType;
    @FXML
    private ComboBox<Specialite> cboConsultation;
    @FXML
    private ComboBox<TypePrestation> cboPrestation;
    @FXML
    private TableView<RdvDto> tblvRdv;
    
    ObservableList<RdvDto> obRdvTableView ;
    
    @FXML
    private TableColumn<RdvDto, Date> tblcDate;
    @FXML
    private TableColumn<RdvDto, String> tblcService;
    @FXML
    private TableColumn<RdvDto, String> tblcType;
    @FXML
    private TableColumn<RdvDto, String> tblcEtat;
    
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboService.getItems().add("Consultation");
        cboService.getItems().add("Prestation");
        cboConsultation.setVisible(false);
        cboPrestation.setVisible(false);  
        txtType.setVisible(false);
        laodTableView();
        //System.out.println(ConnexionPageController.getCtrl().getUser().getNci());
    }    

    @FXML
    private void handleAskRdv(MouseEvent event) {
        
        String date =  txtDate.getText();
        Specialite typeConsultation = cboConsultation.getValue();
        TypePrestation typePrestation = cboPrestation.getValue();
        
        if (date.isEmpty() && typeConsultation == null && typePrestation == null )
        {
            showAlert("Tous les champs sont Obligatoires");
        }
        else{
           try 
           {
               
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateX = new Date(sdf.parse(date).getTime());
             
            if (cboService.getValue().equals("Consultation"))
            {
                Rdv rdv = new Rdv(
                dateX,ConnexionPageController.getCtrl().getUser().getNci(),"En Cours",typeConsultation.getId(),0
                );
                service.askRdv(rdv);
                showAlert("Demande de Rdv envoyé");
                laodTableView();
            }
            else {
                Rdv rdv = new Rdv(
                dateX,ConnexionPageController.getCtrl().getUser().getNci(),"En Cours",0,typePrestation.getId()
                );
                service.askRdv(rdv);
                showAlert("Demande de Rdv envoyé");
                laodTableView();
                
            }

           } catch (ParseException ex) 
           
           {
            showAlert("Format de date invalide");
           }
        }
 }
    
    

    @FXML
    private void handleChoice(ActionEvent event) {
        txtType.setVisible(true); 
        if (cboService.getValue().equals("Consultation"))
        {   
            cboPrestation.setVisible(false);
            cboConsultation.setVisible(true);
            loadComboBoxConsultation(cboConsultation);
        }
        else
        {
            cboPrestation.setVisible(true);
            loadComboBoxPrestation(cboPrestation);            
        }
    }
    
    private void loadComboBoxConsultation(ComboBox<Specialite> cbo)
    {
      obs = FXCollections.observableArrayList(service.showAllSpecialisation());
       cbo.setItems(obs);
    }
    
    
    private void loadComboBoxPrestation(ComboBox<TypePrestation> cbo)
    {
      obp = FXCollections.observableArrayList(service.showAllType());
       cbo.setItems(obp);
    }
    
    public static java.sql.Date convertToSQLDate(java.util.Date javaDate) {
    java.sql.Date sqlDate = null;
    if (javaDate != null) {
        sqlDate = new Date(javaDate.getTime());
    }
    return sqlDate;
    }

    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prise de rendez-vous");
        alert.setContentText(message);
        alert.show();
    }
   
    
       private void laodTableView()
    { 
        List<RdvDto> listDto = conversionList(ConnexionPageController.getCtrl().getUser().getNci());
        //System.out.println(listDto);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcService.setCellValueFactory(new PropertyValueFactory<>("serviceDemande")); 
        tblcType.setCellValueFactory(new PropertyValueFactory<>("typeServiceDemande"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        obRdvTableView=FXCollections.observableArrayList(listDto);
        tblvRdv.setItems(obRdvTableView);
    }
       
       
       
    private List<RdvDto> conversionList(int nci_patient)
    {
        List<Rdv> rdvs = service.showMyRdv(nci_patient);
        List<RdvDto> rdvsDto = new ArrayList();
        List<TypePrestation> prestations = service.showAllType();
        List<Specialite> specialites = service.showAllSpecialisation();
        for (Rdv rdv : rdvs)
        {
            //System.out.println(rdv.getId());
            //System.out.println(rdv);
            
            if(rdv.getSpecialiteId() != 0)
            {
                for (Specialite specialite : specialites)
                {
                    if (rdv.getSpecialiteId() == specialite.getId())
                    {
                        RdvDto dto = new RdvDto (
                                rdv.getId(),
                                rdv.getDate(),
                                "Consultation",
                                specialite.getLibelle(),
                                rdv.getEtat()
                                
                        );
                        rdvsDto.add(dto);
                    }
                }
            }
            
            else
            {
               for (TypePrestation prestation : prestations)
                {
                    if (rdv.getPrestationId() == prestation.getId())
                    {
                        RdvDto dto = new RdvDto (
                                rdv.getId(),
                                rdv.getDate(),
                                "Prestation",
                                prestation.getLibelle(),
                                rdv.getEtat()
                                
                        );
                        rdvsDto.add(dto);
                    }
                } 
            }
        }
        
        return rdvsDto;
    }

       
      
    public static V_rdvController getCtrl() {
        return ctrl;
    }
       
       
      
    
    
}

