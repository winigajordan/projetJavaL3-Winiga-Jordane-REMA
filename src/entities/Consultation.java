/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Consultation {
    
    private int id;
    private String statut;
    private java.sql.Date date;
    private int specialiteId;
    private int medecinNci;
    private int patientNci;
    private int ordonnanceId;
    private int prestationId;
    private String constantes;
    private int rdvId;

    public Consultation(Date date, int specialiteId, int medecinNci, int patientNci, int rdvId) {
        this.statut = "En Cours";
        this.date = date;
        this.specialiteId = specialiteId;
        this.medecinNci = medecinNci;
        this.patientNci = patientNci;
        this.rdvId = rdvId;
    }

    public int getRdvId() {
        return rdvId;
    }

    public void setRdvId(int rdvId) {
        this.rdvId = rdvId;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(int specialiteId) {
        this.specialiteId = specialiteId;
    }

    public int getMedecinNci() {
        return medecinNci;
    }

    public void setMedecinNci(int medecinNci) {
        this.medecinNci = medecinNci;
    }

    public int getPatientNci() {
        return patientNci;
    }

    public void setPatientNci(int patientNci) {
        this.patientNci = patientNci;
    }

    public int getOrdonnanceId() {
        return ordonnanceId;
    }

    public void setOrdonnanceId(int ordonnanceId) {
        this.ordonnanceId = ordonnanceId;
    }

    public int getPrestationId() {
        return prestationId;
    }

    public void setPrestationId(int prestationId) {
        this.prestationId = prestationId;
    }

    public String getConstantes() {
        return constantes;
    }

    public void setConstantes(String constantes) {
        this.constantes = constantes;
    }
    
    
    
}
