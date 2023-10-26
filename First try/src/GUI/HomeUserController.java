/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.SessionManager;

/**
 * FXML Controller class
 *
 * @author tlili
 */
public class HomeUserController implements Initializable {

    @FXML
    private Button dc_btn;
    @FXML
    private ImageView profile_btn;
    @FXML
    private Button gestion_abonnement;
    @FXML
    private Button bar;
    @FXML
    private Button sexe;
    @FXML
    private Button gonbre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btngestionabn(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
            gestion_abonnement.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnbarcharttt(ActionEvent event) {
      try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLBarChartTaux.fxml"));
            bar.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    private void go_profile(MouseEvent event) {
    
               // showContent("ProfileUser.fxml");
                try {
    Parent root = FXMLLoader.load(getClass().getResource("ProfileUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    @FXML
    private void Se_deconnecter(ActionEvent event) {
         SessionManager.getInstance().setCurrentUser(null);
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("connexionUser.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void btnrepsexe(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLpiechartyasmine.fxml"));
            sexe.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btngonbre(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLnbreutilyasmine.fxml"));
            gonbre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
