package shared;

import java.sql.Date;
import java.util.List;
import shared.connexion.MaConnexion;
import shared.entities.enums.TypeDeTransport;
import shared.services.MoyenDeTransportService;
import shared.entities.*;
import shared.services.EvenementService;


public class Shared {


    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        
                  
        MoyenDeTransportService a = new MoyenDeTransportService();
        
        MoyenDeTransport mdt = new MoyenDeTransport(8, TypeDeTransport.Voiture, "Golf 6", "nnvspod", new Date(2009, 11, 12), new Date(2009, 11, 20), 0, true, "Sousse");
        MoyenDeTransport mdt1 = new MoyenDeTransport(68, TypeDeTransport.Moto, "Honda ", "aasdaex", new Date(2010, 1, 31), new Date(2010, 7, 3), 0, true, "Ariana");
        MoyenDeTransport mdt2 = new MoyenDeTransport(4, TypeDeTransport.Velo, "RockRider ", "sdqsrx", new Date(2010, 1, 28), new Date(2011, 8, 47), 0, true, "Tunis");

        
        EvenementService e = new EvenementService();
        Evenement event = new Evenement("Sidi 3mor", "Ba3be3i", "w mriguel", new Date(2021,9,18), new Date(2021,9,18), 0, true, "Ariana");
//        a.ajouterMoyenDeTransport(mdt);
//        a.ajouterMoyenDeTransport(mdt1);  
//        a.ajouterMoyenDeTransport(mdt2);  
        //a.modifierMoyenDeTransport(mdt1,14);
        //a.supprimerMoyenDeTransport(68);
//        List<MoyenDeTransport> l = a.afficherMoyenDeTransport();
//        
//        for(MoyenDeTransport moydetrans : l){
//            System.out.println(moydetrans);
//        }
          e.ajouterEvenement(event);

          e.afficherEvenement();
          
          List<Evenement> l = e.afficherEvenement();
          
//        
        for(Evenement even : l){
            System.out.println(even);
        }
//        System.out.println(a.getMoyenDeTransportByID(3));
    }
    
}
