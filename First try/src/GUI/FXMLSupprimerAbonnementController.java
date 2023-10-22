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
    private TextField idAbonnement; 
    @FXML
    private TextField idSalleDeSport; 
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

                  
                    if (abonnementExiste(idAbonnementValue, idSalleDeSportValue)) {
              
                        sa.supprimerAbonnement(idAbonnementValue, idSalleDeSportValue);

                      
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
                        idAbonnement.getScene().setRoot(root);
                    } else {
         
                        afficherAlerte("Abonnement non trouvé", "L'abonnement n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
            
                    afficherAlerte("Erreur de saisie", "Veuillez saisir des ID d'abonnement et de salle de sport valides.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLSupprimerAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

   
private boolean abonnementExiste(int idAbonnement, int idSalleDeSport) {
    ServicesAbonnement sa = new ServicesAbonnement();

    
    Abonnement abonnement = sa.getAbonnementById(idAbonnement);

 
    return (abonnement != null);
}


    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
