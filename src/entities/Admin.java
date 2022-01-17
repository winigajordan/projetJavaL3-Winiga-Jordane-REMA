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
public class Admin extends User {
     private  String role = "ADMIN";
    
    public Admin( int nci, String nomComplet, String login, String password) {
        super(nci, nomComplet, login, password);
        this.role = role;
    }
    
    
    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
