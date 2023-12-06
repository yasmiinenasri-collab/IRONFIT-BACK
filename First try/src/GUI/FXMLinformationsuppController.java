package GUI;

import APImed.APIsms;
import entite.medecin;
import java.io.IOException;
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

public class FXMLinformationsuppController {
      public Stage STAGE;
    public Scene SCENE;
    public Parent root;

    @FXML
    private TextField adressemedecin;

    @FXML
    private Button btn_retour_info_supp_acc;

    @FXML
    private Button btnajouterinfsupp;

    @FXML
    private TextField emailmedecin;

    @FXML
    private Label label;

    @FXML
    private TextField nommedecin;

    @FXML
    private TextField prenommedecin;

    @FXML
    private TextField specialitemedecin;

    @FXML
    private TextField telmedecin;
     ServiceM serviceM = new ServiceM();

    @FXML
    void btn_RInfoSuppAcc(ActionEvent event) {
 try {
            root = FXMLLoader.load(getClass().getResource("HomeMedecin.fxml"));
            STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SCENE = new Scene(root);
            STAGE.setScene(SCENE);
            STAGE.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLajouterMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnajouterinfsupp(ActionEvent event) {
        
            String nom = nommedecin.getText();
            String prenom = prenommedecin.getText();
            String specialite = specialitemedecin.getText();
            String adresse = adressemedecin.getText();
            String email = emailmedecin.getText();
            String tel = telmedecin.getText();

            if (tel.matches("\\d{8}") && email.endsWith("@gmail.com")) {
                // Les données sont valides, créer un nouvel objet médecin
                medecin nouveauMedecin = new medecin(nom, prenom, specialite, adresse, email, tel);

                // Appeler la méthode d'ajout du service
                serviceM.ajouter(nouveauMedecin);
                label.setText("Médecin ajouté avec succès.");

                // Afficher une boîte de dialogue pour confirmer l'ajout
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Médecin ajouté avec succès.");
                alert.showAndWait();
                    try {
            APIsms sm = new APIsms();
    medecin medecin = null;
            sm.sendSMS(medecin);
            System.out.println("SMS envoyé avec succès");
        } catch (Exception e) {
            // handle the exception here
            e.printStackTrace();
        }
    }
    
         
             else {
                // Afficher une alerte en cas de saisie incorrecte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Vérifiez le format du téléphone (8 chiffres) et de l'e-mail (doit être @gmail.com).");
                alert.showAndWait();
            }
        }
    }
    

