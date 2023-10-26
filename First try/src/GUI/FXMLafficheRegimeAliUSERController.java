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


public class FXMLafficheRegimeAliUSERController implements Initializable {
     public Stage STAGE;
    public Scene SCENE;
    public Parent root;
      @FXML
    private Button btn_retour_L_R_U;

      
@FXML
    private ListView<RegimeAli> listeREGIMEU;
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
        listeREGIMEU.setItems(planning);
            }
     
    
      @FXML
    void btnRetour_listeR_user(ActionEvent event)  {
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
}
