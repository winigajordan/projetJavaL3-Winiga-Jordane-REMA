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
public class Rdv {
   private int id;
   private java.sql.Date date;
   private int patientNci;
   private String etat;
   private int specialiteId;
   private int prestationId;

    public Rdv() {
    }
   
    public Rdv(int id, java.sql.Date date, int patientNci, String etat, int specialiteId, int prestationId) {
        this.id = id;
        this.date = date;
        this.patientNci = patientNci;
        this.etat = etat;
        this.specialiteId = specialiteId;
        this.prestationId = prestationId;
    }

    public Rdv(java.sql.Date date, int patientNci, String etat, int specialiteId, int prestationId) {
        this.date = date;
        this.patientNci = patientNci;
        this.etat = etat;
        this.specialiteId = specialiteId;
        this.prestationId = prestationId;
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

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getPatientNci() {
        return patientNci;
    }

    public void setPatientNci(int patientNci) {
        this.patientNci = patientNci;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(int specialiteId) {
        this.specialiteId = specialiteId;
    }

    public int getPrestationId() {
        return prestationId;
    }

    public void setPrestationId(int prestationId) {
        this.prestationId = prestationId;
    }
   
   @Override
    public String toString(){
        return String.valueOf(prestationId);
    }
    
}
