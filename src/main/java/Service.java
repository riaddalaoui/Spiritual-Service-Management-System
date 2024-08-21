
import com.google.maps.model.LatLng;
import fr.insalyon.dasi.test.td1dasi.Client;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import fr.insalyon.dasi.test.td1dasi.Consultation;
import fr.insalyon.dasi.test.td1dasi.Employe;
import fr.insalyon.dasi.test.td1dasi.Medium;
import fr.insalyon.dasi.test.td1dasi.ProfilAstral;
import java.io.IOException;
import java.util.List;
import util.GeoNetApi;
import static util.Message.envoyerMail;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import util.AstroNetApi;
import util.EnumStatut;
import static util.Message.envoyerNotification;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fjourda
 */
public class Service {
    
    public static Boolean inscrireClient(Client client) {
        
        LatLng coords = GeoNetApi.getLatLng(client.getAdresse());
        
        client.setLatitude(coords.lat);
        client.setLongitude(coords.lng);
       
        client.setProfilAstral(new ProfilAstral(client.getPrenom(), client.getDateNaissance()));
        
        Boolean bol = true;
        ClientDao ClientServiceDao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();
            ClientServiceDao.create(client);

            JpaUtil.validerTransaction(); // essayer de valider la transaction
            envoyerMail("flolebg@insa-lyon.fr", client.getMail(), "Inscription réussie", "BRAVO !!!");
        } catch (Exception ex) { // ça n'a pas marché
            ex.printStackTrace();
            JpaUtil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            envoyerMail("flolebg@insa-lyon.fr", client.getMail(), "Inscription pas réussie", "DOMMAGE !!!");
            bol = false; // on pourrait aussi lancer une exception
        } finally { // dans tous les cas, on ferme l'entity manager
            JpaUtil.fermerContextePersistance();
        }
        return bol;
    }

    
    public static Boolean inscrireEmploye(Employe employe) {
        
        Boolean bol = true;
        EmployeDao employeDao = new EmployeDao();
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();
            employeDao.create(employe);

            JpaUtil.validerTransaction(); // essayer de valider la transaction
            System.out.println("Employe inscrit");
        } catch (Exception ex) { // ça n'a pas marché
            ex.printStackTrace();
            JpaUtil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            System.out.println("Echec de l'inscritpion");
            bol = false; // on pourrait aussi lancer une exception
        } finally { // dans tous les cas, on ferme l'entity manager
            JpaUtil.fermerContextePersistance();
        }
        return bol;
    }
    
    public static Boolean inscrireMedium(Medium medium) {
        
        Boolean bol = true;
        MediumDao mediumDao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();
            mediumDao.create(medium);

            JpaUtil.validerTransaction(); // essayer de valider la transaction
            System.out.println("Medium inscrit");
        } catch (Exception ex) { // ça n'a pas marché
            ex.printStackTrace();
            JpaUtil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            System.out.println("Echec de l'inscritpion");
            bol = false; // on pourrait aussi lancer une exception
        } finally { // dans tous les cas, on ferme l'entity manager
            JpaUtil.fermerContextePersistance();
        }
        return bol;
    }
    
    
    public static Client authentifierClient(String mail,String mdp) {

        ClientDao ClientServiceDao = new ClientDao();
        Client client = null;


        try {
            JpaUtil.creerContextePersistance();
            Client clientTrouve = ClientServiceDao.getClient(mail);

            if (clientTrouve != null) {
                if (!(clientTrouve.getMdp().equals(mdp))) {
                    clientTrouve = null;
                }
            }
            client = clientTrouve;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;

    }
    
public static Employe authentifierEmploye(String mail,String mdp) {
        

        EmployeDao employeDao = new EmployeDao();
        Employe employe = null;


        try {
            JpaUtil.creerContextePersistance();
            Employe employeTrouve = employeDao.getEmploye(mail);

            if (employeTrouve != null) {
                if (!(employeTrouve.getMdp().equals(mdp))) {
                    employeTrouve = null;
                }
            }
            employe = employeTrouve;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employe;

    }
    

/*          Methodes du TD non utiles au projet


    public static Client rechercheID(){
        Integer id = Saisie.lireInteger("id :");
        ClientDao ClientServiceDao = new ClientDao();
        Client client = new Client();
        try {
            JpaUtil.creerContextePersistance();
            Client clientTrouve = ClientServiceDao.getClientID(id);
            client = clientTrouve;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    
    public static void afficherListe()
    {
       List<Client> liste = consulterListeClients();
       if (liste != null){
           for (Client client : liste){
                System.out.println(client.toString());
           }
       }
    }
        
           
    public static List<Client> consulterListeClients(){
        ClientDao ClientServiceDao = new ClientDao();
        List<Client> listeClient = null ;

        try {
            JpaUtil.creerContextePersistance();
            List<Client> clientsTrouves = ClientServiceDao.getListeClients();
            listeClient = clientsTrouves;
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeClient;
    }
        
    */ 
    
     public static Consultation creerConsultation (Client c, Medium m) {
        
         Employe employeDispo = null;
         EmployeDao employeDao = new EmployeDao();
        
         Client client=null;                        
         ClientDao clientDao = new ClientDao();

         
         Consultation consultation = new Consultation();
         ConsultationDao consultationDao = new ConsultationDao();
         
         Medium medium = null;
        MediumDao mediumDao = new MediumDao();

         
         
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();
            client = clientDao.getClientID(c.getId());
            

            employeDispo = employeDao.getEmployeDisponibleByGenre(m.getGenre());

            medium = mediumDao.getMediumID(m.getId());
            System.out.println(medium + "\n\n");
   

            
            if (employeDispo != null){



                consultation.setMedium(medium);
                consultation.setClient(client);
                consultation.setStatut(EnumStatut.EnAttente);
                consultation.setEmploye(employeDispo);

                client.addConsultation(consultation);
                
                employeDispo.setDisponible(false);
                employeDispo.setNbConsultations(employeDispo.getNbConsultations()+1);
                
                medium.setNbConsultations(medium.getNbConsultations()+1);

                consultationDao.create(consultation);
                employeDao.update(employeDispo);
                mediumDao.update(medium);
                clientDao.update(client);
                System.out.println("La consultation a été crée");
            }
            else{
                consultation = null;
                System.out.println("Pas d'employé dispo");
            }

            JpaUtil.validerTransaction(); // essayer de valider la transaction

        } catch (Exception e) {
            System.out.println(e);
            JpaUtil.annulerTransaction(); 

        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         
         return consultation;
     }
     
     public static void indiquerPret(Consultation c){
        ConsultationDao consultationDao = new ConsultationDao();
        Consultation consultation = null;
         try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
           consultation = consultationDao.getConsultationID(c.getId());
           consultation.setStatut(EnumStatut.EnCours);
           LocalDateTime localDateTime = LocalDateTime.now();
           Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
           consultation.setDebut(date);
           envoyerNotification(consultation.getClient().getTel(), "Le médium est prêt pour votre consultation, vous pouvez maintenant l'appeler");

            consultationDao.update(consultation);
            JpaUtil.validerTransaction();

         } catch (Exception e) {
            System.out.println(e);
            JpaUtil.annulerTransaction(); 

        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         
        
     }
     
     public static void validerFinConsultation(Consultation c, String commentaire){
         
         
         try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            ConsultationDao consultationDao = new ConsultationDao();
            Consultation consultation = consultationDao.getConsultationID(c.getId());
            consultation.setStatut(EnumStatut.Fini);
            consultation.setCommentaire(commentaire);
            LocalDateTime localDateTime = LocalDateTime.now();
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            consultation.setFin(date);
            
            EmployeDao employeDao = new EmployeDao();
            Employe employe = employeDao.getEmployeID(consultation.getEmploye().getId());
            employe.setDisponible(Boolean.TRUE);
            
            consultationDao.update(consultation);  
            employeDao.update(employe);
            JpaUtil.validerTransaction(); 

         } catch (Exception e) {
            System.out.println(e);
            JpaUtil.annulerTransaction(); 

        } finally {
            JpaUtil.fermerContextePersistance();
        }         
       
     }
     
     
     public static List<String> obtenirPrediction(Integer niveauAmour, Integer niveauSante, Integer niveauTravail, String couleur, String animal) throws IOException {
        AstroNetApi astroApi = new AstroNetApi();
        List <String> predictions = astroApi.getPredictions(couleur, animal, niveauAmour, niveauSante, niveauTravail);
        return predictions;
     }
     
     
     public static Boolean chargerDisponible(Employe employe){
           
        assert employe != null; 
         
         try{
            JpaUtil.creerContextePersistance();
            EmployeDao employeDao = new EmployeDao();
            employe = employeDao.getEmployeID(employe.getId());
         } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         return employe.getDisponible();
     }
     
     public static Consultation chargerConsultation(Employe employe) {
         
        assert employe != null; 
         
        Consultation consultation = null;
         
         try{
            JpaUtil.creerContextePersistance();

            ConsultationDao consultationDao = new ConsultationDao();
            consultation = consultationDao.getConsultationEmploye(employe);
         } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         
        return consultation;
}
     
     public static List<Employe> chargerListeEmploye(){ 
              
         List<Employe> statEmployes = new ArrayList<>();
         
         try{
            JpaUtil.creerContextePersistance();

            EmployeDao employeDao = new EmployeDao();
            statEmployes = employeDao.getListeEmployes();
         } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         
                  
         return statEmployes;
     }
     
     
     
    public static List<Medium> chargerListeMediums()
    {
        MediumDao Dao = new MediumDao();
        List<Medium> listeMediums = new ArrayList<>();;
        try {
            JpaUtil.creerContextePersistance();
            listeMediums = Dao.getAllMediums();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeMediums;
        
    }

    public static Medium rechercheMedium(Integer id){
        MediumDao Dao = new MediumDao();
        Medium medium = null;
        try {
            JpaUtil.creerContextePersistance();
            medium = Dao.getMediumID(id);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return medium;
    }
    


    public static HashMap<Long, LatLng> chargerCarte() {
        HashMap<Long, LatLng> coordonnees = new HashMap<>();

        try {
            JpaUtil.creerContextePersistance();
            ClientDao clientDao = new ClientDao();
            List<Client> clients = clientDao.getCoordonnees();

            for (Client client : clients) {
                Long clientId = client.getId(); 
                LatLng clientCoords = new LatLng(client.getLatitude(), client.getLongitude());
                coordonnees.put(clientId, clientCoords);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return coordonnees;
    }


     
 
    public static List<Consultation> chargerHistoriqueClient(Client client){ 
              
         List<Consultation> historique = new ArrayList<>();
         
         try{
            JpaUtil.creerContextePersistance();

            ConsultationDao consultationDao = new ConsultationDao();
            historique = consultationDao.getHistorique(client);
         } catch (Exception e) {
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }         
         
                  
         return historique;
     }
     
}
