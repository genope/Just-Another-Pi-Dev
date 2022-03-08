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
public class Avis {
    int id,idGuest,idOffre,Note;
    Date dateCreation;

    public Avis() {
    }

    public Avis(int id, int idGuest, int idOffre, int Note, Date dateCreation) {
        this.id = id;
        this.idGuest = idGuest;
        this.idOffre = idOffre;
        this.Note = Note;
        this.dateCreation = dateCreation;
    }

    public Avis(int idGuest, int idOffre, int Note, Date dateCreation) {
        this.idGuest = idGuest;
        this.idOffre = idOffre;
        this.Note = Note;
        this.dateCreation = dateCreation;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int idAvis) {
        this.id = idAvis;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getNote() {
        return Note;
    }

    public void setNote(int Note) {
        this.Note = Note;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idGuest=" + idGuest + ", idOffre=" + idOffre + ", Note=" + Note + ", dateCreation=" + dateCreation + '}';
    }
    

    
 
}