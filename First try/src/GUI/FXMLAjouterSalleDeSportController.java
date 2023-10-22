package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLAjouterSalleDeSportController implements Initializable {
    @FXML
    private TextField adresse;
    @FXML
    private Button btnAjouterSS;
    @FXML
    private TextField capacite;
    @FXML
    private TextField nom;
    @FXML
    private TextField specialite;
    @FXML
    private Button boutonRetourSS;

    @FXML
    private void retourAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
            boutonRetourSS.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAjouterSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesSalleDeSport ss = new ServicesSalleDeSport();

                String nomSalle = nom.getText();
                String adresseSalle = adresse.getText();
                String capaciteSalle = capacite.getText();
                String specialiteSalle = specialite.getText();

                if (capaciteSalle.matches("\\d+")) {
                    SalleDeSport salleDeSport = new SalleDeSport(nomSalle, adresseSalle, capaciteSalle, specialiteSalle);
                    try {
                        ss.ajouterSalleDeSport(salleDeSport);

                        // Enregistrez toutes les informations de la salle ajoutée pour le code QR
                        String informationsSalle = "Nom :"+ nomSalle + "\nAdresse :"+ adresseSalle  + "\nCapacite :"+ capaciteSalle  + "\nSpecialite :"+ specialiteSalle;
                        FXMLAfficheSalleDeSportController.setInfosSalle(informationsSalle);

                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
                        nom.getScene().setRoot(root);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("La capacité doit être un nombre entier.");
                    alert.showAndWait();
                }
            }
        });
    }
}
