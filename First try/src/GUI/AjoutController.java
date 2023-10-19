package GUI;

import Models.Product;
import java.io.IOException;
import services.ProductService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class AjoutController implements Initializable {

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

    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productService = new ProductService();
        validerBtn.setOnAction(e -> handleValiderBtn());
       // annulerBtn.setOnAction(e -> handleAnnulerBtn());
    }

    @FXML
    private void handleValiderBtn() {
        String productName = nomAREA.getText();
        String description = descreptionAREA.getText();
        double price = Double.parseDouble(prixAREA.getText());
        int quantityInStock = Integer.parseInt(stockAREA.getText());

        Product product = new Product(productName, description, price, quantityInStock);
        try{
        productService.addProduct(product);
         Parent root= FXMLLoader.load(getClass().getResource("ajout.fxml"));
            nomAREA.getScene().setRoot(root);
        }catch (IOException ex) {
                   Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
    }

 /*   @FXML
    private void handleAnnulerBtn() {
        nomAREA.clear();
        descreptionAREA.clear();
        prixAREA.clear();
        stockAREA.clear();*/
    }
}