/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;
import models.Abonnement;

/**
 *
 * @author tlili
 * @param <T>

 */
public interface IServiceAbonnement <T> {
    public void ajouterAbonnement(T t , String z);
    public void modifierAbonnement(T t);
    public void supprimerAbonnement(int O, int B);
    //public T getOneAbonnement(T t);
    //public List<T> getAllAbonnement(T t);
    public Abonnement getAbonnementById(int J);
   List<Abonnement> afficherAbonnement() throws SQLException;
    public int getIdSalleDeSportByNom(String B) ;
   
    
}
