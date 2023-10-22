/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import models.SalleDeSport;
import services.ServicesAbonnement;
import services.ServicesSalleDeSport;

/**
 * FXML Controller class
 *
 * @author tlili
 */
public class FXMLBarCharTauxController implements Initializable {

    @FXML
    private BarChart<String, Number> barcharts;
    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private CategoryAxis CategoryAxis;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // Récupérez les données depuis votre base de données
        ServicesAbonnement abonnments = new ServicesAbonnement();
        ServicesSalleDeSport salledesports = new ServicesSalleDeSport();

        // Créez une série de données pour les événements et le nombre de participants
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre de Participants");

        // Récupérez la liste des événements
        for (SalleDeSport salle : salledesports.getAllSalleDeSport()) {
            // Pour chaque événement, récupérez le nombre de participants
            int nombreabonnement = abonnments.getNombreParticipants(salle.getId());
            // Ajoutez les données à la série
            series.getData().add(new XYChart.Data<>(salle.getNom(), nombreabonnement));
        }

        // Ajoutez la série de données au graphique
        barcharts.getData().add(series);
    }   
    
}
