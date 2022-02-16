 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;
import shared.entities.enums.Statut;

/**
 *
 * @author user
 */
public class Reclamation {
    private int id,idOffre,idUser;
    private Date dateCreation,dateTraitement;
    private String description,objet;
    private Statut statut;
    private String email,image;
    

    public Reclamation() {
    }

    public Reclamation(int id, int idOffre, int idUser, Date dateCreation, Date dateTraitement, String description, String objet, Statut statut, String email, String image) {
        this.id = id;
        this.idOffre = idOffre;
        this.idUser = idUser;
        this.dateCreation = dateCreation;
        this.dateTraitement = dateTraitement;
        this.description = description;
        this.objet = objet;
        this.statut = statut;
        this.email = email;
        this.image = image;
    }

    public Reclamation(int idOffre, int idUser, Date dateCreation, Date dateTraitement, String description, String objet, Statut statut, String email, String image) {
        this.idOffre = idOffre;
        this.idUser = idUser;
        this.dateCreation = dateCreation;
        this.dateTraitement = dateTraitement;
        this.description = description;
        this.objet = objet;
        this.statut = statut;
        this.email = email;
        this.image = image;
    }
    


    

    
    
    

//   // public Reclamation(int idOffre, int idUser, Date dateCreation, Date dateTraitement, String description, String objet, String statut, String email, String image) {
//        this.idOffre = idOffre;
//        this.idUser = idUser;
//        this.dateCreation = dateCreation;
//        this.dateTraitement = dateTraitement;
//        this.description = description;
//        this.objet = objet;
//        this.statut = statut;
//        this.email = email;
//        this.image = image;
//    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdUser() {
        return idUser;
    }

//   // public Reclamation(int idReclamation, int idGuest, int idHost, int idLogement, int idMDT, int idHoreca, int idEvenement, Date dateCreation, Date dateTraitement, String description, String objet, String statut, String email, String image) {
//        this.idReclamation = idReclamation;
//        this.idOffre = idOffre;
//        //this.idGuest = idGuest;
//        //this.idHost = idHost;
//        //this.idLogement = idLogement;
//        //this.idMDT = idMDT;
//        //this.idHoreca = idHoreca;
//        //this.idEvenement = idEvenement;
//        this.dateCreation = dateCreation;
//        this.dateTraitement = dateTraitement;
//        this.description = description;
//        this.objet = objet;
//        this.statut = statut;
//        this.email = email;
//        this.image = image;
//    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setIdReclamation(int id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idOffre=" + idOffre + ", idUser=" + idUser + ", dateCreation=" + dateCreation + ", dateTraitement=" + dateTraitement + ", description=" + description + ", objet=" + objet + ", email=" + email + ", image=" + image + ", statut=" + statut + '}';
    }

   
    
    
    


}

