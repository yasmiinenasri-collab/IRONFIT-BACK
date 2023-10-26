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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.SalleDeSport;
import Services.ServicesSalleDeSport;

public class FXMLModifierSalleDeSportController implements Initializable {
    @FXML
    private ComboBox<String> NomSalleDeSporTt; 
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField capacite;
    @FXML
    private TextField specialite;
    @FXML
    private Button btnModifierSS;
    @FXML
    private Button BoutonRetourMS;

    @FXML
    private void retourAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
            BoutonRetourMS.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();

        // Remplissez le ComboBox avec les noms des salles de sport
        NomSalleDeSporTt.getItems().addAll(ss.getAllSalleDeSportNames());

        btnModifierSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String selectedNom = NomSalleDeSporTt.getValue(); // Obtenez le nom sélectionné

                    if (nomSalleDeSportExiste(selectedNom)) {
                        SalleDeSport salleDeSport = ss.getOneSalleDeSport(selectedNom); // Obtenez la salle de sport par son nom

                        salleDeSport.setNom(nom.getText());
                        salleDeSport.setAdresse(adresse.getText());

                        try {
                            int capaciteValue = Integer.parseInt(capacite.getText());
                            salleDeSport.setCapacite(String.valueOf(capaciteValue));
                        } catch (NumberFormatException e) {
                            afficherAlerte("Erreur de saisie", "La capacité doit être un nombre entier.");
                            return;
                        }

                        salleDeSport.setSpecialite(specialite.getText());

                        ss.modifierSalleDeSport(salleDeSport);

                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheSalleDeSport.fxml"));
                        NomSalleDeSporTt.getScene().setRoot(root);
                    } else {
                        afficherAlerte("Nom non trouvé", "Le nom de la salle de sport n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
                    afficherAlerte("Erreur de saisie", "Veuillez sélectionner un nom de salle de sport valide.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLModifierSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean nomSalleDeSportExiste(String nom) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        SalleDeSport salleExistante = ss.getOneSalleDeSport(nom);

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
