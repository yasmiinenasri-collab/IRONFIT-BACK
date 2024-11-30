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
import Models.categoryName;

import util.DataSource;
/**
 *
 * @author Ayedi
 */
public class ServicecategoryName {
  DataSource Mycnx = DataSource.getInstance();
    Connection cnx = Mycnx.getCnx();
 public void ajouterNameCategory(categoryName p){
        //1
       // Créez un objet de type categoryName
categoryName category = categoryName.MICRONUTRIMENTS;

// Utilisez un switch pour obtenir la valeur de l'énumération
String categoryValue;
switch (category) {
    case accessoires:
        categoryValue = "accessoires";
        break;
    case MICRONUTRIMENTS:
        categoryValue = "MICRONUTRIMENTS";
        break;
    case snacks:
        categoryValue = "snacks";
        break;
    case amino:
        categoryValue = "amino";
        break;
    case enduranceSerie:
        categoryValue = "enduranceSerie";
        break;
    case proteines:
        categoryValue = "proteines";
        break;
    default:
        categoryValue = ""; // Gérer les cas non valides selon vos besoins
}

// Construisez ensuite votre requête SQL avec la valeur obtenue
String req = "INSERT INTO `personne`(`categoryName`) VALUES ('" + categoryValue + "')";

        try {
            //2 : Statement
            Statement st = cnx.createStatement();
            //3 exec
            st.executeUpdate(req);
            System.out.println("Personne ajoutée avec succes!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicecategoryName.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
     public List<String> afficherNameCategry() {
    List<String> categories = new ArrayList<>();
    // 1
    String req = "SELECT nom FROM category"; // Vous ne sélectionnez que le nom de la personne ici, pas tous les champs.
    try {
        // 2
        Statement st = cnx.createStatement();
        // 3
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            String nomCategorie = rs.getString("nom");
            categories.add(nomCategorie);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServicecategoryName.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categories;
}

 
 
 
 
}
