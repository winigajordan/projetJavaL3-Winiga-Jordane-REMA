/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PatientDao implements IDao <Patient> {
    
     private final String SQL_CREATE_ACCOUNT = "INSERT INTO user (nci, nom_complet, login, password, role, antecedents) VALUES (?,?,?,?,?,?)";
     private DataBase dataBase = new DataBase();

    @Override
    public int insert(Patient patient) {
        
        //private int idPatient = 0;
        int idGenere = 0;
        
         try {
             
             dataBase.openConnexion();
             dataBase.initPrepareStatement(SQL_CREATE_ACCOUNT);
             dataBase.getPs().setInt(1, patient.getNci());
             dataBase.getPs().setString(2,patient.getNomComplet());
             dataBase.getPs().setString(3,patient.getLogin());
             dataBase.getPs().setString(4,patient.getPassword());
             dataBase.getPs().setString(5,patient.getRole());
             dataBase.getPs().setString(6,patient.getAntecedents());
             dataBase.executeUpdate(SQL_CREATE_ACCOUNT);
             ResultSet rs = dataBase.getPs().getGeneratedKeys();
             
             if(rs.next())
            {
                idGenere = rs.getInt(1);
            }
             System.out.println(idGenere);
         } catch (SQLException ex) {
             Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return idGenere;
    }
    
    

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
}
