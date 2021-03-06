/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
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
public class UserDao implements IDao <User>{
    
    private final String SQL_LOGIN = "SELECT * FROM user WHERE login = ? AND password = ?";
    private final String SQL_SEARCH_BY_NCI = "SELECT * FROM user WHERE nci = ?";
    private final String SQL_FIND_ALL_USER = "SELECT * FROM user where role NOT IN ( 'ROLE_PATIENT')";
    private final String SQL_DELETE = "DELETE FROM user  WHERE id = ?";
   
    
    private final DataBase database= new DataBase();
    
   public User findUserLoginAndPassword(String login,String password){
        User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_LOGIN);
        try {
            database.getPs().setString(1, login);
            database.getPs().setString(2, password);
            ResultSet rs = database.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                    user = new User(
                    rs.getInt("id"),
                    rs.getInt("nci"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("role") 
                    );       
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return user;
        
    }
    
    
    public int searchByNci(int nci)
    {
        int verification = 0;
        User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_SEARCH_BY_NCI);
        try {
            database.getPs().setInt(1, nci);
            //database.getPs().setString(2, password);
            ResultSet rs = database.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                  verification ++;
                  //System.out.println(verification);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        //return user;
        return verification;
    }
    
    public User createAccount (String u) {
    return null;
    }

    @Override
    public int insert(User ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        int idDel = 0;
        database.openConnexion();
        database.initPrepareStatement(SQL_DELETE);
        try {
            database.getPs().setInt(1, id);
            database.executeUpdate(SQL_DELETE);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next()){
                idDel = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idDel;
    }

    @Override
    public List<User> findAll() {
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL_USER);
        List <User> users = new ArrayList();
        ResultSet rs = database.executeSelect(SQL_FIND_ALL_USER);
        try {
            while(rs.next())
            {
                User u = new User(
                        rs.getInt("id"),
                        rs.getInt("nci"),
                        rs.getString("nom_complet"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return users;
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
}
