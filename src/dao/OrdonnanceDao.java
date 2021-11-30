/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Ordonnance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class OrdonnanceDao implements IDao <Ordonnance>{
    DataBase database = new DataBase();
    private final String SQL_INSERT = "INSERT INTO ordonnance (consultation_id) VALUES(?)";

    @Override
    public int insert(Ordonnance ord) {
        int idGenere = 0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1, ord.getIdConsultation()); 
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            while (rs.next()){
                idGenere = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdMedDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idGenere;
    }

    @Override
    public int update(Ordonnance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
