/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;
import shared.entities.enums.TypeLogements;

/**
 *
 * @author user
 */
public class Logements extends Offres{
    private TypeLogements type;
   

    public Logements(int idhost,String nom, String description, Date datedebut, Date datefin, float prix, boolean etat,String ville,TypeLogements type) {
        super(idhost,nom, description, datedebut, datefin, prix, etat,ville);
        this.type=type;
    }
    
       public Logements(String nom, String description, Date datedebut, Date datefin, float prix, boolean etat,String ville,TypeLogements type) {
        super(nom, description, datedebut, datefin, prix, etat,ville);
        this.type=type;
    }
    
    
    

    public Logements() {
       super();
    }

    @Override
    public String toString() {
        return "Logements"+super.toString()+", type=" +this.type+ '}';
    }

    public TypeLogements getType() {
        return type;
    }

    public void setType(TypeLogements type) {
        this.type = type;
    }
    
    
    
    
    
    
}
