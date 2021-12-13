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
public class Patient extends User {
    
    private String antecedents;
    private final String ROLE = "ROLE_PATIENT";

    public Patient() {
        //this.role = ROLE;
    }

    public Patient( int nci, String nomComplet, String login, String password, String antecedent) {
        super(nci, nomComplet, login, password);
        this.antecedents = antecedent;
        this.role = ROLE;
    }

    

    
    
    

   

    
    
    
    
    
    
    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String role) {
        this.role = ROLE;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
    
}
