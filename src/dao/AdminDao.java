/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Admin;
import entities.Rp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class AdminDao implements IDao <Admin>{

    DataBase database = new DataBase();
    private final String SQL_INSERT = "INSERT INTO user (nci, nom_complet, login, password, role) values (?,?,?,?,?) ";

    @Override
    public int insert(Admin admin) {
        int idGenere = 0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1, admin.getNci());
            database.getPs().setString(2, admin.getNomComplet());
            database.getPs().setString(3, admin.getLogin());
            database.getPs().setString(4, admin.getPassword());
            database.getPs().setString(5, admin.getRole());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idGenere = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idGenere; 
    }


    @Override
    public int update(Admin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
