/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fr.insalyon.dasi.test.td1dasi.Client;
import fr.insalyon.dasi.test.td1dasi.Consultation;
import fr.insalyon.dasi.test.td1dasi.Employe;
import java.util.List;
import javax.persistence.TypedQuery;
import util.EnumStatut;

/**
 *
 * @author fjourda
 */
public class ConsultationDao {
    public void create(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public Consultation getConsultationID(long id){
         TypedQuery <Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Consultation c where c.id = :id", Consultation.class );
        query.setParameter("id",id);
        Consultation consultation = query.getSingleResult();
        return consultation;
    }
    
    public void update(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    public Consultation getConsultationEmploye(Employe employe) {
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.employe=:employe AND c.statut = :statut1 OR c.statut = :statut2", Consultation.class);
        query.setParameter("employe", employe);
        query.setParameter("statut1", EnumStatut.EnAttente);
        query.setParameter("statut2", EnumStatut.EnCours);         
        return query.getSingleResult();
    }
    
    
    public List<Consultation> getHistorique (Client client){
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.client=:client AND c.statut= :statut", Consultation.class);
        query.setParameter("client", client);
        query.setParameter("statut", EnumStatut.Fini);
        return query.getResultList();
    }
    
}
