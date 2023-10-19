package Models;

public class RegimeAli {
    private int id;
    private double prixRegime;
    private String typeRegime;
    private String nomMED;   
    private String prenomMED;  
    private int idMED;


    public RegimeAli(int id, double prixRegime, String typeRegime, String nomMED, String prenomMED, int idMED) {
        this.id = id;
        this.prixRegime = prixRegime;
        this.typeRegime = typeRegime;
        this.nomMED = nomMED;     
        this.prenomMED = prenomMED; 
        this.idMED = idMED;
    }

  
    public RegimeAli(double prixRegime, String typeRegime, String nomMED, String prenomMED, int idMED) {
        this.prixRegime = prixRegime;
        this.typeRegime = typeRegime;
        this.nomMED = nomMED;     
        this.prenomMED = prenomMED; 
        this.idMED = idMED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrixRegime() {
        return prixRegime;
    }

    public void setPrixRegime(double prixRegime) {
        this.prixRegime = prixRegime;
    }

    public String getTypeRegime() {
        return typeRegime;
    }

    public void setTypeRegime(String typeRegime) {
        this.typeRegime = typeRegime;
    }

    public int getIdMED() {
        return idMED;
    }

    public void setIdMED(int idMED) {
        this.idMED = idMED;
    }

    public String getNomMED() {
        return nomMED;
    }

    public void setNomMED(String nomMED) {
        this.nomMED = nomMED;
    }

    public String getPrenomMED() {
        return prenomMED;
    }

    public void setPrenomMED(String prenomMED) {
        this.prenomMED = prenomMED;
    }

    @Override
    public String toString() {
        return "RegimeAli{" +
                "id=" + id +
                ", prixRegime=" + prixRegime +
                ", typeRegime='" + typeRegime + '\'' +
                ", nomMED='" + nomMED + '\'' +
                ", prenomMED='" + prenomMED + '\'' +
                ", idMED=" + idMED +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        RegimeAli other = (RegimeAli) obj;
        return this.id == other.id;
    }

    public RegimeAli(int id) {
        this.id = id;
    }

    public RegimeAli() {
    }
    
}