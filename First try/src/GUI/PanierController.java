

package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import Models.Panier;
import Models.Product;
import services.ServicePanier;

import java.util.List;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


import java.util.List;



import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import Models.Product; // Make sure you import the correct Product class
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ServicePanier;

public class PanierController {

    @FXML
    private Button afficherPanierBtn;
     @FXML
private Button retourBtn;
     @FXML
    private Label totalPriceLabel; // You'll need a label to display the total price


    @FXML
    void handleAfficherPanierBtn() {

    }



    @FXML
    private ListView<Product> cartListView;

    // Inject your ServicePanier instance
    private ServicePanier servicePanier;

    public PanierController() {
        // Initialize your ServicePanier instance here
        servicePanier = new ServicePanier();
    }

    public void initialize() {
        // Call the viewCart method to retrieve the cart products
        List<Product> cartProducts = servicePanier.viewCart();
  retourBtn.setOnAction(e -> handleRetourBtn());
        // Populate the ListView with the cart products
        cartListView.getItems().setAll(cartProducts);
        double totalPrice = calculateTotalPrice(cartProducts);
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }
      public double calculateTotalPrice(List<Product> cartProducts) {
        double total = 0.0;
        for (Product product : cartProducts) {
            total += product.getPrice(); // Assuming Product has a getPrice() method
        }
        return total;
    }
       @FXML
    void handleRetourBtn() {
        try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ecomerce.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("ecomerce");

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

}