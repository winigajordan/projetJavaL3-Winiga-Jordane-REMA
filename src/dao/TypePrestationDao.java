/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class TypePrestationDao implements IDao <TypePrestation>{
    
    private DataBase database = new DataBase();
    private String SQL_FIND_ALL = "SELECT * FROM type_prestation";

    @Override
    public int insert(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypePrestation> findAll() {
        List<TypePrestation> typeListe =new ArrayList<>();
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL);
        
        ResultSet rs = database.executeSelect(SQL_FIND_ALL);
        try {
            while (rs.next()){
                TypePrestation tp = new TypePrestation(rs.getInt("id"),rs.getString("libelle_type_prestation"));
                typeListe.add(tp);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        database.closeConnexion();
        return typeListe;
    }

    @Override
    public TypePrestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
