package Services;

import Interface.IservicePlanning;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Planning;
import util.MyConnection;

public class servicePlanning implements IservicePlanning {
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
      @Override
    public void AjouterPlanning(Planning p){
        String req = "INSERT INTO Planning (niveauProgramme, programme, id_coach, prix, image, videoLink, description) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNiveauProgramme());
            ps.setString(2, p.getProgramme());
            ps.setInt(3, p.getId_coach());
            ps.setFloat(4, p.getPrix());
            ps.setBytes(5, p.getImage());
            ps.setString(6, p.getVideoLink());
            ps.setString(7, p.getDescription());  // Add the description to the query
            ps.executeUpdate();

            System.out.println("Planning ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(servicePlanning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Planning> afficherPlanning() {
        List<Planning> planning = new ArrayList<>();
        String req = "SELECT * FROM Planning";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Planning p = new Planning();
                p.setIdPlanning(rs.getInt("idPlanning"));
                p.setProgramme(rs.getString("programme"));
                p.setNiveauProgramme(rs.getString("niveauProgramme"));
                p.setId_coach(rs.getInt("id_coach"));
                p.setPrix(rs.getFloat("prix"));
                Blob blob = rs.getBlob("image");
                p.setViews(rs.getInt("views"));
                if(blob != null){
                    int blobLength = (int) blob.length();  
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    p.setImage(blobAsBytes);
                }
                p.setVideoLink(rs.getString("videoLink"));
                p.setDescription(rs.getString("description"));  // Get the description from the result set

                planning.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicePlanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planning;
    }

    @Override
    public void supprimerPlanning(int idPlanning) {
        String req = "DELETE FROM Planning WHERE idPlanning = " + idPlanning;
        try {
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);

            if (rowsAffected > 0) {
                System.out.println("Planning supprimé avec succès!");
            } else {
                System.out.println("Aucun planning trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicePlanning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void modifierPlanning(Planning p) {
        String req = "UPDATE Planning SET niveauProgramme = ?, programme = ?, id_coach = ?, prix = ?, image = ?, videoLink = ?, description = ? WHERE idPlanning = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNiveauProgramme());
            ps.setString(2, p.getProgramme());
            ps.setInt(3, p.getId_coach());
            ps.setFloat(4, p.getPrix());
            ps.setBytes(5, p.getImage());
            ps.setString(6, p.getVideoLink());
            ps.setString(7, p.getDescription());  // Add the description to the query
            ps.setInt(8, p.getIdPlanning());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Planning modifié avec succès!");
            } else {
                System.out.println("Aucun planning trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicePlanning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Planning getOne(int idPlanning) {
        Planning planning = null;
        String req = "SELECT * FROM Planning WHERE idPlanning = " + idPlanning;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            if (rs.next()) {
                planning = new Planning();
                planning.setIdPlanning(rs.getInt("idPlanning"));
                planning.setProgramme(rs.getString("programme"));
                planning.setNiveauProgramme(rs.getString("niveauProgramme"));
                planning.setId_coach(rs.getInt("id_coach"));
                planning.setPrix(rs.getFloat("prix"));
                Blob blob = rs.getBlob("image");
                if(blob != null){
                    int blobLength = (int) blob.length();  
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    planning.setImage(blobAsBytes);
                }
                planning.setVideoLink(rs.getString("videoLink"));
                planning.setDescription(rs.getString("description"));  // Get the description from the result set
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicePlanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planning;
    }
    
    /**
     *
     * @return
     */
    
  @Override
public Map<Planning, Integer> getPlanningStatistics() {
    List<Planning> plannings = afficherPlanning();  // Assuming this method returns all plannings

    Map<Planning, Integer> planningCounters = new HashMap<>();

    for (Planning planning : plannings) {
        planningCounters.put(planning, planning.getViews());
        System.out.println("Planning: " + planning.getProgramme() + ", Views: " + planning.getViews());
    }

    return planningCounters;
}


}
