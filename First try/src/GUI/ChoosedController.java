/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Product;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayedi
 */

public class ChoosedController implements Initializable {
      @FXML
    private Button retourBtn;
    @FXML
    private Label descreption;

    @FXML
    private ImageView imgV;

    @FXML
    private Label prix;

    @FXML
    private Label productId;

    @FXML
    private Label productName;

    @FXML
    private Label quantiteInStock;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      retourBtn.setOnAction(e -> handleRetourBtn());    
        
    }    
     public void setProduct(Product p) {
       productId.setText(String.valueOf(p.getProductId()));
        productName.setText(p.getProductName());
         descreption.setText(p. getDescription());
        prix.setText(String.valueOf(p.getPrice()));
        quantiteInStock.setText(String.valueOf(p.getQuantityInStock()));

        Image img = new Image(new ByteArrayInputStream(p.getImage()));
        imgV.setImage(img);
     
}
  @FXML
    void handleRetourBtn() {
        try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("afficher");

        // Set the scene for the new stage and show it
        panierStage.setScene(panierScene);
        panierStage.show();

        // Close the current (Ecommerce) window if needed
        // ((Node) (event.getSource())).getScene().getWindow().hide();
    } catch (IOException ex) {
        ex.printStackTrace();
        // Handle the error gracefully
    }

    }}