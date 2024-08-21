package fr.insalyon.dasi.test.td1dasi;

import fr.insalyon.dasi.test.td1dasi.Client;
import fr.insalyon.dasi.test.td1dasi.Employe;
import fr.insalyon.dasi.test.td1dasi.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.EnumStatut;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-09T16:10:37")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> debut;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Date> fin;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, String> commentaire;
    public static volatile SingularAttribute<Consultation, EnumStatut> statut;

}