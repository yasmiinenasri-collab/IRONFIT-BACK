/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;


public interface IserviceRV <T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    public T getOne(T t);
    public List<T> getAll(T t);
}
