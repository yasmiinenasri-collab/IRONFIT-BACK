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

import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLSupprimerSalleDeSportController implements Initializable {
    @FXML
    private TextField idSalleDeSport; 
    @FXML
    private Button btnSupprimerSS;
    @FXML
    private Button btnRetourSSS;
    @FXML
private void retourAction(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));   
        btnRetourSSS.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSupprimerSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesSalleDeSport ss = new ServicesSalleDeSport();

                try {
                    int id = Integer.parseInt(idSalleDeSport.getText());

                    if (idSalleDeSportExiste(id)) {
           
                        ss.supprimerSalleDeSport(id);

 
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheSalleDeSport.fxml"));
                        idSalleDeSport.getScene().setRoot(root);
                    } else {
                        
                        afficherAlerte("ID non trouvé", "L'ID de la salle de sport n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
        
                    afficherAlerte("Erreur de saisie", "Veuillez saisir un ID de salle de sport valide.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLSupprimerSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    private boolean idSalleDeSportExiste(int id) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        SalleDeSport salleExistante = ss.getOneSalleDeSportById(id);

        return (salleExistante != null);
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
