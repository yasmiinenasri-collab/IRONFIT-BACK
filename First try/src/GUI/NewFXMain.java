package GUI;

import APImed.APIsms;
import entite.medecin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NewFXMain extends Application {
    
   @Override
    public void start(Stage primaryStage) throws IOException {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLinformationsupp.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheRegimeAliUSER.fxml"));
          // Parent root = FXMLLoader.load(getClass().getResource("FXMLafficherMEDUSER.fxml"));
          //   Parent root = FXMLLoader.load(getClass().getResource("FXMLmodifierRegimeAli.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("FXMLmodifierMED.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("FXMLafficherMED.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("FXMLajouterMED.fxml"));
          //  Parent root = FXMLLoader.load(getClass().getResource("FXMLajouterRegimeAli.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("FXMLafficherRegimeAli.fxml"));
         //  Parent root = FXMLLoader.load(getClass().getResource("FXMLstatMED.fxml")); // statistique 
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}