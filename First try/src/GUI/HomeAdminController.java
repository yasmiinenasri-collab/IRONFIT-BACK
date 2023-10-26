/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nasri
 */
public class HomeAdminController implements Initializable {
   
           private Button btn_codep;
           private Button btn_utilisateur;
    @FXML
    private Button gogestsallle;
   private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) btn_utilisateur.getScene().getWindow();
           
            
            // Set the new scene
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        btn_codep.setOnAction(event -> loadFXML("GestionCp.fxml"));
        btn_utilisateur.setOnAction(event -> loadFXML("GestionAdmin.fxml"));
        gogestsallle.setOnAction(event -> loadFXML("FXMLAfficheSalleDeSport.fxml"));
    }    

    @FXML
    private void btngosalle(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
            gogestsallle.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
