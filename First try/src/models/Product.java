/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportproject;
import sportproject.ProductCategory;
/**
 *
 * @author Ayedi
 */
public class Product  {
      private int productId;
    private String productName;
     private String description;
   private double price;
    private int quantityInStock;
private categoryName categoryName;
    public Product(int productId, String productName, String description, double price, int quantityInStock) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product() {
    }

    public Product(String productName, String description, double price, int quantityInStock, categoryName categoryName) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.categoryName = categoryName;
    }

    public Product(int productId, String productName, String description, double price, int quantityInStock, categoryName categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.categoryName = categoryName;
    }

    public categoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(categoryName categoryName) {
        this.categoryName = categoryName;
    }

   

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", description=" + description + ", price=" + price + ", quantityInStock=" + quantityInStock + '}';
    }

  

  

   
}
