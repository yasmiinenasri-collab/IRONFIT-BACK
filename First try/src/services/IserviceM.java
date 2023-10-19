/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author AYMEN ASKRI
 */
interface IserviceM<M> {
        public void ajouter(M m);
    public void modifier(M m);
    public void supprimer(int id);
    public M getOne(M m);
    public List<M> getAll();
    
}
