/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author MSI
 */
public class Client {
    
    private int id_client;
    

    public Client(int id_client, int taille, int poids) {
        this.id_client = id_client;
        
    }

   
    public Client() {
    }

    public int getId_client() {
        return id_client;
    }

   

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

  

    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client  + '}';
    }
    
    
    
}
