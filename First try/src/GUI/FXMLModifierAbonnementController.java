package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Abonnement;
import Services.ServicesAbonnement;

public class FXMLModifierAbonnementController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTypeAbonnement;
    @FXML
    private TextField type;
    @FXML
    private DatePicker datedebut;  // Remplacé par DatePicker
    @FXML
    private DatePicker datefin;   // Remplacé par DatePicker
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
        ServicesAbonnement servicesAbonnements = new ServicesAbonnement();

        // Utilisez la méthode getTypesAbonnements pour obtenir la liste des types d'abonnements
        comboBoxTypeAbonnement.getItems().addAll(servicesAbonnements.getTypesAbonnements());

        btnModifierAbonnement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesAbonnement sa = new ServicesAbonnement();

                try {
                    String typeAbonnement = comboBoxTypeAbonnement.getValue();

                    if (abonnementExiste(typeAbonnement)) {
                        Abonnement abonnement = sa.getAbonnementByType(typeAbonnement);

                        abonnement.setType(type.getText());

                        LocalDate dateDebut = datedebut.getValue();
                        LocalDate dateFin = datefin.getValue();

                        if (dateDebut == null || dateFin == null) {
                            afficherAlerte("Champs de date vides", "Veuillez sélectionner la date de début et la date de fin.");
                            return;
                        }

                        abonnement.setDateDebut(dateDebut.toString());
                        abonnement.setDateFin(dateFin.toString());

                        abonnement.setPrix(Double.parseDouble(prix.getText()));

                        sa.modifierAbonnement(abonnement);

                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
                        btnModifierAbonnement.getScene().setRoot(root);
                    } else {

                        afficherAlerte("Type d'abonnement non trouvé", "Le type d'abonnement sélectionné n'existe pas dans la base de données.");
                    }
                } catch (Exception ex) {
                    afficherAlerte("Erreur de saisie", "Veuillez sélectionner un type d'abonnement valide.");
                }
            }
        });
    }

    private boolean abonnementExiste(String typeAbonnement) {
        ServicesAbonnement sa = new ServicesAbonnement();

        // Utilisez la méthode getAbonnementByType ou toute autre méthode appropriée pour vérifier l'existence du type d'abonnement
        Abonnement abonnementExistant = sa.getAbonnementByType(typeAbonnement);

        return (abonnementExistant != null);
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
