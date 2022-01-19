/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Constantes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConstantesDao implements IDao<Constantes>{
    
    DataBase database = new DataBase();
    
    private final String SQL_INSERT = "insert into constantes (temperature,poids,tension,id_consultation) values (?,?,?,?)";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM constantes WHERE id = ? ";
    
    @Override
    public int insert(Constantes c) {
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        int idGenere= 0;
        try {
            database.getPs().setInt(1, c.getTemperature());
            database.getPs().setInt(2, c.getPoids());
            database.getPs().setInt(3, c.getTension());
            database.getPs().setInt(4, c.getConsultationId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            while(rs.next())
           {
               idGenere = rs.getInt(1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ConstantesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return idGenere;
    }
    
    @Override
    public int update(Constantes ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Constantes> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Constantes findById(int id) {
Constantes c = null ;
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_ID);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next()){
                c = new Constantes (
                        rs.getInt("id"),
                        rs.getInt("temperature"),
                        rs.getInt("poids"),
                        rs.getInt("tension"),
                        rs.getInt("temperature")
                );
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConstantesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return c;    }
    
    
}
