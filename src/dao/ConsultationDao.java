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
    private final String SQL_INSERT = "INSERT INTO consultation (statut,date,specialite_id,medecin_nci,patient_nci) VALUES (?,?,?,?,?)";


    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
