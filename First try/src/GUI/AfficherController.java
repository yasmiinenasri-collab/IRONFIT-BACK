/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Product;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author Ayedi
 */
public class AfficherController implements Initializable {
    
    @FXML
    private ListView<Product> productListView;
    
    @FXML
    private Button displayBtn;
    @FXML
    private Button retourBtn;
    private ProductService productService; 
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOMouseEvent event;
    
        retourBtn.setOnAction(e -> handleRetourBtn());
      

        productService = new ProductService();
        
        // Populate the ListView with products
        ObservableList<Product> products = FXCollections.observableArrayList(productService.displayProducts());
        productListView.setItems(products);
    }    
       
    @FXML
    void handleRetourBtn() {
        try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("admin");

        // Set the scene for the new stage and show it
        panierStage.setScene(panierScene);
        panierStage.show();

        // Close the current (Ecommerce) window if needed
        // ((Node) (event.getSource())).getScene().getWindow().hide();
    } catch (IOException ex) {
        ex.printStackTrace();
        // Handle the error gracefully
    }

    }
 @FXML
void handleDisplayBtn(ActionEvent event) {
    // Get the selected product from the ListView
    Product selectedProduct = productListView.getSelectionModel().getSelectedItem();

    if (selectedProduct != null) {
        try {
            // Create a new stage for the "ChoosedController" interface
            Stage choosedStage = new Stage();

            // Load the "choosed.fxml" interface (FXML) using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("choosed.fxml"));
            Parent choosedRoot = loader.load();

            // Get the controller for the "choosed.fxml" interface
            ChoosedController choosedController = loader.getController();

            // Pass the selected product to the ChoosedController
            choosedController.setProduct(selectedProduct);

            // Set up the scene with the loaded "choosed.fxml" interface
            Scene choosedScene = new Scene(choosedRoot);

            // Set the window title (optional)
            choosedStage.setTitle("Product Details");

            // Set the scene for the new stage and show it
            choosedStage.setScene(choosedScene);
            choosedStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle the error gracefully
        }
    }
}

}




    

