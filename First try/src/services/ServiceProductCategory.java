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

import Models.Product;
import Models.ProductCategory;
import util.DataSource;

public class ServiceProductCategory {
    DataSource mycnx = DataSource.getInstance();
    Connection cnx = mycnx.getCnx();

    public void ajouterProductCategory(ProductCategory c) {
        String req = "INSERT INTO productCategory(categoryName) VALUES (?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, c.getCategoryName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product Category added successfully!");
            } else {
                System.out.println("Failed to add the product category.");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                c.setCategoryId(generatedId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerProductCategory(int categoryId) {
        String req = "DELETE FROM productCategory WHERE categoryId = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setInt(1, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product Category deleted successfully!");
            } else {
                System.out.println("Failed to delete the product category.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ProductCategory> afficherCategory() {
        List<ProductCategory> categories = new ArrayList<>();
        String req = "SELECT c.categoryId, c.categoryName, p.productId, p.productName, p.description, p.price " +
                     "FROM productCategory c " +
                     "LEFT JOIN productCategory_Products pcp ON c.categoryId = pcp.categoryId " +
                     "LEFT JOIN product p ON pcp.productId = p.productId";

        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                ProductCategory currentCategory = categories.stream()
                        .filter(c -> c.getCategoryId() == categoryId)
                        .findFirst()
                        .orElse(null);

                if (currentCategory == null) {
                    currentCategory = new ProductCategory(categoryId, categoryName);
                    categories.add(currentCategory);
                }

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                    byte[] image = resultSet.getBytes("image");
                double price = resultSet.getDouble("price");

                Product product = new Product( productName, description,image, price);
                currentCategory.getProductIds().add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }
    public List<Product> chercherProduitsParCategorie(String categoryName) {
    List<Product> products = new ArrayList<>();
    String req = "SELECT p.productId, p.productName, p.description,image, p.price " +
                 "FROM productCategory c " +
                 "JOIN productCategory_Products pcp ON c.categoryId = pcp.categoryId " +
                 "JOIN product p ON pcp.productId = p.productId " +
                 "WHERE c.categoryName = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
        preparedStatement.setString(1, categoryName);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("productId");
            String productName = resultSet.getString("productName");
            String description = resultSet.getString("description");
             byte[] image = resultSet.getBytes("image");
            double price = resultSet.getDouble("price");

             Product product = new Product( productName, description,image, price);
            products.add(product);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceProductCategory.class.getName()).log(Level.SEVERE, null, ex);
    }

    return products;
}
  
}


    
    
    
    
    
    
 