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
    
    private int specialite_id;
    private String role = "ROLE_MEDECIN";

    public Medecin( int nci, String nomComplet, String login, String password, int specialite_id) {
        super(nci, nomComplet, login, password);
        this.specialite_id = specialite_id;
        this.role = role;
    }

    
   
    
    
    
    
    public int getSpecialite_id() {
        return specialite_id;
    }

    public void setSpecialite_id(int specialite_id) {
        this.specialite_id = specialite_id;
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
