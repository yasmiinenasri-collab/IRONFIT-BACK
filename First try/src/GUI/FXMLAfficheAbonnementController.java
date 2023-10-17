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
@FXML
private void retourAction17(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSupprimerAbonnement.fxml"));
        
        btnGOSUPPRIMAB.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServicesAbonnement sa = new ServicesAbonnement();
          ArrayList<Abonnement> abonnements;
        abonnements = (ArrayList<Abonnement>) sa.afficherAbonnement();

        // Créer une liste observable d'abonnements sous forme de chaînes
        ObservableList<String> abonnementsEnChaine = FXCollections.observableArrayList();
        for (Abonnement abonnement : abonnements) {
            String abonnementString = "Type : " + abonnement.getType() + 
                ", Date de début : " + abonnement.getDateDebut() + 
                ", Date de fin : " + abonnement.getDateFin() + 
                ", Prix : " + abonnement.getPrix();
            abonnementsEnChaine.add(abonnementString);
        }

        listViewAbonnements.setItems(abonnementsEnChaine);
    }
}



