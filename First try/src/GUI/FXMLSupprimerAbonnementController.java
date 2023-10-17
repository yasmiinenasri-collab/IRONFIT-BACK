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

public class FXMLSupprimerAbonnementController implements Initializable {
    @FXML
    private TextField idAbonnement; // Champ pour saisir l'ID de l'abonnement à supprimer
    @FXML
    private TextField idSalleDeSport; // Champ pour saisir l'ID de la salle de sport liée à l'abonnement
    @FXML
    private Button btnSupprimerAbonnement;
    @FXML
    private Button btnRetourSAB;
    @FXML
private void retourAction(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));   
        btnRetourSAB.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSupprimerAbonnement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesAbonnement sa = new ServicesAbonnement();

                try {
                    int idAbonnementValue = Integer.parseInt(idAbonnement.getText());
                    int idSalleDeSportValue = Integer.parseInt(idSalleDeSport.getText());

                    // Vérifiez d'abord si l'abonnement existe dans la base de données
                    if (abonnementExiste(idAbonnementValue, idSalleDeSportValue)) {
                        // Appelez la méthode de service pour supprimer l'abonnement
                        sa.supprimerAbonnement(idAbonnementValue, idSalleDeSportValue);

                        // Redirigez l'utilisateur vers une interface pour afficher les abonnements
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
                        idAbonnement.getScene().setRoot(root);
                    } else {
                        // Affichez un message d'erreur si l'abonnement n'existe pas
                        afficherAlerte("Abonnement non trouvé", "L'abonnement n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
                    // Gérez une erreur si l'ID n'est pas un nombre valide
                    afficherAlerte("Erreur de saisie", "Veuillez saisir des ID d'abonnement et de salle de sport valides.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLSupprimerAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Méthode pour vérifier si l'abonnement existe dans la base de données
   // Méthode pour vérifier si l'abonnement existe dans le service
private boolean abonnementExiste(int idAbonnement, int idSalleDeSport) {
    ServicesAbonnement sa = new ServicesAbonnement();

    // Remplacez ce code par la vérification réelle de l'existence de l'abonnement dans votre service
    // Vous devez adapter cette partie en fonction de la structure de votre service
    Abonnement abonnement = sa.getAbonnementById(idAbonnement);

    // Si l'abonnement n'est pas nul, il existe
    return (abonnement != null);
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
