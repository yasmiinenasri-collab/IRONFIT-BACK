package GUI;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Abonnement;
import services.ServicesAbonnement;

public class FXMLAfficheAbonnementController implements Initializable {
    @FXML
    private ListView<String> listViewAbonnements;
    @FXML
    private Button btnGOAJOUTAB;
    @FXML
    private Button btnGOMODIFAB;
    @FXML
    private Button btnGOSUPPRIMAB;
    
    @FXML
    private Button btnretourtest;
    @FXML
    private void retourAction1200(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLTest.fxml"));   
            btnretourtest.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Label statistiqueLabel;
    private int nombreTotalAbonnements = 0;
    private ArrayList<Abonnement> abonnements;

    @FXML
    private void retourAction15(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLModifierAbonnement.fxml"));
            btnGOMODIFAB.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retourAction16(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAjouterAbonnement.fxml"));
            btnGOAJOUTAB.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServicesAbonnement sa = new ServicesAbonnement();
        abonnements = (ArrayList<Abonnement>) sa.afficherAbonnement();

        // Créez une liste observable d'abonnements sous forme de chaînes
        ObservableList<String> abonnementsEnChaine = FXCollections.observableArrayList();
        for (Abonnement abonnement : abonnements) {
            String abonnementString = "Type : " + abonnement.getType() + 
                ", Date de début : " + abonnement.getDateDebut() + 
                ", Date de fin : " + abonnement.getDateFin() + 
                ", Prix : " + abonnement.getPrix();
            abonnementsEnChaine.add(abonnementString);
        }
        nombreTotalAbonnements = abonnements.size();
        mettreAJourStatistique();

        listViewAbonnements.setItems(abonnementsEnChaine);
        // Associez un gestionnaire d'événements pour la sélection d'éléments
        listViewAbonnements.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Activez le bouton "Supprimer" lorsque quelque chose est sélectionné
                btnGOSUPPRIMAB.setDisable(false);
            } else {
                // Désactivez le bouton "Supprimer" lorsque rien n'est sélectionné
                btnGOSUPPRIMAB.setDisable(true);
            }
        });
    }

    private void mettreAJourStatistique() {
        statistiqueLabel.setText("Nombre total des Abonnements : " + nombreTotalAbonnements);
    }

    @FXML
    private void supprimerAbonnement(ActionEvent event) {
        int selectedIndex = listViewAbonnements.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedAbonnementString = listViewAbonnements.getSelectionModel().getSelectedItem();

            if (selectedAbonnementString != null) {
                // Divisez la chaîne pour obtenir les parties pertinentes
                String[] parts = selectedAbonnementString.split(",");
            
                // Déclarez des variables pour stocker le type et le prix
                String type = null;
                double prix = 0.0;

                // Parcourez les parties pour extraire le type et le prix
                for (String part : parts) {
                    part = part.trim();
                    if (part.startsWith("Type : ")) {
                        type = part.substring(7); // Le type commence après "Type : "
                    } else if (part.startsWith("Prix : ")) {
                        String prixString = part.substring(6); // Le prix commence après "Prix : "
                        prix = Double.parseDouble(prixString.trim());
                    }
                }
            
                if (type != null) {
                    // Supprimez l'abonnement en utilisant le type et le prix
                    ServicesAbonnement serviceAbonnement = new ServicesAbonnement();
                    serviceAbonnement.supprimerAbonnementParTypeEtPrix(type, prix);

                    // Mettez à jour la liste d'abonnements
                    abonnements.remove(selectedIndex);

                    // Mettez à jour le ListView
                    ObservableList<String> abonnementsEnChaine = FXCollections.observableArrayList();
                    for (Abonnement abonnement : abonnements) {
                        String abonnementString = "Type : " + abonnement.getType() + 
                            ", Date de début : " + abonnement.getDateDebut() + 
                            ", Date de fin : " + abonnement.getDateFin() + 
                            ", Prix : " + abonnement.getPrix();
                        abonnementsEnChaine.add(abonnementString);
                    }
                    listViewAbonnements.setItems(abonnementsEnChaine);

                    // Réduisez le nombre total d'abonnements
                    nombreTotalAbonnements--;
                    mettreAJourStatistique();
                }
            }
        }
    }
}
