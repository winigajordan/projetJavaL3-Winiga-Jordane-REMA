/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medicament;
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
public class MedicamentDao implements IDao <Medicament>{
    DataBase database = new DataBase();
    private final String SQL_FIND_ALL = "Select * from medicament";

    @Override
    public int insert(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medocs = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL);
        ResultSet rs = database.executeSelect(SQL_FIND_ALL);
        try {
            while(rs.next()){
                Medicament med = new Medicament(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("nom")
                );
                medocs.add(med);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return medocs;
    }

    @Override
    public Medicament findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
