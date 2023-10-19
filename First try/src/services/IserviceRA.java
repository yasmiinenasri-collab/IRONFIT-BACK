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
 * @param <RA>
 */

public interface IserviceRA <RA> {
    public void ajouter(RA ra);
    public void modifier(RA ra);
    public void supprimer(int id);
    public RA getOne(RA ra);
    public List<RA> getAll(RA ra);
}
