package GUI;

import Models.Product;
import java.io.IOException;
import java.net.URL;
//import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ProductService;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;


public class ModifierController implements Initializable {
    @FXML
    private Button annulerBtn;

    @FXML
    private TextField idArea;

    @FXML
    private TextField prixAREA;

    @FXML
    private Button validerBtn;

    private ProductService productService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productService = new ProductService();
        validerBtn.setOnAction(e -> handleValiderBtn());
        annulerBtn.setOnAction(e -> handleAnnulerBtn());
    }

    @FXML
    void handleAnnulerBtn() {
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
    public void handleValiderBtn() {
      try {
        int productId = Integer.parseInt(idArea.getText());
        double newPrice = Double.parseDouble(prixAREA.getText());

        productService.modifyProductPrice(productId, newPrice);

        // Show a success alert
        showAlert("Success", "Price Modification", "Price of the product with productId " + productId + " modified successfully!");
    } catch (NumberFormatException e) {
        // Handle the error here if the input is not a valid number
        showAlert("Error", "Invalid Input", "Please enter valid numbers for ID and new price.");
    }
}
private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
    
  public void handleNotBtn(){
 Notifications notifs= Notifications.create()
                .title("Gestionnaire produit")
                .text("pas de notification")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_CENTER).onAction(new EventHandler<ActionEvent>(){
                    
                    
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("Clicked on notification");
                    
                    }
                    });
                notifs.show();
  
  
  
  
  }
}
