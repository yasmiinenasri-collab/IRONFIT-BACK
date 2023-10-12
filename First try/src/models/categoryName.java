/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import util.MyConnection;
/**
 *
 * @author Ayedi
 */
public enum categoryName {
   accessoires,MICRONUTRIMENTS,snacks,amino,enduranceSerie,proteines;

    public static categoryName getAccessoires() {
        return accessoires;
    }

    public static categoryName getMICRONUTRIMENTS() {
        return MICRONUTRIMENTS;
    }

    public static categoryName getSnacks() {
        return snacks;
    }

    public static categoryName getAmino() {
        return amino;
    }

    public static categoryName getEnduranceSerie() {
        return enduranceSerie;
    }

    public static categoryName getProteines() {
        return proteines;
    }
    
}
