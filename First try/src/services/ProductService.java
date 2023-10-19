package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Product;
import util.DataSource;

public class ProductService {
    private DataSource mycnx = DataSource.getInstance();
    private Connection cnx = mycnx.getCnx();

    public ProductService() {
    }

    public void addProduct(Product p) {
        String query = "INSERT INTO product(productId, productName, description, price, quantityInStock) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, p.getProductId());
            preparedStatement.setString(2, p.getProductName());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setDouble(4, p.getPrice());
            preparedStatement.setInt(5, p.getQuantityInStock());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Failed to add the product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifyProductPrice(Product p, double newPrice) {
        String query = "UPDATE product SET price = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, p.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Price of the product modified successfully!");
            } else {
                System.out.println("Failed to modify the price of the product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int productId) {
        String query = "DELETE FROM product WHERE productId = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product with productId " + productId + " deleted successfully.");
            } else {
                System.out.println("No product found with productId " + productId + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting product: " + e.getMessage());
        }
    }

    public List<Product> displayProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT productId, productName, description, price, quantityInStock FROM product";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantityInStock = resultSet.getInt("quantityInStock");

                Product product = new Product(productId, productName, description, price, quantityInStock);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error while displaying products: " + e.getMessage());
        }

        return products;
    }
public List<Product> chercherProduitsParCategorie(String categoryName) {
    List<Product> products = new ArrayList<>();
    String query = "SELECT p.productId, p.productName, p.description, p.price, p.quantityInStock " +
                 "FROM productCategory c " +
                 "JOIN productCategory_Products pcp ON c.categoryId = pcp.categoryId " +
                 "JOIN product p ON pcp.productId = p.productId " +
                 "WHERE c.categoryName = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setString(1, categoryName);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("productId");
            String productName = resultSet.getString("productName");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int quantityInStock = resultSet.getInt("quantityInStock");

            Product product = new Product(productId, productName, description, price, quantityInStock);
            products.add(product);
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la recherche de produits par catégorie : " + e.getMessage());
    }

    return products;
}

}
