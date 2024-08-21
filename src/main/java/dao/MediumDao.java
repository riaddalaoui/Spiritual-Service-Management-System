/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fr.insalyon.dasi.test.td1dasi.Medium;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author fjourda
 */
public class MediumDao {
    public void create(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    public Medium getMediumID(long id){
        TypedQuery <Medium> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Medium c where c.id =:id", Medium.class );
        query.setParameter("id",id);
        Medium medium = query.getSingleResult();
        return medium;
    }
    public void update(Medium medium) {
         JpaUtil.obtenirContextePersistance().merge(medium);
    }
    
    public List<Medium> getAllMediums(){
    TypedQuery <Medium> query = JpaUtil.obtenirContextePersistance().createQuery("Select m from Medium m", Medium.class );
    return query.getResultList(); 
    }
    
    public List<Medium> getNbConsultations(){
        TypedQuery <Medium> query = JpaUtil.obtenirContextePersistance().createQuery("Select e.denomination e.nbConsultations from Medium e", Medium.class );
        return query.getResultList(); 
        
    }
}
