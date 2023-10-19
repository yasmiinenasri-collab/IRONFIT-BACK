package Models;

import java.util.Date;

public class RendezVous {
    private int idRV;
    private String nomPatient;
    private String prenomPatient;
    private Date dateRendezVous;
    private String heureRendezVous;

    public RendezVous(int idRV, String nomPatient, String prenomPatient, Date dateRendezVous, String heureRendezVous) {
        this.idRV = idRV;  
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.dateRendezVous = dateRendezVous;
        this.heureRendezVous = heureRendezVous;
    }

    // Getters and setters
    public int getIdRV() {
        return idRV;
    }

    public void setIdRV(int idRV) {
        this.idRV = idRV;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public String getHeureRendezVous() {
        return heureRendezVous;
    }

    public void setHeureRendezVous(String heureRendezVous) {
        this.heureRendezVous = heureRendezVous;
    }

    public RendezVous() {
    }

    public RendezVous( String nomPatient, String prenomPatient, Date dateRendezVous, String heureRendezVous) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.dateRendezVous = dateRendezVous;
        this.heureRendezVous = heureRendezVous;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "idRV=" + idRV +
                ", nomPatient='" + nomPatient + '\'' +
                ", prenomPatient='" + prenomPatient + '\'' +
                ", dateRendezVous=" + dateRendezVous +
                ", heureRendezVous='" + heureRendezVous + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idRV;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RendezVous other = (RendezVous) obj;
        return this.idRV == other.idRV;
    }

    public RendezVous(int idRV) {
        this.idRV = idRV;
    }
}
