package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FXMLTestController {

    @FXML
    private Button gestionab;

    @FXML
    private Button gestionss;
@FXML
private void retourAction110(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheAbonnement.fxml"));
        gestionab.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
@FXML
private void retourAction111(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAfficheSalleDeSport.fxml"));
        gestionss.getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
