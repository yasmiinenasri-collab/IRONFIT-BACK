/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ironfit;
import static java.lang.System.console;
import models.User;
import Services.ServiceUser;

/**
 *
 * @author nasri
 */
public class IRONFIT {








/**
 *
 * @author ANIS
 */
public class ironfit {}

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
        // TODO code application logic here
        //public User(int id, String username, String mail, String mdp,Role role, String image,int age, String sexe)
       
          User updatedUser = new User();
    updatedUser.setId_user(1); // Set the ID of the user you want to update
    updatedUser.setUsername("aa");
    updatedUser.setMail("a@a.com");
    updatedUser.setMdp("Aa000000");
    updatedUser.setRole("ADMIN");
    updatedUser.setImage("newImageURL");
    updatedUser.setAge(25);
    updatedUser.setSexe("Homme");

    // Assuming 'modifier' method is part of a class (e.g., UserDAO)
    ServiceUser su = new ServiceUser();

    // Call the 'modifier' method to update the user
    su.modifier(updatedUser);
    
    
    }
   
    }
    

