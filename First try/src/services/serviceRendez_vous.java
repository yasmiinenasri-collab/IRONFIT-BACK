package Services;

import Interface.IserviceRendez_vous;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Rendez_vous;
import util.MyConnection;

public class serviceRendez_vous implements IserviceRendez_vous {
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    @Override
    public void AjouterRendez_vous(Rendez_vous R) {
        String req = "INSERT INTO Rendez_vous (Id_coach, id_client, date_RDV, time_RDV) VALUES ('" + R.getId_coach() + "', '" + R.getId_client() + "', '" + R.getDate_RDV() + "', '" + R.getTime_RDV() + "')";

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                R.setId_RDV(id);
            }

            System.out.println("Rendez-vous ajouté avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(serviceRendez_vous.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Rendez_vous> afficherRendez_vous() {
        List<Rendez_vous> rendezvous = new ArrayList<>();
        String req = "SELECT * FROM Rendez_vous";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Rendez_vous r = new Rendez_vous();
                r.setId_RDV(rs.getInt("id_RDV"));
                r.setId_coach(rs.getInt("id_coach"));
                r.setId_client(rs.getInt("id_client"));
                r.setDate_RDV(rs.getString("date_RDV"));
                r.setTime_RDV(rs.getString("time_RDV"));
                rendezvous.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceRendez_vous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rendezvous;
    }

    @Override
    public void supprimerRendez_vous(int id_RDV) {
        String req = "DELETE FROM Rendez_vous WHERE id_RDV = " + id_RDV;
        try {
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);

            if (rowsAffected > 0) {
                System.out.println("Rendez-vous supprimé avec succès!");
            } else {
                System.out.println("Aucun rendez-vous trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceRendez_vous.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierRendez_vous(Rendez_vous R) {
        String req = "UPDATE Rendez_vous SET Id_coach = '" + R.getId_coach() + "', id_client = '" + R.getId_client() + "', date_RDV = '" + R.getDate_RDV() + "', time_RDV = '" + R.getTime_RDV() + "' WHERE id_RDV = " + R.getId_RDV();
        try {
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);

            if (rowsAffected > 0) {
                System.out.println("Rendez-vous modifié avec succès!");
            } else {
                System.out.println("Aucun rendez-vous trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceRendez_vous.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Rendez_vous getOne(int id_RDV) {
        Rendez_vous rendezvous = null;
        String req = "SELECT * FROM Rendez_vous WHERE id_RDV = " + id_RDV;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                rendezvous = new Rendez_vous();
                rendezvous.setId_RDV(rs.getInt("id_RDV"));
                rendezvous.setId_coach(rs.getInt("id_coach"));
                rendezvous.setId_client(rs.getInt("id_client"));
                rendezvous.setDate_RDV(rs.getString("date_RDV"));
                rendezvous.setTime_RDV(rs.getString("time_RDV"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceRendez_vous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rendezvous;
    }
}
