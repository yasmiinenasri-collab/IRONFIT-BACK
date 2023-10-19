package GUI; 
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceM;
import Models.medecin;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class FXMLajouterMEDcontroler implements Initializable {
     @FXML
    private Label label;

    @FXML
    private TextField adressemedecin;

    @FXML
    private Button btnajoutermedecin;

    @FXML
    private TextField emailmedecin;

    @FXML
    private TextField nommedecin;

    @FXML
    private TextField prenommedecin;

    @FXML
    private TextField specialitemedecin;

    @FXML
    private TextField telmedecin;

    ServiceM serviceM = new ServiceM();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation de votre contrôleur
        ServiceM serviceM = new ServiceM();


    
   
        btnajoutermedecin.setOnAction(event->{
        String nom = nommedecin.getText();
        String prenom = prenommedecin.getText();
        String specialite = specialitemedecin.getText();
        String adresse = adressemedecin.getText();
        String email = emailmedecin.getText();
        String tel = telmedecin.getText();

        // Créer un objet medecin avec les données saisies
        medecin nouveauMedecin = new medecin(nom, prenom, specialite, adresse, email, tel);

        // Appeler la méthode d'ajout du service
        serviceM.ajouter(nouveauMedecin);
        label.setText("");

        // Afficher une boîte de dialogue pour confirmer l'ajout
        });
    }
}