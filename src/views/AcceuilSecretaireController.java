/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.RdvDto;
import entities.Consultation;
import entities.Medecin;
import entities.Prestation;
import entities.Specialite;
import entities.TypePrestation;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class AcceuilSecretaireController implements Initializable {

    Service service = new Service();
    private ObservableList<Medecin> obm;
    private static AcceuilSecretaireController ctrl;
    
    
    //Textes
    @FXML
    private Text txtNomComplet;
    @FXML
    private Text txrNci;

    
    //Table consultation
    @FXML
    private TableView<RdvDto> tblvRdv;
    @FXML
    private TableColumn<RdvDto, Integer> tblcConsultationNci;
    @FXML
    private TableColumn<RdvDto, String> tblcConsultationSpecialite;
    @FXML
    private TableColumn<RdvDto, Date> tblcConsultationDate;
    @FXML
    private TableColumn <RdvDto, String> tblcConsultationEtat;
    ObservableList <RdvDto> obRdvConsultation;
    
    
    //Table de prestation
    @FXML
    private TableView<RdvDto> tblvRdvPrestation;
    @FXML
    private TableColumn<RdvDto, Integer> tblcPrestationNci;
    @FXML
    private TableColumn<RdvDto, String> tblcPrestationType;
    @FXML
    private TableColumn<RdvDto, Date> tblcPrestationDate;
    @FXML
    private TableColumn<RdvDto, String> tblcPrestationEtat;
    ObservableList <RdvDto> obRdvPrestation;
    
    
    //Autres
    @FXML
    private TextField FormNciPatient;
    @FXML
    private TextField FormServiceDemande;
    @FXML
    private TextField formDate;
    @FXML
    private Button btnPrestation;
    @FXML
    private ComboBox<Medecin> cboMedecin;
    @FXML
    private Button btnConsultation;
    @FXML
    private Button btnDeconnexion;
    
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNomComplet.setText("Nom : " + String.valueOf(ConnexionPageController.getCtrl().getUser().getNomComplet()));
        txrNci.setText("NCI : " + String.valueOf(ConnexionPageController.getCtrl().getUser().getNci()));
        laodTableViewConsultation();
        laodTableViewPrestation();
        cboMedecin.setVisible(false);
        btnConsultation.setVisible(false);
    }    
    
    
    public void laodTableViewConsultation(){
        List <RdvDto> rdvs = service.showConsultationToSecretaire();
        //for(RdvDto rdv : rdvs)
        //{
        //    System.out.println("Consultation : "+rdv.getServiceDemande());
        //    }
       tblcConsultationNci.setCellValueFactory(new PropertyValueFactory<>("nciPatient"));
       tblcConsultationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcConsultationSpecialite.setCellValueFactory(new PropertyValueFactory<>("typeServiceDemande"));
       tblcConsultationEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       obRdvPrestation = FXCollections.observableArrayList(rdvs);
       tblvRdv.setItems(obRdvPrestation);
    }
    
    
    
    public void laodTableViewPrestation(){
        List <RdvDto> rdvs = service.showPrestationsToSecretaire();
       
       tblcPrestationNci.setCellValueFactory(new PropertyValueFactory<>("nciPatient"));
       tblcPrestationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcPrestationType.setCellValueFactory(new PropertyValueFactory<>("typeServiceDemande"));
       tblcPrestationEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       obRdvConsultation = FXCollections.observableArrayList(rdvs);
       tblvRdvPrestation.setItems(obRdvConsultation);
    }

    

    

    @FXML
    private void handleFillFormConsultation(MouseEvent event) {
        btnPrestation.setVisible(false);
        btnConsultation.setVisible(true);
        cboMedecin.setVisible(true);
        
        
        try{
            RdvDto rdv = tblvRdv.getSelectionModel().getSelectedItem();
            
        FormNciPatient.setText(String.valueOf(rdv.getNciPatient()));
        FormServiceDemande.setText(String.valueOf(rdv.getTypeServiceDemande()));
        formDate.setText(String.valueOf(rdv.getDate()));
        
        laodComboBoxSpecialiste(rdv.getTypeServiceDemande());
            
        } 
        catch(Exception ex){
            showAlert("Veuillez selectionner un RDV");
        }
          
    }

    
    @FXML
    private void handleFillFormPrestation(MouseEvent event) {
        btnPrestation.setVisible(true);
        btnConsultation.setVisible(false);
        cboMedecin.setVisible(false);
        
         try{
        
             RdvDto rdv = tblvRdvPrestation.getSelectionModel().getSelectedItem();
             
        FormNciPatient.setText(String.valueOf(rdv.getNciPatient()));
        FormServiceDemande.setText(String.valueOf(rdv.getTypeServiceDemande()));
        formDate.setText(String.valueOf(rdv.getDate()));
        } 
        catch(Exception ex){
            showAlert("Veuillez selectionner un RDV");
        }            
    }
    
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Validation de RDV");
        alert.setContentText(message);
        alert.show();
    }
    
    
    
    @FXML
    private void handleAskPrestation(MouseEvent event) {
        
       RdvDto rdv = tblvRdvPrestation.getSelectionModel().getSelectedItem();

        if (rdv!= null)
        {
            if(service.checkRdvInPrestation(rdv.getId()))
            {
                showAlert("Ce Rdv a déjà été validé ");
            }
            else
            {
            
                //Creation de la prestation
                Date date = rdv.getDate();
                int nciPatient = rdv.getNciPatient();
                int typeId = 0;
                int rdvId = rdv.getId();
                List<TypePrestation> prestations = service.showAllType();

                for(TypePrestation p : prestations){

                    if(p.getLibelle().equals(rdv.getTypeServiceDemande()))
                    {
                        typeId = p.getId();
                    }
                }

                Prestation p = new Prestation(
                        (java.sql.Date) date,"En cours", "--" , nciPatient,typeId,rdvId  
                );

                int idPrestation = service.createPrestation(p);
                showAlert("Prestation id : "+ idPrestation);
                
                //Changement de l'état du Rdv dans la base de donnée
                service.updateRdv(rdv.getId());    
                
                //Mise à jours de la tableView
                laodTableViewPrestation();
            }        
        }
        
        else
        {
        showAlert("Veuillez selectionner un RDV");
        }
    }
    
    

    
    
    @FXML
    private void handleAskConsultation(MouseEvent event) {
        RdvDto rdv = tblvRdv.getSelectionModel().getSelectedItem();
        
        if (rdv!= null)
        {
            
            java.sql.Date date = rdv.getDate();
            int specialiteId = 0;
            int patientNci = rdv.getNciPatient();
            int medecinNci = 0;
            int rdvId = rdv.getId();
            
            if (cboMedecin.getSelectionModel().getSelectedItem()!=null)
            {
                if(service.checkRdvInConsultation(rdv.getId()))
                {
                    showAlert("Ce Rdv a déjà été validé ");
                }
                else
                {
                    medecinNci = cboMedecin.getSelectionModel().getSelectedItem().getNci();
                    List<Specialite> specialites = service.showAllSpecialisation();   
                    for(Specialite s : specialites)
                    {

                        if(s.getLibelle().equals(rdv.getTypeServiceDemande()))
                       {
                            specialiteId = s.getId();
                        }
                    }

                    Consultation consultation  = new Consultation
                    (
                    date, specialiteId, medecinNci, patientNci, rdvId
                    );

                    int idConsultation = service.createConsultation(consultation);
                    
                    //Changement de l'état du Rdv dans la base de donnée
                    service.updateRdv(rdv.getId());    
                
                    //Mise à jours de la tableView
                    laodTableViewConsultation();
                    
                    showAlert("Consultation id : "+ idConsultation);
                    
                }
                
            }
        
            else
            {
                showAlert("Veuillez choisir un spécialiste");
            }

        }
        
        else
        {
            showAlert("Veuillez selectionner un RDV");
        }   
    } 
    
    
    private void laodComboBoxSpecialiste(String specialite)
    {
     obm = FXCollections.observableArrayList(service.showMedecin(specialite));
     cboMedecin.setItems(obm);
    }

    @FXML
    private void handleDisconnection(MouseEvent event) {
        AnchorPane root;
        try
        {
            Stage stage = (Stage) btnDeconnexion.getScene().getWindow();
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

  
    
}
