package GUI;

import Models.Product;
import java.io.ByteArrayOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import services.ProductService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AjoutController implements Initializable {
    @FXML
    private Button chooseImageBtn;
    @FXML
    private ImageView imagev;
    @FXML
    private TextField nomAREA;
    @FXML
    private TextField descreptionAREA;
    @FXML
    private TextField prixAREA;
    @FXML
    private TextField stockAREA;

    @FXML
    private Button validerBtn;
    @FXML
    private Button annulerBtn;

    private File selectedFile; // Store the selected image file
    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productService = new ProductService();
        validerBtn.setOnAction(e -> handleValiderBtn());
        chooseImageBtn.setOnAction(e -> handleChooseImage());
        annulerBtn.setOnAction(e -> handleAnnulerBtn());
    }

 @FXML
private void handleValiderBtn() {
    String productName = nomAREA.getText();
    String description = descreptionAREA.getText();
    String priceText = prixAREA.getText();
    String stockText = stockAREA.getText();

    if (productName.isEmpty() || description.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
        showAlert("Error", "Please fill in all the required fields.");
    } else {
        try {
            double price = Double.parseDouble(priceText);
            int quantityInStock = Integer.parseInt(stockText);

            if (price <= 0 || quantityInStock < 0) {
                showAlert("Error", "Price must be positive, and stock quantity must be non-negative.");
            } else {
                if (selectedFile != null) {
                    try {
                        byte[] imageBytes = convertFileToByteArray(selectedFile);

                        Product product = new Product(productName, description, imageBytes, price, quantityInStock);
                        productService.addProduct(product, description, imageBytes);

                        showAlert("Success", "Product with image and description added successfully!");
                    } catch (IOException ex) {
                        showAlert("Error", "An error occurred while adding the product.");
                        Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    showAlert("Error", "Please choose an image file.");
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Price and stock quantity must be valid numbers.");
        }
    }
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imagev.setImage(image);
        }
    }

  private byte[] convertFileToByteArray(File file) throws IOException {
    byte[] imageBytes;
    try (FileInputStream fis = new FileInputStream(file);
         ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
        int read;
        byte[] buffer = new byte[1024];
        while ((read = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, read);
        }
        imageBytes = bos.toByteArray();
    }
    return imageBytes;
}

    @FXML
    private void handleAnnulerBtn() {
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
}
