/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;

public class Offres {
    private int id;
    private int id_user;
    private String nom;
    private String description;
   // private String File;
    private Date datedebut;
    private Date datefin;
    private float prix;
    private boolean etat;
    private String ville;

    public Offres(String nom, String description, Date datedebut, Date datefin, float prix, boolean etat,String ville) {
        this.nom = nom;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.etat = etat; 
        this.ville=ville;
   }

    public Offres( int id_user, String nom, String description, Date datedebut, Date datefin, float prix, boolean etat, String ville) {
//        this.id = id;
        this.id_user = id_user;
        this.nom = nom;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.etat = etat;
        this.ville = ville;
    }
    

    public Offres() {
    }

    @Override
    public String toString() {
        return "id_host = "+ id_user +  "id ="+ id +", nom=" + nom + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + ", prix=" + prix + ", etat=" + etat + ", ville=" + ville ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    
}