package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.RendezVous;
import util.DataSource;

public class ServiceRV implements IserviceRV<RendezVous> {
    Connection cnx;

    public ServiceRV() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(RendezVous rendezVous) {
        try {
            String req = "INSERT INTO `rendezvous`(`DateRendezVous`, `HeureRendezVous`, `NomPatient`, `PrenomPatient`) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            java.sql.Date sqlDate = new java.sql.Date(rendezVous.getDateRendezVous().getTime());

            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, rendezVous.getHeureRendezVous());
            preparedStatement.setString(3, rendezVous.getNomPatient());
            preparedStatement.setString(4, rendezVous.getPrenomPatient());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(RendezVous rendezVous) {
        try {
            String req = "UPDATE `rendezvous` SET `DateRendezVous` = ?, `HeureRendezVous` = ?, `NomPatient` = ?, `PrenomPatient` = ? WHERE `IdRV` = ?";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);

            java.sql.Date sqlDate = new java.sql.Date(rendezVous.getDateRendezVous().getTime());

            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, rendezVous.getHeureRendezVous());
            preparedStatement.setString(3, rendezVous.getNomPatient());
            preparedStatement.setString(4, rendezVous.getPrenomPatient());
            preparedStatement.setInt(5, rendezVous.getIdRV());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `rendezvous` WHERE `IdRV` = ?";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public RendezVous getOne(RendezVous rendezVous) {
        try {
            String req = "SELECT * FROM `rendezvous` WHERE `IdRV` = ?";
            PreparedStatement preparedStatement = this.cnx.prepareStatement(req);

            preparedStatement.setInt(1, rendezVous.getIdRV());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                RendezVous retrievedRV = new RendezVous();
                retrievedRV.setIdRV(rs.getInt("IdRV"));
                retrievedRV.setNomPatient(rs.getString("NomPatient"));
                retrievedRV.setPrenomPatient(rs.getString("PrenomPatient"));
                retrievedRV.setDateRendezVous(rs.getDate("DateRendezVous"));
                retrievedRV.setHeureRendezVous(rs.getString("HeureRendezVous"));
                return retrievedRV;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public List<RendezVous> getAll(RendezVous rendezVous) {
        String req = "SELECT * FROM rendezvous";
        ArrayList<RendezVous> rendezVousList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.cnx.prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RendezVous rv = new RendezVous();
                rv.setIdRV(rs.getInt("IdRV"));
                rv.setNomPatient(rs.getString("NomPatient"));
                rv.setPrenomPatient(rs.getString("PrenomPatient"));
                rv.setDateRendezVous(rs.getDate("DateRendezVous"));
                rv.setHeureRendezVous(rs.getString("HeureRendezVous"));
                rendezVousList.add(rv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rendezVousList;
    }
}
