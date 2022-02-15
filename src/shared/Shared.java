package shared;

import shared.entities.ReservationLogement;
import shared.entities.ReservationMoyenTransport;
import shared.services.ReservationMoyenTransportService;
import shared.services.ReservationLogementService;
import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.ReservationEvent;
import shared.entities.ReservationHoreca;
import shared.services.ReservationEventService;
import shared.services.ReservationHorecaService;


public class Shared {


    public static void main(String[] args) {
        
        MaConnexion m = MaConnexion.getInstance();
       java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
           
       
        ReservationMoyenTransportService mts = new ReservationMoyenTransportService();
        //mts.ajouterReservationMT(mt);
        ReservationMoyenTransport mt1=new ReservationMoyenTransport(3,130,14,sqlTS,Date.valueOf("2022-03-15"));
        //mts.ajouterReservationMT(mt1);
         //System.out.println(mts.afficherReservationMT()); 
        //mts.modifierReservationMT(new ReservationMoyenTransport(3,15,16,sqlTS,Date.valueOf("2010-12-10")),3);
        //System.out.println("apres modif" +mts.afficherReservationMT()); 
        //int sup=mts.supprimerReservationMoyenTransport(3);
    
       
       ReservationLogementService ms = new ReservationLogementService();
       ReservationLogement ml=new ReservationLogement(1,4,5,sqlTS,Date.valueOf("2020-03-20"));
       // ms.ajouterReservationL(ml);
      //  ms.modifierReservationMT(new ReservationLogement(2,3,4,sqlTS,Date.valueOf("2011-12-10")),1);
      //  int sup=ms.supprimerReservationMoyenTransport(1);

      ReservationHorecaService hs = new ReservationHorecaService();
       ReservationHoreca mh=new ReservationHoreca(1,4,5,sqlTS,Date.valueOf("2020-03-20"));
           //  hs.ajouterReservationL(mh);
      //  hs.modifierReservationHoreca(new ReservationHoreca(2,3,4,sqlTS,Date.valueOf("2011-12-10")),1);
       // int sup=hs.supprimerReservationHoreca(1);
       // int sup=hs.supprimerReservationHoreca(1);

        
 ReservationEventService vs = new ReservationEventService();
       ReservationEvent mv=new ReservationEvent(1,4,5,sqlTS,Date.valueOf("2020-03-20"));
     //   vs.ajouterReservationE(mv);
       // vs.modifierReservationEvent(new ReservationEvent(2,3,4,sqlTS,Date.valueOf("2011-12-10")),1);
        int sup=vs.supprimerReservationEvent(1);
              
        
        
    }
    
}
