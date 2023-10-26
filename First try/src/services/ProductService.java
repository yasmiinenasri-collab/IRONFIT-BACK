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
import java.io.InputStream;
import util.DataSource;

public class ProductService {
    private DataSource mycnx = DataSource.getInstance();
    private Connection cnx = mycnx.getCnx();

    public ProductService() {
    }

  public void addProduct(Product p, String description, byte[] imageBytes) {
    String query = "INSERT INTO product(productId, productName, description, image, price, quantityInStock) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, p.getProductId());
        preparedStatement.setString(2, p.getProductName());
        preparedStatement.setString(3, description); // Set the description as a string
        preparedStatement.setBytes(4, imageBytes); // Set the image as a byte array
        preparedStatement.setDouble(5, p.getPrice());
        preparedStatement.setInt(6, p.getQuantityInStock());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product with image and description added successfully!");
        } else {
            System.out.println("Failed to add the product with image and description.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

   /* public void modifyProductPrice(Product p, double newPrice) {
        int prodmodifier=p.getProductId();
        if(prodmodifier!=0){
            p.setPrice(newPrice);
        String query = "UPDATE product SET price = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2,prodmodifier );

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Price of the product modified successfully!");
            } else {
                System.out.println("Failed to modify the price of the product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }}
         else {
                System.out.println("Aucun forum trouvé avec cet ID.");
            }
    }*/
  public void modifyProductPrice(int productId, double newPrice) {
    String query = "UPDATE product SET price = ? WHERE productId = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setDouble(1, newPrice);
        preparedStatement.setInt(2, productId);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Price of the product with productId " + productId + " modified successfully!");
        } else {
            System.out.println("Failed to modify the price of the product with productId " + productId + ".");
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
    String query = "SELECT productId, productName, image, price, quantityInStock FROM product";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            int productId = resultSet.getInt("productId");
            String productName = resultSet.getString("productName");
            byte[] image = resultSet.getBytes("image");
            double price = resultSet.getDouble("price");
            int quantityInStock = resultSet.getInt("quantityInStock");

            Product product = new Product(productId, productName, null, image, price, quantityInStock);
            products.add(product);
        }
    } catch (SQLException e) {
        System.err.println("Error while displaying products: " + e.getMessage());
    }

    return products;
}

public List<Product> chercherProduitsParCategorie(String categoryName) {
    List<Product> products = new ArrayList<>();
    String query = "SELECT p.productId, p.productName, p.image, p.price, p.quantityInStock " +
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
               String descreption = resultSet.getString("descreption");
            byte[] image = resultSet.getBytes("image");
            double price = resultSet.getDouble("price");
            int quantityInStock = resultSet.getInt("quantityInStock");

            Product product = new Product(productId, productName, descreption, image, price, quantityInStock);
            products.add(product);
        }
    } catch (SQLException e) {
        System.err.println("Error while fetching products by category: " + e.getMessage());
    }

    return products;
}
public Product getOne(int productId) {
    String query = "SELECT productId, productName, description, image, price, quantityInStock FROM product WHERE productId = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, productId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int retrievedProductId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                byte[] image = resultSet.getBytes("image");
                double price = resultSet.getDouble("price");
                int quantityInStock = resultSet.getInt("quantityInStock");

                Product product = new Product(retrievedProductId, productName, description, image, price, quantityInStock);
                return product;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error while retrieving the product: " + e.getMessage());
    }

    return null; // Return null if the product is not found
}

 
}
