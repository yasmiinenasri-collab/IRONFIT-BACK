package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLGetOneSalleDeSportController implements Initializable {
    @FXML
    private ComboBox<String> nomSalleDeSport; // ComboBox pour afficher les noms de salle de sport
    @FXML
    private Button btnGetOneSS;
    @FXML
    private Text resultText;
    @FXML
    private Button BoutonRetourGO;

    @FXML
    private void retourAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
            BoutonRetourGO.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        nomSalleDeSport.getItems().addAll(ss.getAllSalleDeSportNames());

        btnGetOneSS.setOnAction(event -> {
            String nom = nomSalleDeSport.getValue(); // Récupérer le nom sélectionné dans le ComboBox

            if (nom != null && !nom.isEmpty()) {
                // Recherchez la salle de sport par nom
                ServicesSalleDeSport serviceSalleDeSport = new ServicesSalleDeSport();
                SalleDeSport salleExistante = serviceSalleDeSport.getOneSalleDeSportByNom(nom);

                if (salleExistante != null) {
                    // Affichez les détails de la salle de sport
                    resultText.setText("Nom: " + salleExistante.getNom() + "\n"
                        + "Adresse: " + salleExistante.getAdresse() + "\n"
                        + "Capacité: " + salleExistante.getCapacite() + "\n"
                        + "Spécialité: " + salleExistante.getSpecialite());
                } else {
                    afficherAlerte("Salle de sport non trouvée", "Aucune salle de sport trouvée avec ce nom.");
                }
            } else {
                afficherAlerte("Champ vide", "Veuillez sélectionner un nom de salle de sport.");
            }
        });
    }

    // Méthode pour afficher une alerte
    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
