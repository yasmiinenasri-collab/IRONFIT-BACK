
package models;

public class Rendez_vous {
    private int id_RDV;
    private int id_coach; 
    private int id_client;
    private String date_RDV;
    private String time_RDV;

    public Rendez_vous(int id_RDV, int id_coach, int id_client, String date_RDV, String time_RDV) {
        this.id_RDV = id_RDV;
        this.id_coach = id_coach;
        this.id_client = id_client;
        this.date_RDV = date_RDV;
        this.time_RDV = time_RDV;
    }

    public Rendez_vous(int id_coach, int id_client, String date_RDV, String time_RDV) {
        this.id_coach = id_coach;
        this.id_client = id_client;
        this.date_RDV = date_RDV;
        this.time_RDV = time_RDV;
    }

    public Rendez_vous() {
    }

    public int getId_RDV() {
        return id_RDV;
    }

    public int getId_coach() {
        return id_coach;
    }

    public int getId_client() {
        return id_client;
    }

    public String getDate_RDV() {
        return date_RDV;
    }

    public String getTime_RDV() {
        return time_RDV;
    }

    public void setId_RDV(int id_RDV) {
        this.id_RDV = id_RDV;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setDate_RDV(String date_RDV) {
        this.date_RDV = date_RDV;
    }

    public void setTime_RDV(String time_RDV) {
        this.time_RDV = time_RDV;
    }

    @Override
    public String toString() {
        return "Rendez_vous{" + "id_RDV=" + id_RDV + ", id_coach=" + id_coach + ", id_client=" + id_client + ", date_RDV=" + date_RDV + ", time_RDV=" + time_RDV + '}';
    }
  
}
