/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author tlili
 */
public class FXMLpiechartyasmineController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private Button retouirhme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
    ServiceUser su = new ServiceUser();
 
     


    // Appelez une méthode pour récupérer les données depuis la base de données
    int hommes = su.getHommesCount(); // Remplacez par votre méthode réelle
    int femmes = su.getFemmesCount(); // Remplacez par votre méthode réelle

    // Créez des objets PieChart.Data
    PieChart.Data hommesData = new PieChart.Data("Hommes", hommes);
    PieChart.Data femmesData = new PieChart.Data("Femmes", femmes);

    // Créez une liste de données
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(hommesData, femmesData);

    // Ajoutez les données au PieChart
    pieChart.setData(pieChartData);

    // Vous pouvez également personnaliser le PieChart ici
    pieChart.setTitle("Répartition des sexes");
    }    

    @FXML
    private void btnrtrusr(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
            retouirhme.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
