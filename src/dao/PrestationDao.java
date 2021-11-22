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
   private final String SQL_INSERT = "INSERT INTO prestation (date, statut, resultat, patient_nci, type_prestation_id) VALUES (?,?,?,?,?)";
       
   

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<PrestationDto> findByNci(int patient_nci)
    {
        
       
           List <PrestationDto> prestations = new ArrayList();
           dataBase.openConnexion();
           dataBase.initPrepareStatement(SQL_FIND_BY_NCI);
           try {
           dataBase.getPs().setInt(1, patient_nci);
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
    
   
}
