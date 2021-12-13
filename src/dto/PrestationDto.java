/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author user
 */
public class PrestationDto {
    private int id;
    private int patientNci;
    private Date date;
    private String prestation;
    private String statut;
    private String resultat;

    public PrestationDto(int id, Date date, String prestation, String statut, String resultat) {
        this.id = id;
        this.date = date;
        this.prestation = prestation;
        this.statut = statut;
        this.resultat = resultat;
    }

    public PrestationDto(Date date, String prestation, String statut, String resultat) {
        this.date = date;
        this.prestation = prestation;
        this.statut = statut;
        this.resultat = resultat;
    }

    public PrestationDto(int id, int patientNci, Date date, String prestation, String statut, String resultat) {
        this.id = id;
        this.patientNci = patientNci;
        this.date = date;
        this.prestation = prestation;
        this.statut = statut;
        this.resultat = resultat;
    }

    
    public int getPatientNci() {
        return patientNci;
    }

    public void setPatientNci(int patientNci) {
        this.patientNci = patientNci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
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
    
    
    
    
    
}
