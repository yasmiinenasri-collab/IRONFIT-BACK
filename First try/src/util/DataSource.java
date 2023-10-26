<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANIS
 */
public class DataSource {
    String url = "jdbc:mysql://localhost:3306/pidev4secrud";
    String user = "root";
    String pwd = "";
    
    
    Connection con;
    
    //3 
    static DataSource instance;
     //1 rendre le constructeur prive
    private DataSource() {
        
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // 2 etape: de creer une methode static pour utiliser le const 
    public static DataSource getinstance(){
        if(instance == null){
            instance =  new DataSource();
        }
        return instance;
        
    }

    public Connection getCon() {
        return con;
    }
    
    
}
=======

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AYMEN ASKRI
 */
public class DataSource {
     private Connection cnx;
    private static DataSource instance;
    
    private String url = "jdbc:mysql://localhost:3306/pidev4secrud";
    private String user = "root";
    private String password = "";
    
    private DataSource(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance(){
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return this.cnx;
    }
}
>>>>>>> 595d8441276fcc8a656ad5e3c28f2e4ca6806126
