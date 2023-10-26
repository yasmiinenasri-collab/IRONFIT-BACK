/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.util.List;
import Services.servicePlanning;
import Services.serviceRendez_vous;
import util.MyConnection;

public class main  {
    public static void main(String[] args) {
        MyConnection myConnection = MyConnection.getInstance();
        Connection connection = myConnection.getCnx();

        servicePlanning service = new servicePlanning();

        Planning planning1 = new Planning("Beginner", "Yoga", 1, 50.0f, null, "https://www.youtube.com/watch?v=link", "This is a beginner level Yoga program.");
    service.AjouterPlanning(planning1);
    System.out.println("Added planning: " + planning1);

    // Test afficherPlanning
    List<Planning> plannings = service.afficherPlanning();
    System.out.println("All plannings:");
    for (Planning p : plannings) {
        System.out.println(p);
    }

    // Test modifierPlanning
    planning1.setProgramme("Pilates");
    planning1.setDescription("This is a beginner level Pilates program.");  // Modify the description
    service.modifierPlanning(planning1);
    System.out.println("Modified planning: " + planning1);

    // Test getOne
    Planning planning2 = service.getOne(planning1.getIdPlanning());
    System.out.println("Retrieved planning: " + planning2);

    // Test supprimerPlanning
    service.supprimerPlanning(planning1.getIdPlanning());
    System.out.println("Deleted planning with ID: " + planning1.getIdPlanning());


/*
   
    serviceRendez_vous serviceR = new serviceRendez_vous();

    // Test AjouterRendez_vous
    Rendez_vous rendezvous1 = new Rendez_vous(1, 1, "2023-10-14", "10:00");
    serviceR.AjouterRendez_vous(rendezvous1);
    System.out.println("Added rendezvous: " + rendezvous1);

    // Test afficherRendez_vous
    List<Rendez_vous> rendezvous = serviceR.afficherRendez_vous();
    System.out.println("All rendezvous:");
    for (Rendez_vous r : rendezvous) {
        System.out.println(r);
    }

    // Test modifierRendez_vous
    rendezvous1.setTime_RDV("11:00");
    serviceR.modifierRendez_vous(rendezvous1);
    System.out.println("Modified rendezvous: " + rendezvous1);

    // Test getOne
    Rendez_vous rendezvous2 = serviceR.getOne(rendezvous1.getId_RDV());
    System.out.println("Retrieved rendezvous: " + rendezvous2);

    // Test supprimerRendez_vous
    serviceR.supprimerRendez_vous(rendezvous1.getId_RDV());
    System.out.println("Deleted rendezvous with ID: " + rendezvous1.getId_RDV());



}
*/
}}

    
         
        

    