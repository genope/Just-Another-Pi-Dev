/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Logements extends Offres{
    
    public enum type{
        Maison,
        Chambre,
        Appartement,
    }

    public Logements(String nom, String description, Date datedebut, Date datefin, float prix, boolean etat,enum type) {
        super(nom, description, datedebut, datefin, prix, etat);
        this.type=type;
    }

    @Override
    public String toString() {
        return super.toString()+"Logements{" +this.type '}';
    }
    
    
    
    
    
    
}
