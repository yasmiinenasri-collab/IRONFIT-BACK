package services;
import entite.RegimeAli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataSource;

public class ServiceRA implements IserviceRA<RegimeAli> {
    private Connection cnx;

    public ServiceRA() {
        this.cnx = DataSource.getInstance().getConnection();
    }


    
        
    @Override
    public void ajouter(RegimeAli ra) {
        // Trouver l'idMED en utilisant le nom et le prénom du médecin
        int idMedecin = trouverIdMedecin(ra.getNomMED(), ra.getPrenomMED());

        if (idMedecin != -1) {
            String req = "INSERT INTO regimeAli (prixRegime, typeRegime, nomMED, prenomMED, idMED) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setDouble(1, ra.getPrixRegime());
                ps.setString(2, ra.getTypeRegime());
                ps.setString(3, ra.getNomMED());
                ps.setString(4, ra.getPrenomMED());
                ps.setInt(5, idMedecin); // Utilisation de l'idMedecin trouvé
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("RegimeAli ajouté avec succès !");
                }
            } catch (SQLException ex) {
                System.out.println("Erreur lors de l'ajout du RegimeAli : " + ex.getMessage());
            }
        } else {
            System.out.println("Le médecin associé n'existe pas. Impossible d'ajouter le RegimeAli.");
        }
    }

    @Override
    public void modifier(RegimeAli ra) {
        String req = "UPDATE regimeAli SET prixRegime = ?, typeRegime = ?, nomMED = ?, prenomMED = ? WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, ra.getPrixRegime());
            ps.setString(2, ra.getTypeRegime());
            ps.setString(3, ra.getNomMED());
            ps.setString(4, ra.getPrenomMED());
            ps.setInt(5, ra.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("RegimeAli modifié avec succès !");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du RegimeAli : " + ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM regimeAli WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("RegimeAli supprimé avec succès !");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du RegimeAli : " + ex.getMessage());
        }
    }

    @Override
    public RegimeAli getOne(RegimeAli ra) {
        String req = "SELECT * FROM regimeAli WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, ra.getId());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new RegimeAli(
                    resultSet.getInt("id"),
                    resultSet.getDouble("prixRegime"),
                    resultSet.getString("typeRegime"),
                    resultSet.getString("nomMED"),
                    resultSet.getString("prenomMED"),
                    resultSet.getInt("idMED")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du RegimeAli : " + ex.getMessage());
        }
        return null;
    }

    public List<RegimeAli> getAll(RegimeAli ra) {
        List<RegimeAli> regimes = new ArrayList<>();
        String req = "SELECT * FROM regimeAli WHERE nomMED = ? AND prenomMED = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ra.getNomMED());
            ps.setString(2, ra.getPrenomMED());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                regimes.add(new RegimeAli(
                    resultSet.getInt("id"),
                    resultSet.getDouble("prixRegime"),
                    resultSet.getString("typeRegime"),
                    resultSet.getString("nomMED"),
                    resultSet.getString("prenomMED"),
                    resultSet.getInt("idMED")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des RegimeAli : " + ex.getMessage());
        }
        return regimes;
    }

    public  int trouverIdMedecin(String nomMED, String prenomMED) {
        String req = "SELECT id FROM medecin WHERE nomMED = ? AND prenomMED = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nomMED);
            ps.setString(2, prenomMED);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de l'id du médecin : " + ex.getMessage());
        }
        return -1; // Retourner -1 si le médecin n'est pas trouvé
    }

    public List<RegimeAli> getAll() {
         List<RegimeAli> regimes = new ArrayList<>();
        try {
            String req = "SELECT * FROM regimeAli";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RegimeAli regime = new RegimeAli();
                regime.setId(rs.getInt("id"));
                regime.setPrixRegime(rs.getDouble("prixRegime"));
                regime.setTypeRegime(rs.getString("typeRegime"));
                regime.setNomMED(rs.getString("nomMED"));
                regime.setPrenomMED(rs.getString("prenomMED"));
                regime.setIdMED(rs.getInt("idMED"));
                regimes.add(regime);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return regimes;
    
    }

   
}
