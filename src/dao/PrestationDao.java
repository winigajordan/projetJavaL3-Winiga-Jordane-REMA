/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import entities.Rdv;
import dto.PrestationDto;
import entities.Prestation;
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
public class PrestationDao implements IDao <Prestation>{
    
   DataBase dataBase = new DataBase();
   //private final String  SQL_FIND_PRESTATION_BY_ID = "select * from prestation where id = ?";
   private final String SQL_FIND_BY_NCI = "select p.date, t.libelle_type_prestation, p.statut, p.resultat from prestation as p, type_prestation as t where p.type_prestation_id = t.id AND p.patient_nci= ? ";
   private final String SQL_INSERT = "INSERT INTO prestation (date, statut, resultat, patient_nci, type_prestation_id, prestation_rdv_id) VALUES (?,?,?,?,?,?)";
   private final String SQL_FIND_ALL = "SELECT * FROM prestation"; 
   private final String SQL_FIND_ALL_CONSULTATIONS = "select p.id, p.date, p.statut, p.resultat, p.patient_nci, t.libelle_type_prestation from prestation as p, type_prestation as t where p.type_prestation_id = t.id";
   private final String SQL_GET_DATE = "SELECT DISTINCT date from prestation";
   private final String SQL_FIND_BY_DATE = "select p.id, p.date, p.statut, p.resultat, p.patient_nci, t.libelle_type_prestation from prestation as p, type_prestation as t where p.type_prestation_id = t.id and p.date = ?";
   private final String SQL_UPDATE_PRESTATION = "UPDATE prestation SET statut = 'Annule' WHERE id=?";
   private final String SQL_VALIDATE_PRESTATION = "UPDATE prestation SET statut = 'Faite', resultat = ? WHERE id=?";
   
   
    @Override
    public int insert(Prestation prestation) {
        int generetedId = 0;
        dataBase.openConnexion();
        
       try {
           dataBase.initPrepareStatement(SQL_INSERT);
           dataBase.getPs().setDate(1, prestation.getDate());
           dataBase.getPs().setString(2 , prestation.getStatut());
           dataBase.getPs().setString(3 , prestation.getResultat());
           dataBase.getPs().setInt(4 , prestation.getNciPatient());
           dataBase.getPs().setInt(5 , prestation.getTypePrestationId());
           dataBase.getPs().setInt(6, prestation.getRdvId());
           dataBase.executeUpdate(SQL_INSERT);
           ResultSet rs = dataBase.getPs().getGeneratedKeys();
           while(rs.next())
           {
               generetedId = rs.getInt(1);
           }
           
           
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }

        dataBase.closeConnexion();
         return generetedId;
    }

    @Override
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        List <Prestation> prestations = new ArrayList();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_ALL);
        ResultSet rs = dataBase.executeSelect(SQL_INSERT);
        
       try {
           while(rs.next())
           {
               Prestation p = new Prestation (
               rs.getDate("date"),
               rs.getString("statut"),
                       rs.getString("resultat"),
                       rs.getInt("patient_nci"),
                       rs.getInt("type_prestation_id"),
                       rs.getInt("prestation_rdv_id")
               );
               
               prestations.add(p);
           }
               
               dataBase.closeConnexion();
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return prestations;
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<PrestationDto> findByNci(int patientNci)
    {
        
       
           List <PrestationDto> prestations = new ArrayList();
           dataBase.openConnexion();
           dataBase.initPrepareStatement(SQL_FIND_BY_NCI);
           try {
           dataBase.getPs().setInt(1, patientNci);
           ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_NCI);
           while (rs.next()){
               PrestationDto prestation = new PrestationDto(
                       //rs.getInt("id"),
                       rs.getDate("date"),
                       rs.getString("libelle_type_prestation"),
                       rs.getString("statut"),
                       rs.getString("resultat")
               );
               prestations.add(prestation);
           }
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
       dataBase.closeConnexion();
       return prestations;
    }
    
     
     public List <PrestationDto> returnPrestationToRp()
     {
         List <PrestationDto> prestations = new ArrayList();
         dataBase.openConnexion();
         dataBase.initPrepareStatement(SQL_FIND_ALL_CONSULTATIONS);
         ResultSet rs = dataBase.executeSelect(SQL_FIND_ALL_CONSULTATIONS);
       try {
           while (rs.next()){
               PrestationDto prestation = new PrestationDto(
                       rs.getInt("id"),
                       rs.getInt("patient_nci"),
                       rs.getDate("date"),
                       rs.getString("libelle_type_prestation"),
                       rs.getString("statut"),
                       rs.getString("resultat")
               );
               prestations.add(prestation);
           }
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
         
         dataBase.closeConnexion();
         return prestations;
     }
     
     public List<java.sql.Date> findDate()
     {
         List<java.sql.Date> dates = new ArrayList();
         dataBase.openConnexion();
         dataBase.initPrepareStatement(SQL_GET_DATE);
         ResultSet rs = dataBase.executeSelect(SQL_GET_DATE);
       try {
           while (rs.next()){
               dates.add(rs.getDate("date"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
         dataBase.closeConnexion();
         return dates;
     }
     
     public List <PrestationDto> returnPrestationToRpByDate(java.sql.Date date)
     {
         List <PrestationDto> prestations = new ArrayList();
         dataBase.openConnexion();
         dataBase.initPrepareStatement(SQL_FIND_BY_DATE);
         
       try {
           dataBase.getPs().setDate(1, date);
            ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_DATE);
           while (rs.next()){
               PrestationDto prestation = new PrestationDto(
                       rs.getInt("id"),
                       rs.getInt("patient_nci"),
                       rs.getDate("date"),
                       rs.getString("libelle_type_prestation"),
                       rs.getString("statut"),
                       rs.getString("resultat")
               );
               prestations.add(prestation);
           }
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
         
         dataBase.closeConnexion();
         return prestations;
     
     }
     
     public void annulationPrestation(int id){
     
         dataBase.openConnexion();
         dataBase.initPrepareStatement(SQL_UPDATE_PRESTATION);
       try {
           dataBase.getPs().setInt(1, id);
           dataBase.executeUpdate(SQL_UPDATE_PRESTATION);
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
         dataBase.closeConnexion();
     }
   
     
     public void updateConsultation(int id, String resultat){
         dataBase.openConnexion();
         dataBase.initPrepareStatement(SQL_VALIDATE_PRESTATION);
       try {
           dataBase.getPs().setString(1, resultat);
           dataBase.getPs().setInt(2, id);
           dataBase.executeUpdate(SQL_VALIDATE_PRESTATION);
       } catch (SQLException ex) {
           Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
         dataBase.closeConnexion();
     }
     
}
