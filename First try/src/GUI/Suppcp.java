/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.CodePromo;
import Services.ServiceCP;

/**
 *
 * @author nasri
 */
public class Suppcp implements Initializable {
    
    @FXML
    private Label label;
     @FXML
    private Button btr8;
     @FXML
    private ListView lv_supp;
      @FXML
    private Button  bt_supp;    
    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) btr8.getScene().getWindow();
            
            // Set the new scene
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btr8.setOnAction(event -> loadFXML("InterfaceAdmin.fxml"));
        populateListView();
       bt_supp.setOnAction(new EventHandler<ActionEvent>() {
       
            @Override
            public void handle(ActionEvent event) {
            CodePromo selectedCP = (CodePromo) lv_supp.getSelectionModel().getSelectedItem();
        if (selectedCP != null) {
            // Utilisez votre service de suppression pour supprimer l'utilisateur sélectionné
            ServiceCP serviceCP = new ServiceCP();
            serviceCP.supprimercp(selectedCP);
            // Rafraîchissez la liste après la suppression
            lv_supp.getItems().remove(selectedCP);
        }
    }


});}
 public void populateListView() {
      ServiceCP serviceCP = new ServiceCP();

        // Get the list of CodePromo objects from the service
        List<CodePromo> CPList = serviceCP.getAllcp();

        // Convert the list to an observable list
        ObservableList<CodePromo> observableCPList = FXCollections.observableArrayList(CPList);

        // Set the observable list to the ListView
        lv_supp.setItems(observableCPList);
    }
}