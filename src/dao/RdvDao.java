/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.RdvDto;
import entities.Rdv;
import entities.Specialite;
import entities.TypePrestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Service;

/**
 *
 * @author user
 */
public class RdvDao implements IDao <Rdv> {

    private final String SQL_INSERT = "INSERT INTO rdv ( date, etat, patient_nci, specialite_id, prestation_id) VALUES (?,?,?,?,?)";
    private final String SQL_FIND_ALL_BY_NCI = "SELECT * FROM rdv WHERE etat IN ('En Cours','Valide') AND patient_nci = ?";
    private final String SQL_FIND_RDV_CONSULTATION = "select r.id, r.patient_nci, s.libelle_specialite, r.date, r.etat from rdv as r, specialite as s where r.specialite_id = s.id";
    private final String SQL_FIND_RDV_PRESTATION = "select r.id, r.patient_nci, p.libelle_type_prestation, r.date, r.etat from rdv as r, type_prestation as p where r.prestation_id = p.id";
    private final String SQL_UPDATE_RDV = "UPDATE rdv SET etat = 'Valide' WHERE id=?";

    DataBase dataBase = new DataBase();
//    Service service = new Service();

    @Override
    public int insert(Rdv rdv) {

 //private int idPatient = 0;
        int idGenere = 0;
        
         try {
             
             dataBase.openConnexion();
             dataBase.initPrepareStatement(SQL_INSERT);
             dataBase.getPs().setDate(1, rdv.getDate());
             dataBase.getPs().setString(2,rdv.getEtat());
             dataBase.getPs().setInt(3,rdv.getPatientNci());
             dataBase.getPs().setInt(4,rdv.getSpecialiteId());
             dataBase.getPs().setInt(5,rdv.getPrestationId());
             
             dataBase.executeUpdate(SQL_INSERT);
             ResultSet rs = dataBase.getPs().getGeneratedKeys();
             
             if(rs.next())
            {
                idGenere = rs.getInt(1);
            }
            
         } catch (SQLException ex) {
             Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        dataBase.closeConnexion();
        return idGenere;

    }

    @Override
    public int update(Rdv ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rdv> findAll() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
    public List<Rdv> findAllRdv(int patient_nci) {
        
         dataBase.openConnexion();
         List<Rdv> rdvs = new ArrayList();
         dataBase.initPrepareStatement(SQL_FIND_ALL_BY_NCI);
         
        try {
            dataBase.getPs().setInt(1, patient_nci);
            ResultSet rs = dataBase.executeSelect(SQL_FIND_ALL_BY_NCI);
            while (rs.next())
            {
                
                Rdv rdv = new Rdv(
                        rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getInt(("patient_nci")),
                        rs.getString("etat"),
                        rs.getInt("specialite_id"),
                        rs.getInt("prestation_id")
                );
                rdvs.add(rdv); 

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
         dataBase.closeConnexion();
         return rdvs;
    }
    @Override
    public Rdv findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<RdvDto> findRdvConsultation()  
    {
        dataBase.openConnexion();
        
        List <RdvDto> rdvs = new ArrayList();
        dataBase.initPrepareStatement(SQL_FIND_RDV_CONSULTATION);
        ResultSet rs = dataBase.executeSelect(SQL_FIND_RDV_CONSULTATION);
        try {
            while (rs.next())
            {
                
               RdvDto rdv = new RdvDto  (
                       rs.getInt("id"),
                       rs.getDate("date"),
                       rs.getInt("patient_nci"),
                       rs.getString("libelle_specialite"),
                       rs.getString("etat")
               ); 
               rdvs.add(rdv);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return rdvs;
    }
    
    
    
    
    public List<RdvDto> findRdvPrestation()  
    {
        dataBase.openConnexion();
        
        List <RdvDto> rdvs = new ArrayList();
        dataBase.initPrepareStatement(SQL_FIND_RDV_PRESTATION);
        ResultSet rs = dataBase.executeSelect(SQL_FIND_RDV_PRESTATION);
        try {
            while (rs.next())
            {
                
               RdvDto rdv = new RdvDto  (
                       rs.getInt("id"),
                       rs.getDate("date"),
                       rs.getInt("patient_nci"),
                       rs.getString("libelle_type_prestation"),
                       rs.getString("etat")
               ); 
               rdvs.add(rdv);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return rdvs;
    }
   
    public int updateEtat(int idRdv)
    {
        int idModifie = 0;
    dataBase.openConnexion();
    dataBase.initPrepareStatement(SQL_UPDATE_RDV);
        try {
            dataBase.getPs().setInt(1,idRdv);
            dataBase.executeUpdate(SQL_UPDATE_RDV);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idModifie = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    dataBase.closeConnexion();
    return idModifie;
    }
  
}   
