/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.td1dasi;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.AstroNetApi;

/**
 *
 * @author fjourda
 */
@Embeddable
public class ProfilAstral {

    private String signeZodiac;
    private String signeChinois;
    private String couleur;
    private String animal;
    
    
    
    public String getSigneZodiac() {
    return signeZodiac;
    }

    public void setSigneZodiac(String signeZodiac) {
        this.signeZodiac = signeZodiac;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public ProfilAstral(String prenom, Date dateNaissance) {
            AstroNetApi astroApi = new AstroNetApi();
        try{
            List<String> profil = astroApi.getProfil(prenom, dateNaissance); 
            setSigneZodiac(profil.get(0));    
            setSigneChinois(profil.get(1));
            setCouleur(profil.get(2));
            setAnimal(profil.get(3));
        }
        catch (IOException e){
            System.out.println("Erreur dans le calcul du profil astral");
        }
        
    }

    public ProfilAstral() {
    }
    
    @Override
    public String toString() {
        return signeZodiac + signeChinois + couleur + animal;
    }

    
}
