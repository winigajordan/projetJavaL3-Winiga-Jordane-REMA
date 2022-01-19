/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.OrdMed;
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
public class OrdMedDao implements IDao <OrdMed>{
    DataBase database = new DataBase();
    private final String SQL_INSERT = "INSERT INTO ord_med(id_med, posologie,id_odonnance) VALUES (?,?,?)";

    @Override
    public int insert(OrdMed ordMed) {
        int idGenere = 0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1, ordMed.getIdMed());
            database.getPs().setString(2, ordMed.getPosologie());
            database.getPs().setInt(3, ordMed.getIdOrdonnance());
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
    public int update(OrdMed ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdMed> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdMed findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
