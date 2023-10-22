/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportproject.Product;
import util.MyConnection;

/**
 *
 * @author Ayedi
 */
public class productService {
    //1
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
   
 //2   
    public void ajouterProduit(Product p){
    String req = "INSERT INTO `personne`(`productId`, `productName`, `description`, `price`, `quantityInStock`) VALUES ('" + p.getProductId() + "','" + p.getProductName() + "','" + p.getDescription() + "','" + p.getPrice() + "','" + p.getQuantityInStock() + "')";
try {
            //2 : Statement
            Statement st = cnx.createStatement();
            //3 exec
            st.executeUpdate(req);
            System.out.println("Personne ajoutée avec succes!");
            
        } catch (SQLException ex) {
            Logger.getLogger(productService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    //fetch
    
 /** public List<Product> afficherProduct() throws SQLException 
  { List<Product> products = new ArrayList<>();
  String req = "SELECT * FROM personne";
  try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Product p = new Product();
                p.getProductId(rs.getInt(1));//(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setAge(rs.getInt("age"));
                personnes.add(p);
            }catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  return products;}
    
    
**/
}