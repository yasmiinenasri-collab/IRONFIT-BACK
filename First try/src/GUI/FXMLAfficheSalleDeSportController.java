package GUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLAfficheSalleDeSportController implements Initializable {

    @FXML
    private ListView<String> listViewSalleDeSport;
    @FXML
    private Button btnGOAJOUT;
    @FXML
    private Button btnGOMODIF;
    @FXML
    private Button btnGOSUPPRIM; 
    @FXML
    private Button btnGOSEARCH;
@FXML
private void retourAction(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLModifierSalleDeSport.fxml"));
        btnGOMODIF.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
@FXML
private void retourAction1(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAjouterSalleDeSport.fxml"));
        
        btnGOAJOUT.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
@FXML
private void retourAction2(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSupprimerSalleDeSport.fxml"));
        
        btnGOSUPPRIM.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
@FXML
private void retourAction3(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGetOneSalleDeSport.fxml"));
        
        btnGOSEARCH.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        ArrayList<SalleDeSport> salleDeSports;
        salleDeSports = (ArrayList<SalleDeSport>) ss.getAllSalleDeSport();
        ObservableList<String> obs = FXCollections.observableArrayList();
        for (SalleDeSport salle : salleDeSports) {
            String SalleString = "Nom : " + salle.getNom() +
                ", adresse : " + salle.getAdresse() +
                ", capacite : " + salle.getCapacite() +
                ", specialite : " + salle.getSpecialite();
            obs.add(SalleString);
        }
        listViewSalleDeSport.setItems(obs);
    }
}
