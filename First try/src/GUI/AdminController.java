package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.EventHandler;
/**
 * FXML Controller class
 *
 * @author Ayedi
 */
public class AdminController implements Initializable {
     
    @FXML
    private Button afficheBtn;
    @FXML
    private Button ajouterBTN;

    @FXML
    private Button modifierBTN;

    @FXML
    private Button supprimerBTN;
     @FXML
    void handleAfficheBtn(ActionEvent event) {
 try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("ajouter");

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
    void handleAjouterBTN(ActionEvent event) {
         try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajout.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("ajouter");

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
    void handleModifierBTN(ActionEvent event) {
         try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("modifier");

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
    void handleSupprimerBTN(ActionEvent event) {
 try {
        // Create a new stage for the "PanierController" interface
        Stage panierStage = new Stage();

        // Load the "PanierController" interface (FXML) using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("delate.fxml"));
        Parent panierRoot = loader.load();

        // Set up the scene with the loaded "PanierController" interface
        Scene panierScene = new Scene(panierRoot);

        // Set the window title (optional)
        panierStage.setTitle("delate");

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ajouterBTN.setOnAction(event -> handleAjouterBTN(event));
        modifierBTN.setOnAction(event -> handleModifierBTN(event));
        supprimerBTN.setOnAction(event -> handleSupprimerBTN(event));
        // TODO
    }    
    
}