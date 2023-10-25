package GUI;

import entite.medecin;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import services.ServiceM;
//////////
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class FXMLafficherMEDcontroler implements Initializable {
    @FXML
    private ListView<medecin> affichemed;
    public Stage STAGE;
    public Scene SCENE;
    public Parent root;
    @FXML
    private Button btnretour_Aff_MED;
     @FXML
    private Button btn_modif_M_A;
     
    @FXML
    private Button btn_ajouter_med;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){ /*{
    
    ServiceM sm = new ServiceM();
    ArrayList<medecin> medecins;
   medecins = (ArrayList<medecin>) sm.getAll();
    ObservableList<String> medecinsEnChaine = FXCollections.observableArrayList();
       for (medecin medecin : medecins) {
            String medecinString  = "Id :" + medecin.getId() +",nomMED :" + medecin.getNomMED() +", prenomMED :" + medecin.getPrenomMED() + ", specialite : " +medecin.getSpecialite() +", adresse : " +medecin.getAdresse() + ", email :"+ medecin.getEmail() + ", tel:" + medecin.getTel();
            medecinsEnChaine.add(medecinString);
       }
          affichemed.setItems(medecinsEnChaine);*/
            //}
    
            
            ServiceM S=new ServiceM();
        ObservableList<medecin> planning = FXCollections.observableArrayList(S.getAll());
        affichemed.setItems(planning);
    }
    
    
        @FXML
    void btn_supp_affichage_med(ActionEvent event) {
        ServiceM sm = new ServiceM();
        
        medecin selectedmedecin = affichemed.getSelectionModel().getSelectedItem();
        sm.supprimer(selectedmedecin.getId());
        affichemed.getItems().remove(selectedmedecin);

    }
    @FXML
    void btn_ajouter_med(ActionEvent event) {
        
    try {
        root = FXMLLoader.load(getClass().getResource("FXMLajouterMED.fxml"));
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        }
        @FXML
    void btn_retour_delist_med(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLafficherRegimeAli.fxml")); // page yasmine 
            STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SCENE = new Scene(root);
            STAGE.setScene(SCENE);
            STAGE.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLajouterMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML
    void btn_modifier_MED_a(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLmodifierMED.fxml"));  
            STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SCENE = new Scene(root);
            STAGE.setScene(SCENE);
            STAGE.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLajouterMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }

   
    

