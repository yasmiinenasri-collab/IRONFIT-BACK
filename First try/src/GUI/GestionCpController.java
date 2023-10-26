/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
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

public class GestionCpController implements Initializable {
  @FXML
    private Button btn_affichercp;

    @FXML
    private Button btn_ajoutercp;

    @FXML
    private Button btn_modifiercp;

    @FXML
    private Button btn_suppcp;
    /**
     * Initializes the controller class.
     */
    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) btn_ajoutercp.getScene().getWindow();
           
            
            // Set the new scene
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btn_ajoutercp.setOnAction(event -> loadFXML("AjouterCP.fxml"));
        btn_affichercp.setOnAction(event -> loadFXML("AfficherCP.fxml"));
        btn_suppcp.setOnAction(event -> loadFXML("ModifierCP.fxml"));
        btn_modifiercp.setOnAction(event -> loadFXML("Suppcp.fxml"));
        // TODO
    }    
    
}
