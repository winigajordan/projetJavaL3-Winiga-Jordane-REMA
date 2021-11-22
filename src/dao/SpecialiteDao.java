/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Specialite;
import entities.TypePrestation;
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
public class SpecialiteDao implements IDao{
    
    private DataBase database = new DataBase();
    private String SQL_FIND_ALL = "SELECT * FROM specialite";


    @Override
    public int insert(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() {
        
        

        List<Specialite> typeListe =new ArrayList<>();
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL);
        
        ResultSet rs = database.executeSelect(SQL_FIND_ALL);
        try {
            while (rs.next()){
                Specialite tp = new Specialite(rs.getInt("id"),rs.getString("libelle_specialite"));
                typeListe.add(tp);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        database.closeConnexion();
        return typeListe;

    }

    @Override
    public Object findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
