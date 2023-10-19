  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.HBox.setMargin;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ayedi
 */
public class EcomerceController implements Initializable {
@FXML
private GridPane storeGrid;
    /**
     * Initializes the controller class.
     */
 private List<Product>RecentlyAdded;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    RecentlyAdded=new ArrayList<>(getRecentlyAdded());
    int columns=0;
    int row=1;
     try {
    for(Product prod: RecentlyAdded){
   // FXMLoader fxmlLoader=new FXMLoader();
  //  fxmloader.setLocation(getClass().getResource(name="prod.fxml"));
   // Pane pane =fxmlLoader.load();
  //  Prod p=fxmlLoader.get
    
    FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("prod.fxml")); // Assuming it's an FXML file.
       
            Pane pane=fxmlLoader.load();
        
        
        prod ProdController = fxmlLoader.getController(); 
        ProdController.setData(prod);
        if(columns==3){
        columns=0;
        ++row;
        }
    storeGrid.add(pane, columns++, row);
   GridPane.setMargin(pane, new Insets(20, 20, 20, 20));
    }
        }catch (IOException ex) {
        ex.printStackTrace();
        }
    
    
    
    
    

        
        
        
        
        
        
        
        
        
    } 
   private List<Product> getRecentlyAdded() {
    List<Product> productList = new ArrayList<>();

    String dbUrl = "jdbc:mysql://localhost:3306/sportproject";
    String dbUsername = "root";
    String dbPassword = "";

    try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
        String query = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantityInStock = resultSet.getInt("quantityInStock");

                Product product = new Product(productId, productName, description, price, quantityInStock);
                productList.add(product);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return productList;
}
    
}
