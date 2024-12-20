/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;;


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import models.User;
import Services.ServiceUser;


/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ProfileUserController implements Initializable {
    private BufferedImage originalImage;
    @FXML
    private Button btn_profile_desc;
    @FXML
    private Label label_us;
  @FXML
    private TextField tf_modif_ident;
    @FXML
    private TextField tf_modif_email;
    @FXML
    private PasswordField tf_modif_mdp;
    @FXML
    private PasswordField tf_modif_cfrmmdp;
    @FXML
    private TextField tf_modif_age;
    @FXML
    private ComboBox<String> cb_modif_rl;
    @FXML
    private ComboBox<String> cb_modif_sx;
    
    private String ImagePath;
     @FXML
    private Button btn_modif_modif;
      @FXML
    private Button btn_modif_retour;

        @FXML
        private Label imageLabel;
         @FXML
        private ImageView ImagePreviw;
         @FXML
        private Button Add_image_button;
         @FXML
        private MediaView MediaView;
        
        Thread webcamThread;
        @FXML
        private Button btnSnap;
        
       @FXML
        private Button btnCancel;
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
           ObservableList<String> list = FXCollections.observableArrayList("CLIENT","MEDECIN","COACH");
           cb_modif_rl.setItems(list);
           cb_modif_rl.setValue("CLIENT");
           
           ObservableList<String> listS = FXCollections.observableArrayList("FEMME","HOMME");
           cb_modif_sx.setItems(listS);
           cb_modif_sx.setValue("HOMME");
           
        
        ServiceUser su = new ServiceUser();
        User aold = su.readById(ConnexionUserController.id_modif);
        ImagePath = aold.getImage();
        
        ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
        tf_modif_ident.setText(aold.getUsername());
        label_us.setText("Mr "+aold.getUsername());
        tf_modif_email.setText(aold.getMail());
        tf_modif_mdp.setText(aold.getMdp());
        tf_modif_cfrmmdp.setText(aold.getMdp());
        tf_modif_age.setText(Integer.toString(aold.getAge()));
        cb_modif_rl.setValue(aold.getRole());
        cb_modif_sx.setValue(aold.getSexe());
        
       
            }
    
    @FXML
    private void enregistrer(ActionEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        User aold = su.readById(ConnexionUserController.id_modif);
        User u = new User();
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String mdp1 = tf_modif_mdp.getText();
        String mdp2 = tf_modif_cfrmmdp.getText();
        String age1 = tf_modif_age.getText();
        String role1 = cb_modif_rl.getSelectionModel().getSelectedItem();
        String sexe = cb_modif_sx.getSelectionModel().getSelectedItem();

            
        if (tf_modif_ident.getText().isEmpty() || tf_modif_email.getText().isEmpty() || mdp1.isEmpty() || 
            mdp2.isEmpty()  || 
            age1.isEmpty() ) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }else if (!tf_modif_email.getText().matches(emailRegex)) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Format email incorrect");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email valide !");
    alert.showAndWait();
    //return;
}else if ( !aold.getMail().equals(tf_modif_email.getText()) ) {
    if(su.checkEmailExists(tf_modif_email.getText())){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Email existe déjà");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email différent !");
    alert.showAndWait();}
   // return;
}else if (mdp1.length() < 8) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Attention");
    alert.setHeaderText(null);
    alert.setContentText("Le mot de passe doit avoir au moins 8 caractères.");
    alert.showAndWait();
    }   else if (!mdp1.matches(".*[A-Z].*") || !mdp1.matches(".*\\d.*")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule et un chiffre.");
        alert.showAndWait();
      //  return;
    } else if(!mdp1.matches(mdp2)){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("vous devez revoir votre mot de passe");
        alert.showAndWait();
    }else if(!age1.matches(".*\\d.*")){
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Format d'age invalide");
        alert.setHeaderText(null);
        alert.setContentText("Format d'age invalide !");
        alert.showAndWait();
      //  return;
    }else if (Integer.parseInt(age1) < 1 || Integer.parseInt(age1) > 150) {
    

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Âge hors de la plage valide");
        alert.setHeaderText(null);
        alert.setContentText("L'âge doit être compris entre 1 et 150.");
        alert.showAndWait();
    } else {
            u.setId_user(ConnexionUserController.id_modif);
            u.setUsername(tf_modif_ident.getText());
            u.setMail(tf_modif_email.getText());
            u.setMdp(mdp1);
            u.setAge(Integer.parseInt(age1));
            u.setRole(role1);
            u.setSexe(sexe);
            u.setImage(ImagePath);
            su.modifier(u);
             Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
           alert.setContentText("Profil modifié");
            alert.show();
     try {
            Parent page1 = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
            
            Scene scene = new Scene(page1);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    @FXML
    private void Retour(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void copyFileToDirectory(File sourceFile, File destDir) throws IOException {
    Path sourcePath = sourceFile.toPath();
    Path destPath = destDir.toPath().resolve(sourceFile.getName());
    Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }
    @FXML
    private void add_image_action(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File defaultDir = new File("C:\\Users\\nasri\\OneDrive\\Bureau\\IRONFIT\\src\\image");
        fc.setInitialDirectory(defaultDir);
        File SelectedFile = fc.showOpenDialog(null);

        if (SelectedFile != null) {
            copyFileToDirectory(SelectedFile, defaultDir);

            ImagePath = defaultDir + "/" + SelectedFile.getName();
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
        } else {

            ImagePath = "C:\\Users\\nasri\\OneDrive\\Bureau\\IRONFIT\\src\\image/5.jpg";
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));

        }
    }

      @FXML
    private void desactiver(ActionEvent event) {
            ServiceUser su = new ServiceUser();
            User aold = su.readById(ConnexionUserController.id_modif);
            
            Alert A = new Alert(Alert.AlertType.CONFIRMATION);
            A.setAlertType(Alert.AlertType.CONFIRMATION);

            A.setContentText("Voulez vous vraiment desactiver votre compte ?");
            Optional<ButtonType> result = A.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                su.supprimer(aold.getId_user());
                A.setAlertType(Alert.AlertType.INFORMATION);
                A.setContentText("on espere que vous reternez trés vite ! ");
                A.show();
            try {

            Parent page1 = FXMLLoader.load(getClass().getResource("ConnexionUser.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
            }

    }
     private void saveImage(BufferedImage image) {
       try {
            ImagePath = "C:\\Users\\nasri\\OneDrive\\Bureau\\IRONFIT\\src\\image/" + tf_modif_email.getText() + ".png";
            File outputfile = new File(ImagePath);
            imageLabel.setText(ImagePath);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
