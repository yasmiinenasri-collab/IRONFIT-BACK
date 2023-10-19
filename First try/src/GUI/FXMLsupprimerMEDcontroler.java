package GUI;

import Models.medecin;
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
import services.ServiceM;

public class FXMLsupprimerMEDcontroler implements Initializable {

    @FXML
    private Button btnsupprimermed;

    @FXML
    private TextField idmedsupprimer;
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnsupprimermed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             ServiceM sm = new ServiceM();
                try {
                    int id = Integer.parseInt(idmedsupprimer.getText());
                      // Vérifiez d'abord si l'ID existe dans la base de données
                    if (idmedsupprimerExiste(id)) {
                        // Appelez la méthode de service pour supprimer la salle de sport
                        sm.supprimer(id);
                         // Redirigez l'utilisateur vers une interface pour afficher les salles de sport
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficherMED.fxml"));
                        idmedsupprimer.getScene().setRoot(root);
                        } else {
                        // Affichez un message d'erreur si l'ID n'existe pas
                        afficherAlerte("ID non trouvé", "L'ID medecin de sport n'existe pas dans la base de données.");
                    }
           } catch (NumberFormatException ex) {
                    // Gérez une erreur si l'ID n'est pas un nombre valide
                    afficherAlerte("Erreur de saisie", "Veuillez saisir un ID de salle de sport valide.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLsupprimerMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Méthode pour vérifier si l'ID de salle de sport existe dans la base de données
    private boolean idmedsupprimerExiste(int id) {
        ServiceM ss = new ServiceM();
        medecin salleExistante = ss.getOneMedecinById(id);

        return (salleExistante != null);
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