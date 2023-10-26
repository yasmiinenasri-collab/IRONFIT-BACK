package pidev12 ;

import entite.medecin;
import entite.RegimeAli;
import entite.RendezVous;
import services.ServiceM;
import services.ServiceRA;
import services.ServiceRV;
import util.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PIdev12 {

    public static void main(String[] args) {
//         ServiceM - Médecin
        ServiceM serviceM = new ServiceM();

        // Ajouter un médecin
        medecin medecinToAdd = new medecin("fatma", "askri", "pediatre", "ariana", "fatma.aymen@esp", "97188980");
        serviceM.ajouter(medecinToAdd);
//         Modifier un médecin (vous devez connaître son ID)
        medecin medecinToUpdate = serviceM.getOne(new medecin(serviceM.trouverIdMedecin("ines", "askri"))); 
        if (medecinToUpdate != null) {
            medecinToUpdate.setNomMED("ines");
            medecinToUpdate.setPrenomMED("askri");
            medecinToUpdate.setSpecialite("dentiste");
            medecinToUpdate.setAdresse("Nouvelle madina");
            medecinToUpdate.setEmail("ines.askri@gmail.com");
            medecinToUpdate.setTel("98381864");
            serviceM.modifier(medecinToUpdate);
        }

        // Supprimer un médecin (vous devez connaître son ID)
        int medecinIdToDelete = 1; // Mettez l'ID correct ici
        serviceM.supprimer(medecinIdToDelete);
       
        // Obtenir un médecin par ID
        medecin medecinById = serviceM.getOne(new medecin(2)); // Mettez l'ID correct ici
        if (medecinById != null) {
            System.out.println("Médecin trouvé : " + medecinById);
        } else {
            System.out.println("Médecin non trouvé.");
        }

        // Obtenir la liste de tous les médecins
        List<medecin> medecins = serviceM.getAll();
        System.out.println("Liste de médecins :");
        for (medecin medecin : medecins) {
            System.out.println(medecin);
        }

        // ServiceRA - Régime Ali
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceRA serviceRA = new ServiceRA(); // ajouter cnx entre les ()

        // Récupérer l'ID du médecin automatiquement (en utilisant le nom et prénom du médecin)
     int idMedecinAutomatique = serviceM.trouverIdMedecin("fatma", "askri");

        // Exemple d'ajout d'un RegimeAli avec l'ID du médecin trouvé automatiquement
        RegimeAli regime = new RegimeAli();
        regime.setPrixRegime(19.5);
        regime.setTypeRegime("test2");
        regime.setNomMED("fatma");
        regime.setPrenomMED("askri");
        regime.setIdMED(idMedecinAutomatique); // Utilisez l'ID du médecin trouvé
        serviceRA.ajouter(regime);

        // Exemple de modification d'un RegimeAli
        RegimeAli regimeModifie = new RegimeAli();
        regimeModifie.setId(1); // Remplacez par l'ID du régime que vous souhaitez modifier
        regimeModifie.setPrixRegime(150.0);
        regimeModifie.setTypeRegime("Nouveau Régime");
        regimeModifie.setNomMED("Nouveau Médecin");
        regimeModifie.setPrenomMED("Nouveau Prénom");
        serviceRA.modifier(regimeModifie);

        // Exemple de suppression d'un RegimeAli
        int idASupprimer = 1; // Remplacez par l'ID du régime à supprimer
        serviceRA.supprimer(idASupprimer);

        // Exemple de récupération d'un RegimeAli par son ID
        RegimeAli regimeRecupere = serviceRA.getOne(new RegimeAli(2)); // Remplacez par l'ID du régime que vous souhaitez récupérer
        if (regimeRecupere != null) {
            System.out.println("RegimeAli récupéré : " + regimeRecupere);
        } else {
            System.out.println("RegimeAli non trouvé.");
        }

        // Exemple de récupération de tous les RegimeAli associés à un médecin
        RegimeAli rechercheMedecin = new RegimeAli();
        rechercheMedecin.setNomMED("Nom du Médecin");
        rechercheMedecin.setPrenomMED("Prénom du Médecin");
        List<RegimeAli> regimesAssociesAuMedecin = serviceRA.getAll(rechercheMedecin);
        System.out.println("Regimes associés au médecin : ");
        for (RegimeAli regimeAssocie : regimesAssociesAuMedecin) {
            System.out.println(regimeAssocie);
        }   
        
        List<RegimeAli> regimes =serviceRA.getAll();
        System.out.println("Liste des regime :");
        for (RegimeAli RegimeAli : regimes) {
            System.out.println(regime);
        }

     
        // Fermez la connexion à la base de données lorsque vous avez terminé.
        try {
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ServiceRV - Rendez-Vous
        ServiceRV serviceRV = new ServiceRV();

        // Ajouter un rendez-vous
        RendezVous rendezVousToAdd = new RendezVous("wissem", "adouli", new Date(), "12:00 AM");
        serviceRV.ajouter(rendezVousToAdd);

        // Modifier un rendez-vous (vous devez connaître son ID)
        RendezVous rendezVousToUpdate = serviceRV.getOne(new RendezVous(2)); // Mettez l'ID correct ici
        if (rendezVousToUpdate != null) {
            rendezVousToUpdate.setHeureRendezVous("8:00 AM");
            serviceRV.modifier(rendezVousToUpdate);
        }

        // Supprimer un rendez-vous (vous devez connaître son ID)
        int rendezVousIdToDelete = 3; // Mettez l'ID correct ici
        serviceRV.supprimer(rendezVousIdToDelete);

        // Obtenir un rendez-vous par ID
        RendezVous rendezVousById = serviceRV.getOne(new RendezVous(1)); // Mettez l'ID correct ici
        if (rendezVousById != null) {
            System.out.println("Rendez-vous trouvé : " + rendezVousById);
        } else {
            System.out.println("Rendez-vous non trouvé.");
        }

        // Obtenir la liste de tous les rendez-vous
        List<RendezVous> rendezVousList = serviceRV.getAll(new RendezVous());
        System.out.println("Liste de rendez-vous :");
        for (RendezVous rendezVous : rendezVousList) {
            System.out.println(rendezVous);
             }
    }

    
}
            