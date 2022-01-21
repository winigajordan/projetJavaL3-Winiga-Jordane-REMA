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
public class Medecin extends User{
    
    private int specialiteId;
    private String role = "ROLE_MEDECIN";

    public Medecin( int nci, String nomComplet, String login, String password, int specialiteId) {
        super(nci, nomComplet, login, password);
        this.specialiteId = specialiteId;
        this.role = role;
    }

    
   
    
    
    
    
    public int getSpecialite_id() {
        return specialiteId;
    }

    public void setSpecialiteId(int specialiteId) {
        this.specialiteId = specialiteId;
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
