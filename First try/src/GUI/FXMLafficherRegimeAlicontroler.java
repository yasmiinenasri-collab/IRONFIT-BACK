package GUI;

import entite.RegimeAli;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ServiceRA;



public class FXMLafficherRegimeAlicontroler implements Initializable {
     public Stage STAGE;
    public Scene SCENE;
    public Parent root;
       @FXML
    private Button btn_retour_L_Regime;
     @FXML
    private Button btn_modif_regime_liste;
      @FXML
    private Button btn_ajouter_regimeL;

       @FXML
    private Button btn_suprime_regimeL;
@FXML
    private ListView<RegimeAli> affichregime;
@Override
    public void initialize(URL location, ResourceBundle resources) {
    
    ServiceRA rg = new ServiceRA();
    ArrayList<RegimeAli> regimes;
   regimes = (ArrayList<RegimeAli>) rg.getAll();
    ObservableList<String> regimesEnChaine = FXCollections.observableArrayList();
       for (RegimeAli RegimeAli : regimes) {
            String regimesString  = "prixregime :" + RegimeAli.getPrixRegime() +
                    ",type :" + RegimeAli.getTypeRegime() +
                    ", nomMED :" +RegimeAli.getNomMED() + 
                    ", prenomMED : " + RegimeAli.getPrenomMED();
            regimesEnChaine.add(regimesString);
       }
           ServiceRA S=new ServiceRA();
        ObservableList<RegimeAli> planning = FXCollections.observableArrayList(S.getAll());
        affichregime.setItems(planning);
            }
      @FXML
    void btn_supprime_regimeL(ActionEvent event) {

        ServiceRA R = new ServiceRA();
        
        RegimeAli selectedRegime = affichregime.getSelectionModel().getSelectedItem();
        R.supprimer(selectedRegime.getId());
        affichregime.getItems().remove(selectedRegime);
        
    }
      @FXML
    void btn_ajout_regimeL(ActionEvent event)  {
         try {
        root = FXMLLoader.load(getClass().getResource("FXMLajouterRegimeAli.fxml"));
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    }    catch (IOException ex) {
             Logger.getLogger(FXMLafficherRegimeAlicontroler.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      @FXML
    void btn_modifier_r_listeR(ActionEvent event) {
        try {
        root = FXMLLoader.load(getClass().getResource("FXMLmodifierRegimeAli.fxml"));
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
      @FXML
    void btn_retour_liste_regime(ActionEvent event) {
         try {
        root = FXMLLoader.load(getClass().getResource("FXMLafficherMED.fxml")); // interface yassmine
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

}
