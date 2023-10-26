/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author tlili
 */
public class FXMLnbreutilyasmineController implements Initializable {

    @FXML
    private Label lblNombreUtilisateurs;
    @FXML
    private Button retourhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser su = new ServiceUser();
  int nombreUtilisateurs = su.getNombreUtilisateurs();
     lblNombreUtilisateurs.setText("Ils nous ont fait confiance !\n" + nombreUtilisateurs);
      
    }    

    @FXML
    private void btn_retournbres(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
            retourhome.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
