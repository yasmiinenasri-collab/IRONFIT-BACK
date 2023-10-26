package GUI;

import entite.RegimeAli;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceM;
import services.ServiceRA;
import util.DataSource;

public class FXMLajouterRegimeAlicontroler {
    @FXML
    private Button btnajouterRegimeAli;

    @FXML
    private TextField nomMED;

    @FXML
    private TextField prenomMed;

    @FXML
    private TextField prixRegime;

    @FXML
    private TextField typeRegime;

    @FXML
    private Label label;

    @FXML
    private Button retour_ajout_regime;

    public Stage STAGE;
    public Scene SCENE;
    public Parent root;

    // Initialize the connection and services in the initialize method
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your controller
    }

    @FXML
    private void ADD(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceRA serviceRA = new ServiceRA();
        String prixText = prixRegime.getText();
        String nommed = nomMED.getText();
        String prenommed = prenomMed.getText();
        String type = typeRegime.getText();

        try {
            double prix = Double.parseDouble(prixText);

            if (prix < 100) {
                // Le prix est supérieur à 100, créer un nouvel objet RegimeAli
                RegimeAli nouveauRegimeAli = new RegimeAli(prix, type, nommed, prenommed, -1);

                // Importer l'ID du médecin
                ServiceM service = new ServiceM();
                int idMedecinAutomatique = service.trouverIdMedecin(nommed, prenommed);
                System.out.println(idMedecinAutomatique);

                if (idMedecinAutomatique != -1) {
                    nouveauRegimeAli.setIdMED(idMedecinAutomatique);
                    // Appeler la méthode d'ajout du service
                    serviceRA.ajouter(nouveauRegimeAli);
                    label.setText("Régime alimentaire ajouté avec succès.");
                    // Afficher une boîte de dialogue pour confirmer l'ajout
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Régime alimentaire ajouté avec succès.");
                    alert.showAndWait();
                } else {
                    System.out.println("Médecin non trouvé. Impossible d'ajouter le régime alimentaire.");
                    // Afficher une alerte à l'utilisateur
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Médecin non trouvé. Impossible d'ajouter le régime alimentaire.");
                    alert.showAndWait();
                }
            } else {
                System.out.println("Le prix doit être inferieur à 100DT.");
                // Afficher une alerte à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le prix doit être supérieur à 100.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            System.out.println("Le prix n'est pas un nombre valide.");
            // Afficher une alerte à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix n'est pas un nombre valide.");
            alert.showAndWait();
        }
    }

    @FXML
    void retour_regime(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLafficherRegimeAli.fxml"));
            STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SCENE = new Scene(root);
            STAGE.setScene(SCENE);
            STAGE.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLajouterRegimeAlicontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

