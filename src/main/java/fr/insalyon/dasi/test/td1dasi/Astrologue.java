/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.td1dasi;

import javax.persistence.Entity;


/**
 *
 * @author fjourda
 */
@Entity
public class Astrologue extends Medium {
    private String formation;
    private String promotion;

    public Astrologue() {
    }
    
    

    public Astrologue(String formation, String promotion, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    
    
}
