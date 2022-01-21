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
public class ConsultationDto {
    private int id;
    private Date date;
    private String service;
    private String etat;
    private int prestationId;

    public ConsultationDto(int id, Date date, String service, String etat, int prestationId ) {
        this.id = id;
        this.date = date;
        this.service = service;
        this.etat = etat;
        this.prestationId = prestationId;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getPrestationId() {
        return prestationId;
    }

    public void setPrestationId(int prestationId) {
        this.prestationId = prestationId;
    }
    
    
    
}
