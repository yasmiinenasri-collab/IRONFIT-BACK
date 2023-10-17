package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Abonnement;
import services.ServicesAbonnement;

public class FXMLAjouterAbonnementController implements Initializable {

    @FXML
    private Button btnajouterab;

    @FXML
    private TextField datedebut;

    @FXML
    private TextField datefin;

    @FXML
    private TextField prix;

    @FXML
    private TextField type;

    @FXML
    private TextField salleDeSportNom;

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
        btnajouterab.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesAbonnement ab = new ServicesAbonnement();

                // Vérifiez d'abord que les champs de date de début et de date de fin ne sont pas vides
                String dateDebutAbonnement = datedebut.getText();
                String dateFinAbonnement = datefin.getText();

                if (dateDebutAbonnement.isEmpty() || dateFinAbonnement.isEmpty()) {
                    afficherAlerte("Champs de date vides", "Veuillez remplir les champs de date de début et de date de fin.");
                    return;
                }

                // Vérifiez que les dates sont au format "jj-mm-yyyy"
                if (!estFormatDateValide(dateDebutAbonnement) || !estFormatDateValide(dateFinAbonnement)) {
                    afficherAlerte("Format de date invalide", "Le format de date doit être jj-mm-yyyy.");
                    return;
                }

                // Vérifiez que les jours, mois et années sont dans les limites spécifiées
                if (!estDateValide(dateDebutAbonnement) || !estDateValide(dateFinAbonnement)) {
                    afficherAlerte("Date non valide", "Les jours doivent être de 1 à 31, les mois de 1 à 12 et l'année jusqu'à 2026.");
                    return;
                }

                double prixAbonnement = Double.parseDouble(prix.getText());
                String typeAbonnement = type.getText();

                // Obtenez le nom de la salle de sport à partir de l'interface utilisateur (par exemple, un champ de texte)
                String nomSalleDeSport = salleDeSportNom.getText();

                // Utilisez la méthode pour obtenir l'ID de la salle de sport
                int idSalleDeSport = ab.getIdSalleDeSportByNom(nomSalleDeSport);

                // Assurez-vous que l'ID de la salle de sport n'est pas -1 (non trouvé)
                if (idSalleDeSport != -1) {
                    // Si l'ID de la salle de sport est trouvé, associez-le à l'abonnement
                    Abonnement abonnement = new Abonnement(typeAbonnement, dateDebutAbonnement, dateFinAbonnement, prixAbonnement);
                    abonnement.setIdSalleDeSport(idSalleDeSport);

                    try {
                        // Maintenant, ajoutez l'abonnement
                        ab.ajouterAbonnement(abonnement, nomSalleDeSport);
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheAbonnement.fxml"));
                        type.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAjouterAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Gérez le cas où le nom de la salle de sport n'est pas trouvé
                    afficherAlerte("Salle de sport non trouvée", "Salle de sport '" + nomSalleDeSport + "' non trouvée.");
                }
            }
        });
    }

    // Méthode pour vérifier le format de date "jj-mm-yyyy"
    private boolean estFormatDateValide(String date) {
        String pattern = "\\d{2}-\\d{2}-\\d{4}";
        return date.matches(pattern);
    }

    // Méthode pour vérifier que les jours, mois et années sont dans les limites spécifiées
    private boolean estDateValide(String date) {
        String[] partiesDate = date.split("-");
        int jour = Integer.parseInt(partiesDate[0]);
        int mois = Integer.parseInt(partiesDate[1]);
        int annee = Integer.parseInt(partiesDate[2]);

        return (jour >= 1 && jour <= 31 && mois >= 1 && mois <= 12 && annee <= 2026);
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
