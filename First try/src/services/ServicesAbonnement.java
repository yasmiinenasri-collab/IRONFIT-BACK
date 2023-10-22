/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Abonnement;
import util.DataSource;

public class ServicesAbonnement implements IServiceAbonnement<Abonnement> {
 Connection cnx; 
 Statement ste;
  PreparedStatement preparedStatement;
    public ServicesAbonnement() {
        this.cnx= DataSource.getInstance().getConnection();
    }

    @Override

 public void ajouterAbonnement(Abonnement ab, String nomSalleDeSport) {
    String req = "INSERT INTO `abonnement`(`id`, `type`, `dateDebut`, `dateFin`, `prix`, `idSalleDeSport`) " +
                 "VALUES (?, ?, ?, ?, ?, " +
                 "(SELECT `idSalleDeSport` FROM `SalleDeSport` WHERE `nom` = ? LIMIT 1))";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, ab.getId());
        ps.setString(2, ab.getType());
        ps.setString(3, ab.getDateDebut());
        ps.setString(4, ab.getDateFin());
        ps.setDouble(5, ab.getPrix());
        ps.setString(6, nomSalleDeSport); 
        ps.executeUpdate();
        System.out.println("Abonnement ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



   public List<Integer> getListeIdAbonnements() {
       
        List<Integer> listeIdAbonnements = new ArrayList<>();
        
    
        listeIdAbonnements.add(1);
        listeIdAbonnements.add(2);
        listeIdAbonnements.add(3);     
        return listeIdAbonnements;
    }
       public int getNombreParticipants(int id) {
    int nombreParticipants = 0;
    String req = "SELECT COUNT(*) FROM abonnement WHERE idSalleDeSport = ?";
    try {
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, id);
         ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            nombreParticipants = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du nombre de participants : " + ex.getMessage());
    }

    return nombreParticipants;
 }

 @Override
 public Abonnement getAbonnementById(int abonnementId) {
    Abonnement abonnement = null;

    try {
        String query = "SELECT * FROM abonnement WHERE id = ?";
        preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setInt(1, abonnementId);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("Type");
                String dateDebut = resultSet.getString("DateDebut");
                String dateFin = resultSet.getString("DateFin");
                double prix = resultSet.getDouble("Prix");

                abonnement = new Abonnement(id, type, dateDebut, dateFin, prix);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return abonnement;
}
 public List<String> getNomsAbonnements() {
        List<String> noms = new ArrayList<>();

        try {
            String query = "SELECT nom FROM abonnement"; // Remplacez 'nom' par le nom de la colonne contenant les noms d'abonnement dans votre base de données
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom"); // Assurez-vous de remplacer 'nom' par le nom de la colonne dans votre base de données
                noms.add(nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noms;
    }
 public List<String> getTypesAbonnements() {
    Connection connection = DataSource.getInstance().getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<String> typesAbonnements = new ArrayList<>();

    try {
        String query = "SELECT DISTINCT type FROM abonnement"; // Remplacez "VotreTableAbonnements" par le nom de votre table.
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String typeAbonnement = resultSet.getString("type");
            typesAbonnements.add(typeAbonnement);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Assurez-vous de gérer correctement les ressources de la base de données (fermeture de la connexion, etc.).
    }

    return typesAbonnements;
}
 public Abonnement getAbonnementByType(String typeAbonnement) {
    Connection connection = DataSource.getInstance().getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Abonnement abonnement = null;

    try {
        String query = "SELECT * FROM abonnement WHERE type = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, typeAbonnement);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            abonnement = new Abonnement();
            abonnement.setId(resultSet.getInt("id"));
            abonnement.setType(resultSet.getString("type"));
            abonnement.setDateDebut(resultSet.getString("dateDebut"));
            abonnement.setDateFin(resultSet.getString("dateFin"));
            abonnement.setPrix(resultSet.getDouble("prix"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Assurez-vous de gérer correctement les ressources de la base de données (fermeture de la connexion, etc.).
    }

    return abonnement;
}

   public Abonnement getAbonnementByNom(String nomAbonnement) {
        Abonnement abonnement = null;

        try {
            String query = "SELECT * FROM abonnement WHERE nom = ?"; // Assurez-vous de remplacer 'nom' par le nom de la colonne contenant les noms d'abonnement dans votre base de données
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, nomAbonnement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type"); // Remplacez 'type' par le nom de la colonne contenant le type d'abonnement
                String dateDebut = resultSet.getString("dateDebut"); // Remplacez 'dateDebut' par le nom de la colonne contenant la date de début
                String dateFin = resultSet.getString("dateFin"); // Remplacez 'dateFin' par le nom de la colonne contenant la date de fin
                double prix = resultSet.getDouble("prix"); // Remplacez 'prix' par le nom de la colonne contenant le prix

                abonnement = new Abonnement(id, type, dateDebut, dateFin, prix);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonnement;
    }
     @Override
   public int getIdSalleDeSportByNom(String nomSalleDeSport) {
    String req = "SELECT idSalleDeSport FROM SalleDeSport WHERE nom = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nomSalleDeSport);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("idSalleDeSport");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return -1; 
}


public void associerAbonnementASalleDeSport(Abonnement abonnement, String nomSalleDeSport) {
   
    int idSalleDeSport = getIdSalleDeSportByNom(nomSalleDeSport);
    
    if (idSalleDeSport != -1) {
       
        abonnement.setIdSalleDeSport(idSalleDeSport);
    } else {
    
        System.out.println("Salle de sport '" + nomSalleDeSport + "' non trouvée.");
    }
}

   @Override
public void modifierAbonnement(Abonnement ab) {
    String req = "UPDATE `abonnement` SET `type` = ?, `dateDebut` = ?, `dateFin` = ?, `prix` = ? WHERE `id` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, ab.getType());
        ps.setString(2, ab.getDateDebut());
        ps.setString(3, ab.getDateFin());
        ps.setDouble(4, ab.getPrix());
        ps.setInt(5, ab.getId());
        ps.executeUpdate();
        System.out.println("Abonnement modifié avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
     @Override
     public void supprimerAbonnement(int id, int idSalleDeSport) {
    String req = "DELETE FROM `abonnement` WHERE `id` = ? AND `idSalleDeSport` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.setInt(2, idSalleDeSport);
        int rowCount = ps.executeUpdate();
        
        if (rowCount > 0) {
            System.out.println("Abonnement supprimé avec succès!");
        } else {
            System.out.println("Aucun abonnement trouvé avec l'ID " + id + " et l'ID de salle de sport " + idSalleDeSport);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
     @Override
     public List<Abonnement> afficherAbonnement() {
    List<Abonnement> abonnements = new ArrayList<>();
    
    try {
       preparedStatement = cnx.prepareStatement("SELECT * FROM Abonnement AS a INNER JOIN salledesport AS s ON a.idSalleDeSport = s.idSalleDeSport");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Abonnement ab = new Abonnement();
            ab.setType(resultSet.getString("type"));
            ab.setDateDebut(resultSet.getString("DateDebut"));
            ab.setDateFin(resultSet.getString("DateFin"));
            ab.setPrix(resultSet.getDouble("Prix"));
            ab.setIdSalleDeSport(resultSet.getInt("idSalleDeSport")); 
            abonnements.add(ab);
        }
    } catch (SQLException exception) {
            System.out.println("Error (recuperer) Abonnement : " + exception.getMessage());
    }
           
        
    return abonnements;
}
public List<Integer> getAllIdAbonnement() {
    List<Integer> IDs = new ArrayList<>();
    String req = "SELECT `id` FROM `abonnement`";
    
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            IDs.add(rs.getInt("id"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return IDs;
}
   
   public void supprimerAbonnementParType(String type) {
        Connection conn = DataSource.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Créez la requête SQL pour supprimer l'abonnement par son type
            String query = "DELETE FROM Abonnement WHERE type = ?";

            // Préparez la déclaration SQL
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, type);

            // Exécutez la requête SQL pour supprimer l'abonnement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Abonnement supprimé avec succès !");
            } else {
                System.out.println("Aucun abonnement trouvé avec le type " + type);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'abonnement : " + ex.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


public void supprimerAbonnement(int abonnementId) {
    String req = "DELETE FROM abonnement WHERE id = ?";

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, abonnementId);
        int rowCount = ps.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Abonnement avec l'ID " + abonnementId + " supprimé avec succès !");
        } else {
            System.out.println("Aucun abonnement trouvé avec l'ID " + abonnementId);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'abonnement : " + ex.getMessage());
    }
}
public void supprimerAbonnementParTypeEtPrix(String typeAbonnement, double prixAbonnement) {
    Connection connection = DataSource.getInstance().getConnection();
    PreparedStatement preparedStatement = null;

    try {
        // Créez la requête SQL pour supprimer l'abonnement par type et prix
        String query = "DELETE FROM abonnement WHERE type = ? AND prix = ?";

        // Préparez la déclaration SQL
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, typeAbonnement);
        preparedStatement.setDouble(2, prixAbonnement);

        // Exécutez la requête SQL pour supprimer l'abonnement
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Abonnement supprimé avec succès !");
        } else {
            System.out.println("Aucun abonnement trouvé avec le type " + typeAbonnement + " et le prix " + prixAbonnement);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'abonnement : " + ex.getMessage());
    } finally {
        // Assurez-vous de gérer correctement les ressources de la base de données (fermeture de la connexion, etc.).
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

}  
