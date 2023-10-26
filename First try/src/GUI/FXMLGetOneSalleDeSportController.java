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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import models.SalleDeSport;
import Services.ServicesSalleDeSport;

public class FXMLGetOneSalleDeSportController implements Initializable {
    @FXML
    private ComboBox<String> rechercheParComboBox; // ComboBox pour choisir la méthode de recherche
    @FXML
    private TextField critereTextField; // Champ de texte pour saisir les critères de recherche
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
        // Ajoutez les options pour le ComboBox de méthode de recherche
        rechercheParComboBox.getItems().addAll("Par nom", "Par adresse", "Par capacité", "Par spécialité");

        btnGetOneSS.setOnAction(event -> {
            String methodeRecherche = rechercheParComboBox.getValue(); // Récupérer la méthode de recherche sélectionnée
            String critere = critereTextField.getText(); // Récupérer les critères de recherche saisis

            if (methodeRecherche != null && !methodeRecherche.isEmpty() && critere != null && !critere.isEmpty()) {
                // Effectuer la recherche en fonction de la méthode choisie
                ServicesSalleDeSport serviceSalleDeSport = new ServicesSalleDeSport();
                SalleDeSport salleExistante = null;

                switch (methodeRecherche) {
                    case "Par nom":
                        salleExistante = serviceSalleDeSport.getOneSalleDeSportByNom(critere);
                        break;
                    case "Par adresse":
                        salleExistante = serviceSalleDeSport.getOneSalleDeSportByAdresse(critere);
                        break;
                    case "Par capacité":
                        salleExistante = serviceSalleDeSport.getOneSalleDeSportByCapacite(critere);
                        break;
                    case "Par spécialité":
                        salleExistante = serviceSalleDeSport.getOneSalleDeSportBySpecialite(critere);
                        break;
                }

                if (salleExistante != null) {
                    // Affichez les détails de la salle de sport
                    resultText.setText("Nom: " + salleExistante.getNom() + "\n"
                            + "Adresse: " + salleExistante.getAdresse() + "\n"
                            + "Capacité: " + salleExistante.getCapacite() + "\n"
                            + "Spécialité: " + salleExistante.getSpecialite());
                } else {
                    afficherAlerte("Salle de sport non trouvée", "Aucune salle de sport trouvée avec ces critères.");
                }
            } else {
                afficherAlerte("Champs vides", "Veuillez sélectionner une méthode de recherche et saisir des critères.");
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
