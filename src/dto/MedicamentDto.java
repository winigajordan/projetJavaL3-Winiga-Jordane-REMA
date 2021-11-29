/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author user
 */
public class MedicamentDto {
    private int id;
    private String code;
    private String nom;
    private String posologie;

    public MedicamentDto(int id, String code, String nom, String posologie) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.posologie = posologie;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    
    
    
}
