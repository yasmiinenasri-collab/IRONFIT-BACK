package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.ServiceM;

import java.util.List;

public class FXMLstatMEDController {
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void initialize() {
        ServiceM serviceM = new ServiceM();

        // Récupérez les spécialités depuis la base de données
        List<String> specialites = serviceM.getSpecialitesMedecins();

        // Créez une série de données pour le graphique
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Statistiques des spécialités");

        // Pour chaque spécialité, obtenez le nombre de médecins correspondants et ajoutez-le à la série de données
        for (String specialite : specialites) {
            int count = serviceM.getMedecinCountBySpecialite(specialite);
            series.getData().add(new XYChart.Data<>(specialite, count));
        }

        barChart.getData().add(series);
    }
}
