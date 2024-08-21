/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fr.insalyon.dasi.test.td1dasi.Employe;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author fjourda
 */
public class EmployeDao {
    public void create(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
    public Employe getEmployeDisponibleByGenre(String genre) {
    TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e WHERE e.disponible = True AND e.genre = :genre ORDER BY e.nbConsultations ASC ", Employe.class);
    query.setParameter("genre", genre);
    List<Employe> resultList = query.getResultList();
    Employe result = null;
    if (!resultList.isEmpty()) {
         result = resultList.get(0); // Renvoie le premier employé de la liste
    } 
    return result;
    }
    
    public Employe update(Employe employe) {
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }
    
    public Employe getEmployeID(long id){
         TypedQuery <Employe> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Employe c where c.id = :id", Employe.class );
        query.setParameter("id",id);
        Employe employe = query.getSingleResult();
        return employe;
    }
    
    public List<Employe> getListeEmployes(){
        TypedQuery <Employe> query = JpaUtil.obtenirContextePersistance().createQuery("Select e from Employe e", Employe.class );
        return query.getResultList(); 
        
    }
    
    public Employe getEmploye(String mail){
        TypedQuery <Employe> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Employe c where c.mail = :mail", Employe.class );
        query.setParameter("mail",mail);
        Employe employe = query.getSingleResult();
        return employe;
    }
}
