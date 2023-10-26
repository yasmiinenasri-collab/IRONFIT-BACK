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

import Models.Panier;
import Models.Product;
import util.DataSource;

public class ServicePanier {
    DataSource mycnx = DataSource.getInstance();
    Connection cnx = mycnx.getCnx();

public void addProductToCart(Panier cart) {
    String query = "INSERT INTO panier(nombreProduitsAchetes, productId) VALUES (?, ?)";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setInt(1, cart.getNombreProduitsAchetes());
        // Assuming you want to add the first product from the list in the cart
        if (cart.getProduct() != null) {
            preparedStatement.setInt(2, cart.getProduct().getProductId());
        } else {
            // Handle the case where there are no products in the list
            preparedStatement.setInt(2, 0); // Set a default value, you can adjust it as needed
        }

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product added to the cart successfully!");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                cart.setPanierId(generatedId);
            }
        } else {
            System.out.println("Failed to add the product to the cart.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error adding the product to the cart: " + ex.getMessage());
    }
}


public void deleteCart(int panierId) {
    String query = "DELETE FROM panier WHERE panierId = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, panierId);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Cart with ID " + panierId + " deleted successfully.");
        } else {
            System.out.println("No cart found with ID " + panierId + ". Nothing deleted.");
        }
    } catch (SQLException e) {
        System.err.println("Error while deleting the cart: " + e.getMessage());
    }
}

  public List<Product> viewCart() {
    List<Product> cartProducts = new ArrayList<>();
    String query = "SELECT p.panierId, p.nombreProduitsAchetes, p.productId, pr.productName, pr.description, pr.image, pr.price FROM panier p " +
                   "INNER JOIN product pr ON p.productId = pr.productId";

    try (Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        while (rs.next()) {
            Panier p = new Panier();
            p.setPanierId(rs.getInt("panierId"));
            p.setNombreProduitsAchetes(rs.getInt("nombreProduitsAchetes"));
  
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setDescription(rs.getString("description"));
         
            product.setPrice(rs.getDouble("price"));

            cartProducts.add(product);
        }
    } catch (SQLException e) {
        System.err.println("Error while displaying the cart: " + e.getMessage());
    }

    return cartProducts;
}

 public Product getOneProduct(int panierId) {
    String query = "SELECT p.panierId, p.nombreProduitsAchetes, p.productId, pr.productName, pr.description, pr.image, pr.price FROM panier p " +
                   "INNER JOIN product pr ON p.productId = pr.productId " +
                   "WHERE p.panierId = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, panierId);

        try (ResultSet rs = preparedStatement.executeQuery()) {
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));

                return product;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error while retrieving the product: " + e.getMessage());
    }

    return null; // Return null if the product is not found
}   
}
