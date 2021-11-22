/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
//import entities.Patient;
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
public class MedecinDao implements IDao <Medecin> {
    
    private final String SQL_IND_ALL = "SELECT * FROM user";
    DataBase database = new DataBase();

    @Override
    public int insert(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List <Medecin> medecins = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_IND_ALL);
        ResultSet rs = database.executeSelect(SQL_IND_ALL);
        try {
            while (rs.next()){
                Medecin medecin = new Medecin (
                        rs.getInt("nci"),
                        rs.getString("nom_complet"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("specialite_id")
                );
                
                medecins.add(medecin);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return medecins;
    }

    @Override
    public Medecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
