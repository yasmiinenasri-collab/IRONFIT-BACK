package GUI;

import entite.medecin;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ServiceM;




public class FXMLafficherMEDUSERController implements Initializable {
    @FXML
    private ListView<medecin> affichemedUSER;
    public Stage STAGE;
    public Scene SCENE;
    public Parent root;

   
    
    @Override
    public void initialize(URL location, ResourceBundle resources){ 
    
            
            ServiceM S=new ServiceM();
        ObservableList<medecin> planning = FXCollections.observableArrayList(S.getAll());
        affichemedUSER.setItems(planning);
    }
      @FXML
    void btn_RE_listMuser(ActionEvent event) {
try {
        root = FXMLLoader.load(getClass().getResource("FXMLajouterMED.fxml"));// interface de yassmine 
        STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        SCENE= new Scene(root);
        STAGE.setScene(SCENE);
        STAGE.show();
    } catch (IOException ex) {
        Logger.getLogger(FXMLafficherMEDcontroler.class.getName()).log(Level.SEVERE, null, ex);
    }
    
      
    }
    }
  
  
        
      

    
    