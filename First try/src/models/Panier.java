/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportproject;

/**
 *
 * @author Ayedi
 */

public class Panier {
  private int  panierId;
    private int nombreProduitsAchetes;
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int quantityInStock;
    private String categoryName;

    public Panier(int nombreProduitsAchetes, int productId, String productName, String description, double price, int quantityInStock, String categoryName) {
        this.nombreProduitsAchetes = nombreProduitsAchetes;
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Panier{" + "nombreProduitsAchetes=" + nombreProduitsAchetes + ", productId=" + productId + ", productName=" + productName + ", description=" + description + ", price=" + price + ", quantityInStock=" + quantityInStock + ", categoryName=" + categoryName + '}';
    }

    public Panier(int nombreProduitsAchetes, String productName, String description, double price, String categoryName) {
        this.nombreProduitsAchetes = nombreProduitsAchetes;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
    }

    public int getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }

    
    public int getNombreProduitsAchetes() {
        return nombreProduitsAchetes;
    }
    

    public void setNombreProduitsAchetes(int nombreProduitsAchetes) {
        this.nombreProduitsAchetes = nombreProduitsAchetes;
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

    public String getCategoryName() {
        return categoryName;
    }

    // Ajoutez ici les getters et les setters pour les attributs de la classe Panier
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // ...
    public Panier() {
    }
}


