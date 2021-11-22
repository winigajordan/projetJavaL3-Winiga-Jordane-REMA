/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class User {
    
    protected int id;
     protected int nci;
    protected String nomComplet;
   
    protected String login;
    protected String password;
    protected String role;
    //protected String antecedants;

    public User() {
    }

    public User(int nci, String nomComplet, String login, String password, String role) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
    public User(int nci, String nomComplet, String login, String password) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        //this.role = role;
    }

    public User(String nomComplet, String login, String password, String role) {
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id, int nci, String nomComplet, String login, String password, String role) {
        this.id = id;
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
    

 
    
    
    
    
    

    public int getNci() {
        return nci;
    }

    public void setNci(int nci) {
        this.nci = nci;
    }
    
    

    
    
   
    

  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString(){
        return nomComplet;
    }
    
}
