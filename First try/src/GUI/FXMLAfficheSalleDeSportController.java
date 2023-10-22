package GUI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import models.SalleDeSport;
import services.ServicesSalleDeSport;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class FXMLAfficheSalleDeSportController implements Initializable {
    @FXML
    private ListView<String> listViewSalleDeSport;
    @FXML
    private Button btnGOAJOUT;
    @FXML
    private Button btnGOMODIF;
    @FXML
    private Button btnGOSUPPRIM;
    @FXML
    private Button btnGOSEARCH;
    @FXML
    private Label statistiqueLabel;
    @FXML
    private Button btnretourtest;
    @FXML
    private ImageView qrCodeImageView; // ImageView pour afficher le code QR

    private int nombreTotalSallesDeSport = 0;
    private List<SalleDeSport> salleDeSports;

    // Méthode statique pour définir les informations de la salle depuis un autre contrôleur
    private static String infosSalle;

    public static void setInfosSalle(String infos) {
        infosSalle = infos;
    }
    @FXML
    private Button excel;
/*
    
*/
    @FXML
    private void retourAction42(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLTest.fxml"));
            btnretourtest.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLModifierSalleDeSport.fxml"));
            btnGOMODIF.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retourAction1(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAjouterSalleDeSport.fxml"));
            btnGOAJOUT.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retourAction3(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGetOneSalleDeSport.fxml"));
            btnGOSEARCH.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        salleDeSports = ss.getAllSalleDeSport();
        ObservableList<String> obs = FXCollections.observableArrayList();
        for (SalleDeSport salle : salleDeSports) {
            String salleString = "Nom : " + salle.getNom() +
                ", adresse : " + salle.getAdresse() +
                ", capacite : " + salle.getCapacite() +
                ", spécialité : " + salle.getSpecialite();
            obs.add(salleString);
            nombreTotalSallesDeSport = salleDeSports.size();
            mettreAJourStatistique();
        }
        listViewSalleDeSport.setItems(obs);

        // Associez un gestionnaire d'événements pour la sélection d'éléments
        listViewSalleDeSport.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Activez le bouton "Supprimer" lorsque quelque chose est sélectionné
                btnGOSUPPRIM.setDisable(false);
            } else {
                // Désactivez le bouton "Supprimer" lorsque rien n'est sélectionné
                btnGOSUPPRIM.setDisable(true);
            }
        });

        // Afficher le code QR si les informations de la salle sont définies
        if (infosSalle != null) {
            Image qrCodeImage = generateQRCodeImage(infosSalle, 200, 200);
            qrCodeImageView.setImage(qrCodeImage);
        }
    }

    private void mettreAJourStatistique() {
        statistiqueLabel.setText("Nombre total de salles de sport : " + nombreTotalSallesDeSport);
    }

    @FXML
    private void supprimerSalleDeSport(ActionEvent event) {
        int selectedIndex = listViewSalleDeSport.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedSalleString = listViewSalleDeSport.getSelectionModel().getSelectedItem();
            if (selectedSalleString != null) {
                String[] parts = selectedSalleString.split(",");
                String nom = null;
                String adresse = null;

                for (String part : parts) {
                    part = part.trim();
                    if (part.startsWith("Nom : ")) {
                        nom = part.substring(6);
                    } else if (part.startsWith("adresse : ")) {
                        adresse = part.substring(10);
                    }
                }
                if (nom != null && adresse != null) {
                    // Supprimez la salle de sport en utilisant le nom et l'adresse
                    ServicesSalleDeSport ss = new ServicesSalleDeSport();
                    ss.supprimerSalleDeSport(nom, adresse);
                    salleDeSports.remove(selectedIndex);

                    ObservableList<String> salleEnChaine = FXCollections.observableArrayList();
                    for (SalleDeSport salle : salleDeSports) {
                        String salleString = "Nom : " + salle.getNom() +
                            ", adresse : " + salle.getAdresse() +
                            ", capacite : " + salle.getCapacite() +
                            ", spécialité : " + salle.getSpecialite();
                        salleEnChaine.add(salleString);
                    }
                    listViewSalleDeSport.setItems(salleEnChaine);
                    nombreTotalSallesDeSport--;
                    mettreAJourStatistique();
                }
            }
        }
    }

    private Image generateQRCodeImage(String text, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            // Convertir la matrice BitMatrix en image JavaFX
            BufferedImage bufferedImage = toBufferedImage(bitMatrix);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }
        return image;
    }

    @FXML
    private void btnexcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
         // Set extension filter for Excel files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (.xlsx)", ".xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save file dialog
        File file = fileChooser.showSaveDialog(excel.getScene().getWindow());
         if (file != null) {
            try {
                // Create new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("SalleDeSport");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Nom");
                headerRow.createCell(2).setCellValue("Adresse");     
                headerRow.createCell(3).setCellValue("Capacite");
                headerRow.createCell(4).setCellValue("Specialite"); 
                // Add data rows
                ServicesSalleDeSport salledesports = new ServicesSalleDeSport();
                List<SalleDeSport> SalleDeSport = salledesports.getAllSalleDeSport();
                for (int i = 0; i < SalleDeSport.size(); i++) {
                  Row row = sheet.createRow(i + 1);
                   row.createCell(0).setCellValue(SalleDeSport.get(i).getId());
                   row.createCell(1).setCellValue(SalleDeSport.get(i).getNom());
                   row.createCell(2).setCellValue(SalleDeSport.get(i).getAdresse());
                   row.createCell(3).setCellValue(SalleDeSport.get(i).getCapacite());
                   row.createCell(4).setCellValue(SalleDeSport.get(i).getSpecialite());
                }
                   // Write to file
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful");
                alert.setHeaderText(null);
                alert.setContentText("Events exported to Excel file.");
                alert.showAndWait();
                } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while exporting events to Excel file.");
                alert.showAndWait();
            }
        }
    }   
    }