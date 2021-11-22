/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Prestation {
    private int id;
    private java.sql.Date date;
    private String statut;
    private String resultat;
    private int nciPatient;
    private int typePrestationId;

    public Prestation(java.sql.Date date, String statut, String resultat, int nciPatient, int typePrestationId) {
        this.date = date;
        this.statut = statut;
        this.resultat = resultat;
        this.nciPatient = nciPatient;
        this.typePrestationId = typePrestationId;
    }

    

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = (java.sql.Date) date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public int getNciPatient() {
        return nciPatient;
    }

    public void setNciPatient(int nciPatient) {
        this.nciPatient = nciPatient;
    }

    public int getTypePrestationId() {
        return typePrestationId;
    }

    public void setTypePrestationId(int typePrestationId) {
        this.typePrestationId = typePrestationId;
    }
    
    
    
}
