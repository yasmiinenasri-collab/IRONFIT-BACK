package GUI;

import Models.medecin;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import services.ServiceM;

public class FXMLafficherMEDcontroler implements Initializable {
@FXML
    private ListView<String> affichemed;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    ServiceM sm = new ServiceM();
    ArrayList<medecin> medecins;
   medecins = (ArrayList<medecin>) sm.getAll();
    ObservableList<String> medecinsEnChaine = FXCollections.observableArrayList();
       for (medecin medecin : medecins) {
            String medecinString  = "Id :" + medecin.getId() +",nomMED :" + medecin.getNomMED() +", prenomMED :" + medecin.getPrenomMED() + ", specialite : " +medecin.getSpecialite() +", adresse : " +medecin.getAdresse() + ", email :"+ medecin.getEmail() + ", tel:" + medecin.getTel();
            medecinsEnChaine.add(medecinString);
       }
          affichemed.setItems(medecinsEnChaine);
            }
}
