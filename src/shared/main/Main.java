/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.main;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.Commentaire;
import shared.entities.Publication;
import shared.services.CommentaireService;
import shared.services.PublicationService;



/**
 *
 * @author user
 */
public class Main {    
    public static void main(String[] args) {
        
        MaConnexion m = MaConnexion.getInstance();
        PublicationService ps = new PublicationService();
    CommentaireService cs= new CommentaireService();
   // System.out.println(ps.filtrerRegion("MONASTIR"));
    Commentaire c=new Commentaire(15,14725836,11, "nom commentaire", "commentaireeeeetestt",2);
    //cs.ajouterCommentaire(c);
        System.out.println(cs.GEtMoyRating(11));
         }
    
}