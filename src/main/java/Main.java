
import com.google.maps.model.LatLng;
import dao.JpaUtil;
import fr.insalyon.dasi.test.td1dasi.Astrologue;
import fr.insalyon.dasi.test.td1dasi.Cartomancien;
import fr.insalyon.dasi.test.td1dasi.Client;
import fr.insalyon.dasi.test.td1dasi.Consultation;
import fr.insalyon.dasi.test.td1dasi.Employe;
import fr.insalyon.dasi.test.td1dasi.Medium;
import fr.insalyon.dasi.test.td1dasi.Spirit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import util.Saisie;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        JpaUtil.creerFabriquePersistance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans Predict'IF.");

        while (true) {
            afficherMenuPrincipal();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    simulerMenuClient(scanner);
                    break;
                case 2:
                    simulerMenuEmploye(scanner);
                    break;
                case 3:
                    simulerMenuAdmin(scanner);
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    public static void afficherMenuPrincipal() {
        System.out.println("\nMenu principal :");
        System.out.println("1. Client");
        System.out.println("2. Employé");
        System.out.println("3. Administrateur");
        System.out.println("4. Quitter");
        System.out.print("Votre choix (tapez un chiffre entre 1 et 4) : ");
    }

    public static void simulerMenuClient(Scanner scanner) throws IOException, ParseException {
        Client clientConnecte = null;
        while (true) {
            afficherMenuClient();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    inscrireClient();
                    break;
                case 2:
                    clientConnecte = authentifierClient();
                    break;
                case 3:
                    afficherProfilAstral(clientConnecte);
                    break;
                case 4:
                    demanderConsultation(clientConnecte);
                    break;
                case 5:
                    afficherHistoriqueClient(clientConnecte);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    public static void afficherMenuClient() {
        System.out.println("\nMenu Client :");
        System.out.println("1. S'inscrire");
        System.out.println("2. S'authentifier");
        System.out.println("3. Voir votre profil Astral");
        System.out.println("4. Afficher Liste Médium et demander consultation");
        System.out.println("5. Voir l'historique de consultations");
        System.out.println("6. Retour");
        System.out.print("Votre choix : ");
    }

    public static void inscrireClient() throws IOException, ParseException {
        String nom = Saisie.lireChaine("Nom:");
        String prenom = Saisie.lireChaine("Prenom:");
        String dateNaissanceStr = Saisie.lireChaine("Date de naissance (format JJ/MM/AAAA):");
        String adresse = Saisie.lireChaine("Adresse:");
        String tel = Saisie.lireChaine("Tel:");
        String mail = Saisie.lireChaine("Mail:");
        String mdp = Saisie.lireChaine("Mot de passe:");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = sdf.parse(dateNaissanceStr);

        Client c = new Client(nom, prenom, mail, adresse, dateNaissance, mdp, tel);
        Service.inscrireClient(c);
    }

    public static Client authentifierClient() throws IOException {
        String mail = Saisie.lireChaine("mail :");
        String mdp = Saisie.lireChaine("mdp :");
        Client client = Service.authentifierClient(mail, mdp);
        if (client == null) {
            System.out.println("Erreur d'authentification");
        }
        return client;
    }
    public static void afficherProfilAstral(Client client) throws IOException {
        System.out.println(client.getProfilAstral());
    }

    public static void demanderConsultation(Client client) throws IOException {
        List<Medium> listeMedium = Service.chargerListeMediums();
        for (Medium med : listeMedium) {
            System.out.println(med.getId() + " " + med.getDenomination());
        }
        Integer voyant = Saisie.lireInteger("Saisissez l'ID du médium que vous souhaitez consulter :");

        Medium medium = Service.rechercheMedium(voyant);

        Consultation cons = Service.creerConsultation(client, medium);
    }

    public static void afficherHistoriqueClient(Client client) {
    System.out.println("\nHistorique des consultations :");
    List<Consultation> historique = Service.chargerHistoriqueClient(client);
    
    if (historique.isEmpty()) {
        System.out.println("Aucune consultation trouvée pour ce client.");
    } else {
        for (Consultation consultation : historique) {
            System.out.println("Consultation ID: " + consultation.getId());
            System.out.println("Medium: " + consultation.getMedium().getDenomination());
            System.out.println("Statut: " + consultation.getStatut());
            // Ajoutez d'autres informations de consultation selon vos besoins
            System.out.println();
        }
    }
}


    public static void simulerMenuEmploye(Scanner scanner) throws IOException {
        Employe employeConnecte = null;
        Consultation consu = null;
        while (true) {
            afficherMenuEmploye();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    employeConnecte = authentifierEmploye();
                    break;
                case 2:
                    consu = indiquerPret(employeConnecte);
                    break;
                case 3:
                    genererPredictions(consu);
                    break;
                case 4:
                    validerFinConsultation(consu);
                    break;
                case 5:
                    afficherStatistiques();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    public static void afficherMenuEmploye() {
        System.out.println("\nMenu Employé :");
        System.out.println("1. S'authentifier");
        System.out.println("2. Indiquer prêt");
        System.out.println("3. Obtenir des prédictions");
        System.out.println("4. Valider fin de consultation");
        System.out.println("5. Voir les statistiques");
        System.out.println("6. Retour");
        System.out.print("Votre choix : ");
    }

    public static Employe authentifierEmploye() throws IOException {
        String mail = Saisie.lireChaine("mail :");
        String mdp = Saisie.lireChaine("mdp :");
        Employe employe = Service.authentifierEmploye(mail, mdp);
        if (employe == null) {
            System.out.println("Erreur d'authentification");
        }
        return employe;
    }

    public static Consultation indiquerPret(Employe employe) throws IOException {
    Consultation consultation = null;
    try {
        // Charger la consultation pour l'employé
        consultation = Service.chargerConsultation(employe);
        
        if (consultation == null) {
            System.out.println("Aucune consultation disponible pour l'employé.");
        }
        
        // Indiquer que l'employé est prêt pour la consultation
        Service.indiquerPret(consultation);
        
        // Afficher le statut de la consultation
        System.out.println("Statut de la consultation : " + consultation.getStatut());
    } catch (Exception exception) {
        System.out.println("Erreur lors de l'indication de prêt pour la consultation : " + exception.getMessage());
        consultation = null;
    }
    
    return consultation;
}



    public static void validerFinConsultation(Consultation consu) throws IOException {
        String commentaire = Saisie.lireChaine("Entrez votre commentaire :");
        Service.validerFinConsultation(consu, commentaire);
    }
    
    public static void afficherStatistiques() throws IOException {
        List<Employe> stats = Service.chargerListeEmploye();
        for (Employe s : stats) {
            System.out.println(s.getNom() + s.getNbConsultations());
        }
        
        List<Medium> statM = Service.chargerListeMediums();
        for (Medium m : statM) {
            System.out.println(m.getDenomination() + m.getNbConsultations());
        }
        

       HashMap<Long, LatLng> coordonnees = Service.chargerCarte();
        
        // Parcours de la HashMap et affichage des éléments
        for (Map.Entry<Long, LatLng> entry : coordonnees.entrySet()) {
            Long clientId = entry.getKey();
            LatLng clientCoords = entry.getValue();
            
            double latitude = clientCoords.lat;
            double longitude = clientCoords.lng;
            
            System.out.println("Client ID: " + clientId + ", Latitude: " + latitude + ", Longitude: " + longitude);
        }
    }
    
    public static void genererPredictions(Consultation consu) throws IOException {
        List<String> pred = Service.obtenirPrediction(1, 1, 1, consu.getClient().getProfilAstral().getCouleur(), consu.getClient().getProfilAstral().getAnimal());
        System.out.println(pred);
    }
    
    public static void simulerMenuAdmin(Scanner scanner) throws IOException {
        while (true) {
            afficherMenuAdmin();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    InscrireEmploye();
                    break;
                case 2:
                    InscrireSpirit();
                    break;
                case 3:
                    InscrireCartomancien();
                    break;
                case 4:
                    InscrireAstrologue();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
    
    public static void afficherMenuAdmin() {
           System.out.println("\nMenu Admin :");
           System.out.println("1. Inscrire Employe");
           System.out.println("2. Inscrire un Spirit");
           System.out.println("3. Inscrire un Cartomancien");
           System.out.println("4. Inscrire un Astrologue");
           System.out.println("5. Retour");
           System.out.print("Votre choix : ");
    }
    public static void InscrireEmploye() {
         String nom = Saisie.lireChaine("Nom:");
         String prenom = Saisie.lireChaine("Prenom:");
         String adresse = Saisie.lireChaine("Adresse:");
         String tel = Saisie.lireChaine("Tel:");
         String genre = Saisie.lireChaine("Genre (F ou H):");
         String mail = Saisie.lireChaine("Mail:");
         String mdp = Saisie.lireChaine("Mot de passe:");
         Employe e = new Employe (nom,prenom,mail,adresse, mdp, tel,genre);
         Service.inscrireEmploye(e);
    }

    public static void InscrireSpirit() {
         String denomination = Saisie.lireChaine("Dénomination:");
         String support = Saisie.lireChaine("Support:");
         String genre = Saisie.lireChaine("Genre (F ou H):");
         String presentation = Saisie.lireChaine("Presentation:");
         Medium m = new Spirit (support,denomination,genre,presentation);
         Service.inscrireMedium(m);
    }

    public static void InscrireCartomancien() {
         String denomination = Saisie.lireChaine("Dénomination:");
         String genre = Saisie.lireChaine("Genre (F ou H):");
         String presentation = Saisie.lireChaine("Presentation:");
         Medium m = new Cartomancien (denomination,genre,presentation);
         Service.inscrireMedium(m);
    }
    public static void InscrireAstrologue() {
         String denomination = Saisie.lireChaine("Dénomination:");
         String genre = Saisie.lireChaine("Genre (F ou H):");
         String presentation = Saisie.lireChaine("Presentation:");
         String formation = Saisie.lireChaine("Formation:");
         String promotion = Saisie.lireChaine("Promotion");
         Medium m = new Astrologue (formation, promotion, denomination,genre,presentation);
         Service.inscrireMedium(m);
    }

} 