package entite;

public class medecin {
    private int id;
    private String nomMED;
    private String prenomMED;
    private String specialite;
    private String adresse;
    private String email;
    private String tel; 

    public medecin(int id, String nom, String prenom, String specialite, String adresse, String email, String tel) {
        this.id = id;
        this.nomMED = nom;
        this.prenomMED = prenom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

    // Getters and setters (pour la classe Medecin)
     public void setId(int id) {
        this.id = id;
    }
     
    public int getId() {
        return id;
    }

    public void setNomMED(String nomMED) {
        this.nomMED = nomMED;
    }

    public String getNomMED() {
        return nomMED;
    }

    public void setPrenomMED(String prenomMED) {
        this.prenomMED = prenomMED;
    }

    public String getPrenomMED() {
        return prenomMED;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public medecin() {
    }

    public medecin(String nomMED, String prenomMED, String specialite, String adresse, String email, String tel) {
        this.nomMED = nomMED;
        this.prenomMED = prenomMED;
        this.specialite = specialite;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "medecin{" + "id=" + id + ", nomMED=" + nomMED + ", prenomMED=" + prenomMED + ", specialite=" + specialite + ", adresse=" + adresse + ", email=" + email + ", tel=" + tel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final medecin other = (medecin) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public medecin(int id) {
        this.id = id;
    }
    
}
