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
public class Constantes {
    private int id;
    private int temperature;
    private int poids;
    private int tension;
    private int consultationId;

    public Constantes(int temperature, int poids,int tension, int consultationId) {
        this.temperature = temperature;
        this.poids = poids;
        this.tension = tension;
        this.consultationId = consultationId;
    }

    public Constantes(int id, int temperature, int poids,int tension, int consultationId) {
        this.id = id;
        this.temperature = temperature;
        this.poids = poids;
        this.tension = tension;
        this.consultationId = consultationId;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTension() {
        return tension;
    }

    public void setTension(int tension) {
        this.tension = tension;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }
    
    
    
}
