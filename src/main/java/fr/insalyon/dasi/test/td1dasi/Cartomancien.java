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
public class Cartomancien extends Medium {

    public Cartomancien() {
    }

    public Cartomancien(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }
    
}
