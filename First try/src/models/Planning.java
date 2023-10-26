
package models;

public class Planning{ 
    private int idPlanning;
     private String niveauProgramme;
     private String programme ;
     private int id_coach;
     private float prix;
     private byte[] image;
     private String videoLink;
     private String description;
     private int views;


    public Planning(int idPlanning, String niveauProgramme, String programme, int id_coach, float prix, byte[] image, String videoLink, String description) {
        this.idPlanning = idPlanning;
        this.niveauProgramme = niveauProgramme;
        this.programme = programme;
        this.id_coach = id_coach;
        this.prix = prix;
        this.image = image;
        this.videoLink = videoLink;
        this.description = description;
    }

    public Planning(String niveauProgramme, String programme, int id_coach, float prix, byte[] image, String videoLink, String description) {
        this.niveauProgramme = niveauProgramme;
        this.programme = programme;
        this.id_coach = id_coach;
        this.prix = prix;
        this.image = image;
        this.videoLink = videoLink;
        this.description = description;
    }

    public Planning() {
    }

    public int getIdPlanning() {
        return idPlanning;
    }

    public String getNiveauProgramme() {
        return niveauProgramme;
    }

    public String getProgramme() {
        return programme;
    }

    public int getId_coach() {
        return id_coach;
    }

    public float getPrix() {
        return prix;
    }

    public byte[] getImage() {
        return image;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public void setNiveauProgramme(String niveauProgramme) {
        this.niveauProgramme = niveauProgramme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }
public int getViews() {
    return views;
}

public void setViews(int views) {
    this.views = views;
}

public void incrementViews() {
    this.views++;
}

    @Override
    public String toString() {
        return "Planning{" + "idPlanning=" + idPlanning + ", niveauProgramme=" + niveauProgramme + ", programme=" + programme + ", id_coach=" + id_coach + ", prix=" + prix + ", image=" + image + ", videoLink=" + videoLink + ", description=" + description + '}';
    }

    

}