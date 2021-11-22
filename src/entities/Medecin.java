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
    private String ROLE = "ROLE_MEDECIN";

    public Medecin( int nci, String nomComplet, String login, String password, int specialite_id) {
        super(nci, nomComplet, login, password);
        this.specialite_id = specialite_id;
    }

    
   
    
    
    
    
    public int getSpecialite_id() {
        return specialite_id;
    }

    public void setSpecialite_id(int specialite_id) {
        this.specialite_id = specialite_id;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
    
    
    
    
    
    
    
    
}
