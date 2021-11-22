/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Specialite;
import java.sql.Date;

/**
 *
 * @author user
 */
public class RdvDto {
    private int id;
    private Date date;
    private int nciPatient;
    private String serviceDemande;
    private String typeServiceDemande;
    private String etat;

    public RdvDto() {
    }

    public RdvDto(int id, Date date, String serviceDemande, String typeServiceDemande, String etat) {
        this.id = id;
        this.date = date;
        this.serviceDemande = serviceDemande;
        this.typeServiceDemande = typeServiceDemande;
        this.etat = etat;
    }

    public RdvDto(int id, Date date, int nciPatient, String typeServiceDemande, String etat) {
        this.id = id;
        this.date = date;
        this.nciPatient = nciPatient;
        this.typeServiceDemande = typeServiceDemande;
        this.etat = etat;
    }

  

    
    
    
    public int getNciPatient() {
        return nciPatient;
    }

    public void setNciPatient(int nciPatient) {
        this.nciPatient = nciPatient;
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

    public String getServiceDemande() {
        return serviceDemande;
    }

    public void setServiceDemande(String serviceDemande) {
        this.serviceDemande = serviceDemande;
    }

    public String getTypeServiceDemande() {
        return typeServiceDemande;
    }

    public void setTypeServiceDemande(String typeServiceDemande) {
        this.typeServiceDemande = typeServiceDemande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    @Override
    public String toString(){
        return serviceDemande;
    }
    
    
    
}
