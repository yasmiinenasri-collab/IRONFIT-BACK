package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Abonnement;
import services.ServicesAbonnement;
import services.ServicesSalleDeSport;

public class FXMLAjouterAbonnementController implements Initializable {

    @FXML
    private Button btnajouterab;

    @FXML
    private DatePicker datePickerDebut;

    @FXML
    private DatePicker datePickerFin;

    @FXML
    private TextField prix;

    @FXML
    private TextField type;

    @FXML
    private ComboBox<String> salleDeSportCombo;

    @FXML
    private Button boutonRetour;

    @FXML
    private void retourAction13(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
            boutonRetour.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesSalleDeSport serviceSalleDeSport = new ServicesSalleDeSport();
        salleDeSportCombo.getItems().addAll(serviceSalleDeSport.getAllSalleDeSportNames());

        btnajouterab.setOnAction(event -> {
    ServicesAbonnement ab = new ServicesAbonnement();

    LocalDate dateDebutAbonnement = datePickerDebut.getValue();
    LocalDate dateFinAbonnement = datePickerFin.getValue();

    if (dateDebutAbonnement == null || dateFinAbonnement == null) {
        afficherAlerte("Champs de date vides", "Veuillez sélectionner la date de début et la date de fin.");
        return;
    }

    double prixAbonnement = Double.parseDouble(prix.getText());
    String typeAbonnement = type.getText();

    String nomSalleDeSport = salleDeSportCombo.getValue();

    int idSalleDeSport = serviceSalleDeSport.getIdSalleDeSportByNom(nomSalleDeSport);

    if (idSalleDeSport != -1) {
        Abonnement abonnement = new Abonnement(typeAbonnement, dateDebutAbonnement.toString(), dateFinAbonnement.toString(), prixAbonnement);
        abonnement.setIdSalleDeSport(idSalleDeSport);

        try {
            ab.ajouterAbonnement(abonnement, nomSalleDeSport);
            Parent root;
            root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
            type.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        afficherAlerte("Salle de sport non trouvée", "Salle de sport '" + nomSalleDeSport + "' non trouvée.");
    }
});
    }
    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
