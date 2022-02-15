/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.*;




public class Horeca extends Offres {
    
    private Date dateOuverture;
    private Date dateFermeture;
    

    public Horeca(int idhost,String nom, String description, Date datedebut, Date datefin, float prix, boolean etat, String ville,Date dateOuverture,Date dateFermrture) {
        super(idhost,nom, description, datedebut, datefin, prix, etat, ville);
        this.dateOuverture=dateOuverture;
        this.dateFermeture=dateFermrture;
    }

    public Horeca(String nom, String description, Date datedebut, Date datefin, float prix, boolean etat, String ville,Date dateOuverture,Date dateFermrture) {
        super(nom, description, datedebut, datefin, prix, etat, ville);
        this.dateOuverture=dateOuverture;
        this.dateFermeture=dateFermrture;
    }
    public Horeca() {
        super();
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    @Override
    public String toString() {
        return "Horeca"+super.toString()+" ,dateOuverture=" + dateOuverture + ", dateFermeture=" + dateFermeture + '}';
    }
    
    
    
}
