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
       //  Publication p =new Publication(2,2,"louhichi", "description", "image", "adresse", 5, new Date(07, 02, 2021));
        // Publication p2 =new Publication(4,5,"Boughnim", "description", "image", "adresse", 5);

        Commentaire c=new Commentaire(3, 2,3, "wissal", "commentaireeeeewissal");
        PublicationService ps = new PublicationService();
        CommentaireService cs= new CommentaireService();
      // ps.ajouterPublication(p);
       cs.ajouterCommentaire(c);
        
        // cs.modifierCommentaire(c,2);
         
       // ps.modifierPublication("soudani","wissal","xxxx","azerty",1);
       // System.out.println(ps.afficherPublication());
      //  ps.supprimerPublication(2);
      cs.supprimerCommentaire(3);
       System.out.println(cs.afficherCommentaire());
         }
    
}