package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entite.medecin;
import util.DataSource;

public class ServiceM implements IserviceM<medecin> {
    private final Connection cnx;

    public ServiceM() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(medecin m) {
        try {
            String req = "INSERT INTO medecin (nomMED, prenomMED, specialite, adresse, email, tel) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, m.getNomMED());
            pstmt.setString(2, m.getPrenomMED());
            pstmt.setString(3, m.getSpecialite());
            pstmt.setString(4, m.getAdresse());
            pstmt.setString(5, m.getEmail());
            pstmt.setString(6, m.getTel());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(medecin m) {
        try {
            String req = "UPDATE medecin SET nomMED=?, prenomMED=?, specialite=?, adresse=?, email=?, tel=? "
                    + "WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, m.getNomMED());
            pstmt.setString(2, m.getPrenomMED());
            pstmt.setString(3, m.getSpecialite());
            pstmt.setString(4, m.getAdresse());
            pstmt.setString(5, m.getEmail());
            pstmt.setString(6, m.getTel());
            pstmt.setInt(7, m.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM medecin WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    public int trouverIdMedecin(String nomMED, String prenomMED) {
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

    @Override
    public medecin getOne(medecin t) {
        try {
            String req = "SELECT * FROM medecin WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, t.getId());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                medecin p = new medecin();
                p.setId(rs.getInt("id"));
                p.setNomMED(rs.getString("nomMED"));
                p.setPrenomMED(rs.getString("prenomMED"));
                p.setSpecialite(rs.getString("specialite"));
                p.setAdresse(rs.getString("adresse"));
                p.setEmail(rs.getString("email"));
                p.setTel(rs.getString("tel"));
                return p;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<medecin> getAll() {
        List<medecin> medecins = new ArrayList<>();
        try {
            String req = "SELECT * FROM medecin ";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                medecin p = new medecin();
                p.setId(rs.getInt("id"));
                p.setNomMED(rs.getString("nomMED"));
                p.setPrenomMED(rs.getString("prenomMED"));
                p.setSpecialite(rs.getString("specialite"));
                p.setAdresse(rs.getString("adresse"));
                p.setEmail(rs.getString("email"));
                p.setTel(rs.getString("tel"));
                medecins.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return medecins;
    }
    
    public medecin getOneMedecinById(int id) {
        try {
            String req = "SELECT * FROM medecin WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                medecin m = new medecin();
                m.setId(rs.getInt("id"));
                m.setNomMED(rs.getString("nomMED"));
                m.setPrenomMED(rs.getString("prenomMED"));
                m.setSpecialite(rs.getString("specialite"));
                m.setAdresse(rs.getString("adresse"));
                m.setEmail(rs.getString("email"));
                m.setTel(rs.getString("tel"));
                return m;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null; // Retourner null si le médecin n'est pas trouvé
    }

    public List<String> getSpecialitesMedecins() {
        List<String> specialites = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT specialite FROM medecin";
            PreparedStatement statement = cnx.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                specialites.add(resultSet.getString("specialite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return specialites;
    }

    public int getMedecinCountBySpecialite(String specialite) {
        int count = 0; // Initialiser le compteur à zéro
        try {
            String query = "SELECT COUNT(*) as medecinCount FROM medecin WHERE specialite = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, specialite);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("medecinCount");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count; // Retourner le nombre de médecins pour la spécialité donnée
    }
}
