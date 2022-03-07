package shared;

import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.connexion.MaConnexion;
import shared.entities.Commentaire;
import shared.entities.Publication;
import shared.services.CommentaireService;
import shared.services.PublicationService;


public class Shared extends Application {

//
//    
//        MaConnexion m = MaConnexion.getInstance();
//          Publication p =new Publication(1,1234567,"nom publication3", "description3", "image2", "adresse2",24, new Date(07, 02, 2021));
//        
//
//        Commentaire c=new Commentaire(4,14725836,11, "nom commentaire", "commentaireeeeetestt");
//        PublicationService ps = new PublicationService();
//        CommentaireService cs= new CommentaireService();
//       ps.ajouterPublication(p);
//      cs.ajouterCommentaire(c);
//        
//       cs.modifierCommentaire(c,2);
//         
//       ps.modifierPublication("soudani","wissal","xxxx","azerty",1);
//        
//        ps.supprimerPublication(8);
//      cs.supprimerCommentaire(3);
//      System.out.println(cs.afficherCommentaire());
//      System.out.println(ps.afficherPublication());
//        System.out.println(ps.filtrerRegion("MONASTIR")); 
//        System.out.println(ps.trierpublication()); 
//       System.out.println(ps.Recherchepublication("nom")); 
//    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/shared/GUI/AfficherPublication1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
       
    
}
