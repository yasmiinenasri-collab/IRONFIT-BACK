package GUI ; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import entite.medecin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceM;

public class FXMLmodifierMEDController implements Initializable {
    public Stage STAGE;
    public Scene SCENE;
    public Parent root;
    @FXML
    private TextField nomMEDmodif;
    @FXML
    private Button btn_retour_M_M;
    @FXML
    private TextField prenomMEDmodif;
    @FXML
    private TextField specialiteMEDmodif;
    @FXML
    private TextField adresseMEDmodif;
    @FXML
    private TextField emailMEDmodif;
    @FXML
    private TextField telMEDmodif;
    @FXML
    private Button btn_ModifierMed; // btn_medModif

    @FXML
    /*private void retourAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheMedecin.fxml"));
            boutonRetourMed.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceM serviceM = new ServiceM();

        btn_ModifierMed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nomMED = nomMEDmodif.getText();
                    String prenomMED = prenomMEDmodif.getText();
                    String specialite = specialiteMEDmodif.getText();
                    String adresse = adresseMEDmodif.getText();
                    String email = emailMEDmodif.getText();
                    String tel = telMEDmodif.getText();

                    // Recherchez le médecin par nom et prénom
                    int idMedecin = serviceM.trouverIdMedecin(nomMED, prenomMED);

                    if (idMedecin != -1) {
                        medecin medecinModifie = new medecin(idMedecin, nomMED, prenomMED, specialite, adresse, email, tel);
                        serviceM.modifier(medecinModifie);

                        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficherMED.fxml"));
                        nomMEDmodif.getScene().setRoot(root);
                    } else {
                        afficherAlerte("Médecin non trouvé", "Le médecin n'existe pas dans la base de données.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FXMLmodifierMEDController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    @FXML
    void btn_retour_modif_med(ActionEvent event) {
        try {
        root = FXMLLoader.load(getClass().getResource("FXMLafficherMED.fxml"));
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}