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
public class Spirit extends Medium {
    private String support;

    public Spirit() {
    }
    

    public Spirit(String support, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    
    
}
