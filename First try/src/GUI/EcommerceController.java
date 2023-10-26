package GUI;
import Models.Panier;
import Models.Product;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ProductService;
import services.ServicePanier;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
//import com.itextpdf.text;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EcommerceController implements Initializable {
    @FXML
    private Button afficherBtn;
    
    
    
    @FXML
    private GridPane productGridPane;

    private ProductService productService = new ProductService();
    private ServicePanier servicePanier = new ServicePanier();
    private PanierController p=new PanierController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Product> products = productService.displayProducts();

    int col = 0;
    int row = 0;

    for (Product product : products) {
        String productInfo = product.getProductName() + " - $" + product.getPrice();
        Label nameLabel = new Label(productInfo);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(100); // Adjust the width as needed
        imageView.setFitHeight(100); // Adjust the height as needed

        // Convert the byte array to an Image and set it in the ImageView
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        imageView.setImage(image);

        Button chooseButton = new Button("Add to Cart");
        chooseButton.setOnAction(event -> handleChooseProduct(product));

        productGridPane.add(nameLabel, col, row);
        productGridPane.add(imageView, col, row + 1); // Display the image
        productGridPane.add(chooseButton, col, row + 2);

        col++;
        if (col == 3) {  // Display 3 products per row
            col = 0;
            row += 3;
        }
    }    }

    private void handleChooseProduct(Product product) {
        // Create a Panier object to represent the selected product in the cart
        Panier panier = new Panier(1, product); // 1 represents the quantity of the selected product

        // Add the product to the cart
        servicePanier.addProductToCart(panier);

        // You can add further logic here, such as displaying a confirmation message
        System.out.println("Product chosen and added to the cart: " + product.getProductName());
    }

    @FXML
    private void handleAfficherBtn() {
        try {
            // Create a new stage for the "PanierController" interface
            Stage panierStage = new Stage();

            // Load the "PanierController" interface (FXML) using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent panierRoot = loader.load();

            // Set up the scene with the loaded "PanierController" interface
            Scene panierScene = new Scene(panierRoot);

            // Set the window title (optional)
            panierStage.setTitle("Shopping Cart");

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
    public void generatePDF() {
    // Create a FileChooser to select the location to save the PDF
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File file = fileChooser.showSaveDialog(new Stage());

    if (file != null) {
        try {
            // Create a Document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Add content to the PDF
            List<Product> cartProducts = servicePanier.viewCart();
            for (Product product : cartProducts) {
                String productInfo = product.getProductName() + " - $" + product.getPrice()+"\n";
                
                document.add(new Paragraph(productInfo));
            }
            String productInf="prix total"+p.calculateTotalPrice(cartProducts);
            document.add(new Paragraph(productInf));
            document.close();

            // Optionally open the generated PDF
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }}
