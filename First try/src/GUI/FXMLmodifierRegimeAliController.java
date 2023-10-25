package GUI ; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entite.RegimeAli;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceM;
import services.ServiceRA;

public class FXMLmodifierRegimeAliController {
    public Stage STAGE;
    public Scene SCENE;
    public Parent root;
    
     @FXML
    private Button btn_retour_M_R;

    @FXML
    private Button btn_modif_R;

    @FXML
    private TextField id_R;

    @FXML
    private TextField nom_m_R;

    @FXML
    private TextField prenom_m_R;

    @FXML
    private TextField prix_R;

    @FXML
    private TextField type_R;

    @FXML
    void btn_modifier_regime(ActionEvent event) {
        try {
            int regimeId = Integer.parseInt(id_R.getText());
            String nomMedecin = nom_m_R.getText();
            String prenomMedecin = prenom_m_R.getText();
            double prix = Double.parseDouble(prix_R.getText());
            String type = type_R.getText();

            // Check if the regime ID is valid (greater than 0)
            if (regimeId <= 0) {
                showAlert("ID du régime invalide", "L'ID du régime doit être un entier positif.");
                return; // Exit the method
            }

            // Check if the price is valid (greater than or equal to 100)
            if (prix > 100) {
                showAlert("Prix invalide", "Le prix doit être inferieur à 100.");
                return; // Exit the method
            }

            // Find the associated medical professional's ID
            ServiceM serviceM = new ServiceM();
            int medecinId = serviceM.trouverIdMedecin(nomMedecin, prenomMedecin);

            if (medecinId == -1) {
                showAlert("Médecin non trouvé", "Le médecin associé n'a pas été trouvé.");
                return; // Exit the method
            }

            // Create a new RegimeAli object with the updated values
            RegimeAli updatedRegime = new RegimeAli(regimeId, prix, type, nomMedecin, prenomMedecin, medecinId);

            // Use the ServiceRA to modify the regime
            ServiceRA serviceRA = new ServiceRA();
            serviceRA.modifier(updatedRegime);

            // Show a success message to the user
            showAlert("Modification réussie", "Le régime a été modifié avec succès.");
        } catch (NumberFormatException e) {
            showAlert("Données incorrectes", "Vérifiez les données entrées.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
     @FXML
    void btn_retour_modif_regime(ActionEvent event) {
        try {
        root = FXMLLoader.load(getClass().getResource("FXMLafficherRegimeAli.fxml"));
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
