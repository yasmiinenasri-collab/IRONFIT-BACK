/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ayedi
 */
public class prod {
        @FXML
    private Label description;

    @FXML
    private Label price;

    @FXML
    private Label productName;

    @FXML
    private ImageView whey;
     
    
    public void setData(Product  prod){
 //   Image image= new Image(getClass().getResourceAsStream())
   productName.setText(prod.getProductName());
   description.setText(prod.getDescription());
price.setText(Float.toString((float) prod.getPrice()));

    }
}
