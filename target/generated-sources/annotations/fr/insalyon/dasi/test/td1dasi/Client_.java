package fr.insalyon.dasi.test.td1dasi;

import fr.insalyon.dasi.test.td1dasi.Consultation;
import fr.insalyon.dasi.test.td1dasi.ProfilAstral;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-09T16:10:37")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, Double> latitude;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, String> mdp;
    public static volatile SingularAttribute<Client, ProfilAstral> profilAstral;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile ListAttribute<Client, Consultation> historique;
    public static volatile SingularAttribute<Client, Double> longitude;

}