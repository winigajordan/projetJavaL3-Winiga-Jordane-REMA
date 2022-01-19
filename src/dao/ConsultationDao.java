/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ConsultationDto;
import entities.Consultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConsultationDao implements IDao <Consultation>{
    
    DataBase database = new DataBase();
    private final String SQL_SELECT_BY_NCI_PATIENT ="SELECT c.id, c.date, s.libelle_specialite, c.statut FROM consultation as c, specialite as s where s.id=c.specialite_id and c.patient_nci = ?";
    private final String SQL_INSERT = "INSERT INTO consultation (statut,date,specialite_id,medecin_nci,patient_nci,consultation_rdv_id) VALUES (?,?,?,?,?,?)";
    private final String SQL_FIND_ALL = "SELECT * FROM consultation";
    private final String SQL_SELECT_BY_NCI_MEDECIN = "SELECT * FROM consultation WHERE medecin_nci = ?";
    private final String SQL_CHANGE_STATUT = "UPDATE consultation SET statut = 'Annule' WHERE id=?";
    private final String SQL_CHANGE_STATUT_TO_DONE = "UPDATE consultation SET statut = 'Fait', constantes_id =?, prestation_id = ? WHERE id=?";
    
    
    
    public List <ConsultationDto> findByNci(int nci_patient) 
    {
        List<ConsultationDto> consultations = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_NCI_PATIENT);
        try {
            database.getPs().setInt(1, nci_patient);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_NCI_PATIENT);
            while (rs.next())
            {
                ConsultationDto consultationDto = new ConsultationDto(
                rs.getInt("id"),
                rs.getDate("date"),
                rs.getString("libelle_specialite"),
                rs.getString("statut")
                );
                consultations.add(consultationDto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    public List<Consultation> findByNciMedecin(int nci_medecin)
    {
        List<Consultation> consultations = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_NCI_MEDECIN);
        try {
            database.getPs().setInt(1, nci_medecin);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_NCI_MEDECIN);
            while(rs.next())
            {
                Consultation c = new Consultation(
                rs.getInt("id"), //id
                        rs.getString("statut"), // statut
                        rs.getDate("date"), //date
                        rs.getInt("specialite_id"), //specialiteId
                        rs.getInt("medecin_nci"), //medecinNci
                        rs.getInt("patient_nci"), //patientNci
                        rs.getInt("ordonnance_id"), //ordonnanceId
                        rs.getInt("prestation_id"), //PrestationId
                        rs.getInt("constantes_id"), //constanteId
                        rs.getInt("consultation_rdv_id") //rdvId
                );
                consultations.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return consultations;
    }

    @Override
    public int insert(Consultation consultation) {
            
        int generetedId = 0;
        database.openConnexion();
        
       try {
           database.initPrepareStatement(SQL_INSERT);
           database.getPs().setString(1,consultation.getStatut() );
           database.getPs().setDate(2, consultation.getDate());
           database.getPs().setInt(3, consultation.getSpecialiteId() );
           database.getPs().setInt(4, consultation.getMedecinNci() );
           database.getPs().setInt(5, consultation.getPatientNci());
           database.getPs().setInt(6, consultation.getRdvId());
           
           
           database.executeUpdate(SQL_INSERT);
           ResultSet rs = database.getPs().getGeneratedKeys();
           while(rs.next())
           {
               generetedId = rs.getInt(1);
           }
     
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }

        database.closeConnexion();
         return generetedId;
    }

    @Override
    public int update(Consultation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        List <Consultation> consultations = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL);
        ResultSet rs = database.executeSelect(SQL_INSERT);
        
       try {
           while(rs.next())
           {
               Consultation c = new Consultation (
               rs.getDate("date"),
               rs.getInt("specialite_id"),
               rs.getInt("medecin_nci"),
               rs.getInt("patient_nci"),
               rs.getInt("consultation_rdv_id")
               );
               
               consultations.add(c);
           }
               
               database.closeConnexion();
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return consultations;
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int updateStatut(int idConsultation)
    {
        int idModifie = 0;
    database.openConnexion();
    database.initPrepareStatement(SQL_CHANGE_STATUT);
        try {
            database.getPs().setInt(1,idConsultation);
            database.executeUpdate(SQL_CHANGE_STATUT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idModifie = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    database.closeConnexion();
    return idModifie;
    }
    
    public int updateStatutFait(int idConsultation, int idRdv, int idConstante)
    {
       database.openConnexion();
       int idGenere = 0;
       database.initPrepareStatement(SQL_CHANGE_STATUT_TO_DONE);
       
        try {
            database.getPs().setInt(1, idConsultation);
            database.getPs().setInt(2, idRdv);
            database.getPs().setInt(3, idConsultation);
            idGenere = database.executeUpdate(SQL_CHANGE_STATUT_TO_DONE);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       database.closeConnexion();
        return idGenere;
       
    }
    
    
}
