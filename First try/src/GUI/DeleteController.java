package GUI;

import Models.Product;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ProductService;

public class DeleteController implements Initializable {
        @FXML
    private Button retourBtn;
    @FXML
    private ListView<Product> productListView;
   
    private ProductService productService; // You need to have a reference to your ProductService
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // Initialize your ProductService (you can do this in the constructor)
           retourBtn.setOnAction(e -> handleRetourBtn());
        productService = new ProductService();
        
        // Populate the ListView with products
        ObservableList<Product> products = FXCollections.observableArrayList(productService.displayProducts());
        productListView.setItems(products);
        
        // Set a click event handler for the ListView items
      productListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {  // Check for double-click
                Product selectedProduct = productListView.getSelectionModel().getSelectedItem();

                if (selectedProduct != null) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/choosed.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        ChoosedController controller = fxmlLoader.getController();
                        controller.setProduct(selectedProduct);

                        Stage stage = (Stage) productListView.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // Votre code existant pour deleteProduct

    
    
    // Delete button action (you can also handle deletion here)
    @FXML
    private void deleteProduct() {
       Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
    
    if (selectedProduct != null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");

        // Get the user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, delete the selected product
            productService.deleteProduct(selectedProduct.getProductId());
            // Refresh the ListView after deletion
            productListView.getItems().remove(selectedProduct);
        } else {
            // User clicked Cancel or closed the dialog, do nothing
        }
    }
    }
   
@FXML
private void handle_affiche(MouseEvent event) {
    if (event.getClickCount() == 2) {  // Check for double-click
        // Get the selected product
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();

        // Check if a product is selected
        if (selectedProduct != null) {
            try {
                // Load the FXML file for the ChoosedController
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosed.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ChoosedController controller = fxmlLoader.getController();

                // Set the selected product in the ChoosedController
                controller.setProduct(selectedProduct);

                // Create a new stage for the ChoosedController interface
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}}


