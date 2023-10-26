/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SalleDeSport;
import util.DataSource;

/**
 *
 * @author tlili
 */
public class ServicesSalleDeSport implements IServiceSalleDeSport<SalleDeSport> {
    Connection cnx; 
    

    public ServicesSalleDeSport() {
         this.cnx= DataSource.getinstance().getCon();
    }

  

   @Override
public void ajouterSalleDeSport(SalleDeSport ss) {
    ss.setId(0);
    
    String req = "INSERT INTO `Salledesport`(`idSalleDeSport`, `nom`, `adresse`, `capacite`, `specialite`) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, ss.getId());
        ps.setString(2, ss.getNom());
        ps.setString(3, ss.getAdresse());
        ps.setString(4, ss.getCapacite());
        ps.setString(5, ss.getSpecialite());
        ps.executeUpdate();
        System.out.println("Salle De Sport ajoutée avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
public SalleDeSport getOneSalleDeSportByNom(String nom) {
    Connection connection = DataSource.getinstance().getCon();

    String query = "SELECT * FROM salledesport WHERE nom = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, nom);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                SalleDeSport salleDeSport = new SalleDeSport();
                salleDeSport.setId(resultSet.getInt("idSalleDeSport")); // Assurez-vous que la colonne "idSalleDeSport" existe dans votre base de données
                salleDeSport.setNom(resultSet.getString("nom"));
                salleDeSport.setAdresse(resultSet.getString("adresse"));
                salleDeSport.setCapacite(resultSet.getString("capacite"));
                salleDeSport.setSpecialite(resultSet.getString("specialite"));

                return salleDeSport;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les erreurs de base de données ici
    }

    return null; // Retournez null si la salle de sport n'est pas trouvée
}
public SalleDeSport getOneSalleDeSportByAdresse(String adresse) {
    Connection connection = DataSource.getinstance().getCon();

    String query = "SELECT * FROM salledesport WHERE adresse = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, adresse);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                SalleDeSport salleDeSport = new SalleDeSport();
                salleDeSport.setId(resultSet.getInt("idSalleDeSport")); // Assurez-vous que la colonne "idSalleDeSport" existe dans votre base de données
                salleDeSport.setNom(resultSet.getString("nom"));
                salleDeSport.setAdresse(resultSet.getString("adresse"));
                salleDeSport.setCapacite(resultSet.getString("capacite"));
                salleDeSport.setSpecialite(resultSet.getString("specialite"));

                return salleDeSport;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les erreurs de base de données ici
    }

    return null; // Retournez null si la salle de sport n'est pas trouvée
}
public SalleDeSport getOneSalleDeSportByCapacite(String capacite) {
    Connection connection = DataSource.getinstance().getCon();

    String query = "SELECT * FROM salledesport WHERE capacite = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, capacite);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                SalleDeSport salleDeSport = new SalleDeSport();
                salleDeSport.setId(resultSet.getInt("idSalleDeSport")); // Assurez-vous que la colonne "idSalleDeSport" existe dans votre base de données
                salleDeSport.setNom(resultSet.getString("nom"));
                salleDeSport.setAdresse(resultSet.getString("adresse"));
                salleDeSport.setCapacite(resultSet.getString("capacite"));
                salleDeSport.setSpecialite(resultSet.getString("specialite"));

                return salleDeSport;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les erreurs de base de données ici
    }

    return null; // Retournez null si la salle de sport n'est pas trouvée
}
public SalleDeSport getOneSalleDeSportBySpecialite(String specialite) {
    Connection connection = DataSource.getinstance().getCon();

    String query = "SELECT * FROM salledesport WHERE specialite = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, specialite);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                SalleDeSport salleDeSport = new SalleDeSport();
                salleDeSport.setId(resultSet.getInt("idSalleDeSport")); // Assurez-vous que la colonne "idSalleDeSport" existe dans votre base de données
                salleDeSport.setNom(resultSet.getString("nom"));
                salleDeSport.setAdresse(resultSet.getString("adresse"));
                salleDeSport.setCapacite(resultSet.getString("capacite"));
                salleDeSport.setSpecialite(resultSet.getString("specialite"));

                return salleDeSport;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les erreurs de base de données ici
    }

    return null; // Retournez null si la salle de sport n'est pas trouvée
}


public SalleDeSport getOneSalleDeSportByID(int id) {
     Connection connection = DataSource.getinstance().getCon();

        String query = "SELECT * FROM salledesport WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SalleDeSport salleDeSport = new SalleDeSport();
                    salleDeSport.setId(resultSet.getInt("id"));
                    salleDeSport.setNom(resultSet.getString("nom"));
                    salleDeSport.setAdresse(resultSet.getString("adresse"));
                    salleDeSport.setCapacite(resultSet.getString("capacite"));
                    salleDeSport.setSpecialite(resultSet.getString("specialite"));

                    return salleDeSport;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les erreurs de base de données ici
        }

        return null; // Retournez null si la salle de sport n'est pas trouvée
    }


 
   @Override
public void modifierSalleDeSport(SalleDeSport ss) {
    String req = "UPDATE `Salledesport` SET `nom` = ?, `adresse` = ?, `capacite` = ?, `specialite` = ? WHERE `idSalleDeSport` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, ss.getNom()); // Assurez-vous que les paramètres correspondent à l'ordre des ? dans la requête SQL
        ps.setString(2, ss.getAdresse());
        ps.setString(3, ss.getCapacite());
        ps.setString(4, ss.getSpecialite());
        ps.setInt(5, ss.getId()); // Vous utilisez setInt car il s'agit probablement d'un identifiant entier
        ps.executeUpdate();
        System.out.println("Salle De Sport modifié avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
  public SalleDeSport getOneSalleDeSport(String nom) {
        SalleDeSport salle = null;
        String req = "SELECT * FROM `Salledesport` WHERE `nom` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                salle = new SalleDeSport();
                salle.setId(rs.getInt(1));
                salle.setNom(rs.getString("Nom"));
                salle.setAdresse(rs.getString("Adresse"));
                salle.setCapacite(rs.getString(4));
                salle.setSpecialite(rs.getString("Specialite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return salle;
    }
   public List<String> getAllSalleDeSportNoms() {
        List<String> noms = new ArrayList<>();

       
        DataSource ds = DataSource.getinstance();
        try (Connection conn = ds.getCon()) {
            String query = "SELECT nom FROM `Salledesport`";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                noms.add(nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noms;
    }

    @Override
    public void supprimerSalleDeSport(int id) {
        String req = "DELETE FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Salle De Sport supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
       
    
     public void supprimerSalleDeSport(String nom, String adresse) {
    Connection connection = DataSource.getinstance().getCon();
    PreparedStatement preparedStatement = null;

    try {
        String query = "DELETE FROM salledesport WHERE nom = ? AND adresse = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, adresse);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Salle de sport supprimée avec succès !");
        } else {
            System.out.println("Aucune salle de sport trouvée avec le nom " + nom + " et l'adresse " + adresse);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de la salle de sport : " + ex.getMessage());
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
    

    @Override
    public SalleDeSport getOneSalleDeSport(SalleDeSport ss) {
        SalleDeSport resultat = null;
        String req = "SELECT * FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, ss.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultat = new SalleDeSport();
                resultat.setId(rs.getInt(1));
                resultat.setNom(rs.getString("Nom"));
                resultat.setAdresse(rs.getString("Adresse"));
                resultat.setCapacite(rs.getString(4));
                resultat.setSpecialite(rs.getString("Specialite"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }

    @Override
    public List<SalleDeSport> getAllSalleDeSport()  {
        String req = "SELECT * FROM `Salledesport`";
        ArrayList<SalleDeSport> SalleDeSports = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalleDeSport sss = new SalleDeSport();
                sss.setId(rs.getInt(1));
                sss.setNom(rs.getString("Nom"));
                sss.setAdresse(rs.getString("Adresse"));
                sss.setCapacite(rs.getString(4));
                sss.setSpecialite(rs.getString("Specialite"));
                SalleDeSports.add(sss);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return SalleDeSports;
}
    public List<String> getAllSalleDeSportNames() {
    List<String> names = new ArrayList<>();
    String req = "SELECT `nom` FROM `Salledesport`";
    
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            names.add(rs.getString("nom"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return names;
}

    public SalleDeSport getOneSalleDeSportById(int id) {
        SalleDeSport resultat = null;
        String req = "SELECT * FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultat = new SalleDeSport();
                resultat.setId(rs.getInt(1));
                resultat.setNom(rs.getString("Nom"));
                resultat.setAdresse(rs.getString("Adresse"));
                resultat.setCapacite(rs.getString(4));
                resultat.setSpecialite(rs.getString("Specialite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
       public int getIdSalleDeSportByNom(String nomSalleDeSport) {
        int id = -1; // Initialisez l'ID à -1 (non trouvé) par défaut
        String req = "SELECT `idSalleDeSport` FROM `Salledesport` WHERE `nom` = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nomSalleDeSport);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return id;
    }
     
       public List<Integer> getAllSalleDeSportIds() {
    List<Integer> ids = new ArrayList<>();
    String requete = "SELECT `idSalleDeSport` FROM `Salledesport`";

    try {
        PreparedStatement preparedStatement = cnx.prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ids.add(resultSet.getInt("idSalleDeSport"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return ids;
}

}
