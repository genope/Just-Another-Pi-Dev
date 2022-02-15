/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;

public class Offres {
    
    
    private int id;
    private int idhost;
    private String nom;
    private String description;
   // private String File;
    private Date datedebut;
    private Date datefin;
    private float prix;
    private boolean etat;
    private String ville;

    public Offres(int idhost,String nom, String description, Date datedebut, Date datefin, float prix, boolean etat ,String ville) {
        this.idhost=idhost;
        this.nom = nom;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.etat = etat;
        this.ville=ville;
    }
    
    
    
        public Offres(String nom, String description, Date datedebut, Date datefin, float prix, boolean etat ,String ville) {
        
        this.nom = nom;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.etat = etat;
        this.ville=ville;
    }

    public Offres() {
        
    }

    @Override
    public String toString() {
        return "{ id="+this.id+", id_host="+this.idhost + ", nom=" + nom + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + ", prix=" + prix + ", etat=" + etat + ", ville="+this.ville;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public float getPrix() {
        return prix;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public String getVille() {
        return ville;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setIdhost(int idhost) {
        this.idhost = idhost;
    }

    public int getIdhost() {
        return idhost;
    }
    
    
    
}
