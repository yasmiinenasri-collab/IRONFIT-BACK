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

   public void addProductToCart(Panier p) {
    String query = "INSERT INTO panier(nombreProduitsAchetes, productId) VALUES (?, ?)";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setInt(1, p.getNombreProduitsAchetes());
        preparedStatement.setInt(2, p.getProduct().getProductId());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product added to the cart successfully!");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                p.setPanierId(generatedId);
            }
        } else {
            System.out.println("Failed to add the product to the cart.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error adding product to cart: " + ex.getMessage());
    }
}

    public void deleteCart(int id) {
        Connection connection = DataSource.getInstance().getCnx();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM panier WHERE panierId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cart with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No cart found with ID " + id + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting the cart: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
    }

    public List<Product> viewCart() {
        List<Product> cartProducts = new ArrayList<>();
        String query = "SELECT p.panierId, p.nombreProduitsAchetes, p.productId, pr.productName, pr.description, pr.price FROM panier p " +
                       "INNER JOIN product pr ON p.productId = pr.productId";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
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
    
}
