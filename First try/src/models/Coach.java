

package models;
import java.util.List;

public class Coach {
    private int IdUtilisateur;
    private List<Planning> planning;

    public Coach(int IdUtilisateur, List<Planning> planning) {
        this.IdUtilisateur = IdUtilisateur;
        
        this.planning = planning;
    }

    public Coach( List<Planning> planning) {
       
        this.planning = planning;
    }

    public Coach() {
    }

    public int getIdUtilisateur() {
        return IdUtilisateur;
    }

   
    public List<Planning> getPlanning() {
        return planning;
    }

    public void setIdUtilisateur(int IdUtilisateur) {
        this.IdUtilisateur = IdUtilisateur;
    }

    

    public void setPlanning(List<Planning> planning) {
        this.planning = planning;
    }

    @Override
    public String toString() {
        return "Coach{" + "IdUtilisateur=" + IdUtilisateur + ", planning=" + planning + '}';
    }
}