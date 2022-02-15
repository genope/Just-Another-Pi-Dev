package shared;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.Commentaire;
import shared.entities.Publication;
import shared.services.CommentaireService;
import shared.services.PublicationService;


public class Shared {


    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
          Publication p =new Publication(1,14725836,"nom publication2", "description2", "image2", "adresse2",24, new Date(07, 02, 2021));
        

        Commentaire c=new Commentaire(4,14725836,11, "nom commentaire", "commentaireeeeetestt");
        PublicationService ps = new PublicationService();
        CommentaireService cs= new CommentaireService();
      // ps.ajouterPublication(p);
      cs.ajouterCommentaire(c);
        
        // cs.modifierCommentaire(c,2);
         
       // ps.modifierPublication("soudani","wissal","xxxx","azerty",1);
        
       // ps.supprimerPublication(8);
     // cs.supprimerCommentaire(3);
       System.out.println(cs.afficherCommentaire());
      //System.out.println(ps.afficherPublication());
    }
    
       
    
}
