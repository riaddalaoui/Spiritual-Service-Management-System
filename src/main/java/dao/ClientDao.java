/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import fr.insalyon.dasi.test.td1dasi.Client;
import java.util.List;
import javax.persistence.TypedQuery;


/**
 *
 * @author fjourda
 */
public class ClientDao {
    
    public void create(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    public Client getClient(String mail){
        TypedQuery <Client> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Client c where c.mail = :mail", Client.class );
        query.setParameter("mail",mail);
        Client client = query.getSingleResult();
        return client;
    }
    public Client getClientID(long id){
        TypedQuery <Client> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Client c where c.id = :id", Client.class );
        query.setParameter("id",id);
        return query.getSingleResult();
    }
    
        public List<Client> getListeClients(){
        TypedQuery <Client> query = JpaUtil.obtenirContextePersistance().createQuery("Select c from Client c order by c.nom,c.prenom", Client.class );
        return query.getResultList();
    }

    public void update(Client client) {
        JpaUtil.obtenirContextePersistance().merge(client);
    }
        
    
    public List<Client> getCoordonnees(){
        TypedQuery <Client> query = JpaUtil.obtenirContextePersistance().createQuery("Select e FROM Client e", Client.class );
        return query.getResultList(); 
        
    }
}
