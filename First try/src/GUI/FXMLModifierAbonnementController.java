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

public class FXMLModifierAbonnementController implements Initializable {

    @FXML
    private TextField idAbonnement;
    @FXML
    private TextField type;
    @FXML
    private TextField datedebut;
    @FXML
    private TextField datefin;
    @FXML
    private TextField prix;
    @FXML
    private Button btnModifierAbonnement;
    @FXML
    private Button btnRetourMAB;

    @FXML
    private void retourAction12(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));   
            btnRetourMAB.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModifierAbonnement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesAbonnement sa = new ServicesAbonnement();

                try {
                    int id = Integer.parseInt(idAbonnement.getText());

                    // Vérifiez d'abord si l'ID existe dans la base de données
                    if (abonnementExiste(id)) {
                        Abonnement abonnement = sa.getAbonnementById(id);
                        // Mettez à jour les informations de l'abonnement
                        abonnement.setType(type.getText());
                        
                        // Vérifiez que les dates sont au format "jj-mm-yyyy"
                        String dateDebut = datedebut.getText();
                        String dateFin = datefin.getText();
                        if (!estFormatDateValide(dateDebut) || !estFormatDateValide(dateFin)) {
                            afficherAlerte("Format de date invalide", "Le format de date doit être jj-mm-yyyy.");
                            return;
                        }

                        // Vérifiez que les jours, mois et années sont dans les limites spécifiées
                        if (!estDateValide(dateDebut) || !estDateValide(dateFin)) {
                            afficherAlerte("Date non valide", "Les jours doivent être de 1 à 31, les mois de 1 à 12 et l'année jusqu'à 2026.");
                            return;
                        }

                        abonnement.setDateDebut(dateDebut);
                        abonnement.setDateFin(dateFin);
                        
                        abonnement.setPrix(Double.parseDouble(prix.getText()));

                        // Appelez la méthode de service pour modifier l'abonnement
                        sa.modifierAbonnement(abonnement);

                        // Redirigez l'utilisateur vers une interface pour afficher les abonnements
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
                        idAbonnement.getScene().setRoot(root);
                    } else {
                        // Affichez un message d'erreur si l'ID n'existe pas
                        afficherAlerte("ID non trouvé", "L'ID de l'abonnement n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
                    // Gérez une erreur si l'ID n'est pas un nombre valide
                    afficherAlerte("Erreur de saisie", "Veuillez saisir un ID d'abonnement valide.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLModifierAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean abonnementExiste(int id) {
        ServicesAbonnement sa = new ServicesAbonnement();
        Abonnement abonnementExistant = sa.getAbonnementById(id);
        return (abonnementExistant != null);
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
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
}
