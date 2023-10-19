package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceM;
import Models.medecin;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
public class FXMLmodifierMEDcontroler implements Initializable {
    @FXML
    private Label label;

    @FXML
    private TextField adressemedmodif;

    @FXML
    private Button btnmodifiermedecin;

    @FXML
    private TextField emailmedmodif;

    @FXML
    private TextField nommedmodif;

    @FXML
    private TextField prenommedmodif;

    @FXML
    private TextField specialitemedmodif;

    @FXML
    private TextField telmedmodif;

    private medecin medecinAModifier;
    ServiceM serviceM = new ServiceM();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnmodifiermedecin.setOnAction(event -> {
            if (medecinAModifier != null) {
                String nom = nommedmodif.getText();
                String prenom = prenommedmodif.getText();
                String specialite = specialitemedmodif.getText();
                String adresse = adressemedmodif.getText();
                String email = emailmedmodif.getText();
                String tel = telmedmodif.getText();

                // Mettre à jour les données du médecin
                medecinAModifier.setNomMED(nom);
                medecinAModifier.setPrenomMED(prenom);
                medecinAModifier.setSpecialite(specialite);
                medecinAModifier.setAdresse(adresse);
                medecinAModifier.setEmail(email);
                medecinAModifier.setTel(tel);

                // Appeler la méthode de modification du service
                serviceM.modifier(medecinAModifier);

                // Afficher un message de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setHeaderText(null);
                alert.setContentText("Le médecin a été modifié avec succès.");
                alert.showAndWait();
            }
        });
    }

    public void chargerMedecinAModifier(medecin medecin) {
        this.medecinAModifier = medecin;
        if (medecin != null) {
            nommedmodif.setText(medecin.getNomMED());
            prenommedmodif.setText(medecin.getPrenomMED());
            specialitemedmodif.setText(medecin.getSpecialite());
            adressemedmodif.setText(medecin.getAdresse());
            emailmedmodif.setText(medecin.getEmail());
            telmedmodif.setText(medecin.getTel());
        }
    }
}
