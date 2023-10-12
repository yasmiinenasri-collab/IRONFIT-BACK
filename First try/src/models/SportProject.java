/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportproject;

import interfaceProduit.interfaceProduit;
import java.sql.Connection;
import services.ProductService;
import util.MyConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import services.ProductService ;
import services.ServicePanier;
import services.ServiceProductCategory;
import sportproject.Product;
/**
 *
 * @author Ayedi
 */
public class SportProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
 MyConnection myConnection = MyConnection.getInstance();

        // Get the database connection
        Connection connection = myConnection.getCnx();
        
          Product product1 = new Product("Product 111", "Description 1", 19, 1, categoryName.MICRONUTRIMENTS);
        Product product2 = new Product("Product 2111", "Description 2", 29.99, 20, categoryName.amino);

        // Step 3: Use the ProductService class to interact with the database
        ProductService productService = new ProductService();

        // Add products to the database
        productService.ajouterProduit(product1);
        productService.ajouterProduit(product2);
         double newPrice =19;
        productService.modifierProduit(product1, newPrice);
        productService.supprimerProduit(2);
         ServicePanier servicePanier = new ServicePanier();
     // Assurez-vous d'avoir une méthode pour établir la connexion dans votre classe ServicePanier
    
    // Créez un objet Panier avec les valeurs appropriées
    Panier panier = new Panier();
    panier.setPanierId(17); // Remplacez par l'ID du panier approprié
    panier.setNombreProduitsAchetes(6); // Remplacez par le nombre de produits approprié
    panier.setProductId(119); // Remplacez par l'ID du produit approprié
    
    // Appelez la méthode ajouterProduitAuPanier pour insérer le panier dans la base de données
    servicePanier.ajouterProduitAuPanier(panier);
        
//        Panier panier = new Panier();
//    panier.setId(2); // Remplacez 1 par l'ID approprié
 //   panier.setStock(10); // Remplacez 10 par la quantité appropriée
 //   panier.setProductId(4); // Remplacez 123 par l'ID du produit approprié

    // Créez une instance de la classe ServicePanier
  //  ServicePanier servicePanier = new ServicePanier();

    // Appelez la méthode ajouterProduitAuPanier en lui passant le panier en argument
  //  servicePanier.ajouterProduitAuPanier(panier);


    // Appelez la méthode supprimerPanier en lui passant l'ID du panier à supprimer en argument
    int idPanierASupprimer = 14; // Remplacez 1 par l'ID du panier que vous souhaitez supprimer
    servicePanier.supprimerPanier(idPanierASupprimer);
    ServiceProductCategory serviceProductCategory = new ServiceProductCategory();

    // Créez une instance de la classe ProductCategory avec les valeurs appropriées
   // ProductCategory category = new ProductCategory();
 //   category.setCategoryId(155); // Remplacez 1 par l'ID de catégorie approprié
   // category.setCategoryName("Nom de la catégorie"); // Remplacez par le nom de la catégorie approprié
    /* List<Integer> Ids = new ArrayList<>();
     Ids.add(3);
     // Ids.add(4);
     //  Ids.add(6);
       // Ids.add(11);
    category.setProductIds(Ids);
    // Appelez la méthode ajouterProductCategory en lui passant la catégorie en argument
    serviceProductCategory.ajouterProductCategory(category)*/
    
    ;

    // Affichez un message pour confirmer l'ajout de la catégorie
  

        ServiceProductCategory service = new ServiceProductCategory();

        // Test ajouterProductCategory
        ProductCategory newCategory = new ProductCategory(3, "creatine");
        newCategory.getProductIds().add(101); // Add some product IDs
        newCategory.getProductIds().add(102);

        service.ajouterProductCategory(newCategory);

        // Test afficherCategory
        System.out.println("List of Product Categories:");
        for (ProductCategory category : service.afficherCategory()) {
            System.out.println("Category ID: " + category.getCategoryId());
            System.out.println("Category Name: " + category.getCategoryName());
            System.out.println("Product IDs: " + category.getProductIds());
            System.out.println("--------------");
        }
    }
    

        // TODO code application logic here
//ProductService p= new ProductService();
//Product m= new  Product(29, "Khaled", "Guedria",5,6);
//p.ajouterProduit(m);
//   ProductService u=new ProductService();  
   //  u.ajouterProduit( p);
  //  MyConnection s=new MyConnection()    
   //  Product p=new Product();
//interfaceProduit ps= new ProductService();
/* Product m= new  Product();
 m.setCategoryName(categoryName.accessoires);
 m.setDescription("description");
 m.setPrice(13.0);
 m.setProductId(0);
 m.setQuantityInStock(14);*/
 
 //ps.ajouterProduit(m);
    }
    

