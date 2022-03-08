
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.text.SimpleDateFormat;
import shared.connexion.MaConnexion;
import shared.entities.*;
import shared.entities.enums.CategorieOffres;
import static shared.entities.enums.CategorieOffres.*;
import static shared.entities.enums.TypeOffres.*;
import shared.services.ChatServices;
import shared.services.OffreServices;
/**
 *
 * @author user
 */
public class Shared {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    
        MaConnexion m = MaConnexion.getInstance();
       
        // Logements log=new Logements(110405018,"iheb", "hhhh", new Date(2021, 02, 07), new Date(2021, 02, 07), 15f, true, "bizerte");
         
         //Logements logg=new Logements("l3ej", "hhhh", new Date(2021, 02, 07), new Date(2021, 02, 07), 15f, true, "bizerte");
         
       //Horeca hor=new Horeca(110405018,"l3ej", "jjjj", new Date(2021, 02, 07), new Date(2021, 02, 07), 0, true, "tunis", new Date(2021, 02, 07), new Date(2021, 02, 07));
       Offres of=new Offres(110405018, "l3ej", "le3j",new Date(2021, 02, 07) , new Date(2021, 02, 07), 15f, false, "bizerte", Chambre,"hhhh");
       
       Offres off=new Offres(110405018, "Restaurant ", "Laico",new Date(2021, 02, 07) , new Date(2021, 02, 07), 15f, true, "bizerte","jjjj");
       
      
        OffreServices offreService=new OffreServices();
        //offreService.modifierOffreById(9, off);
       // System.out.println( offreService.getAllMyOffres(110405018));
//        System.out.println("Nombre totales des offres = "+offreService.nombreOffres()); 
//        System.out.println("Nombre d'offres par host = "+offreService.getNbrMyOffres(110405018));
//        System.out.println("Nombre de différent type d'offre par host = "+offreService.getNbrMyLogements(110405018,Horeca));
    // offreService.ajoutOffre(of);
       // System.out.println(offreService.getAllOffres(Maison));
     
       // System.out.println(offreService.getAllMaisons());
       // System.out.println(offreService.getAllOffresById(9));      
              System.out.println(offreService.getAllOffresById2(110405018));    
            //offreService.modifierOffreById(7, of);
      // Horeca horr=new Horeca("hhhhhh", "jjjj", new Date(2021, 02, 07), new Date(2021, 02, 07), 0, true, "tunis", new Date(2021, 02, 07), new Date(2021, 02, 07));
       // System.out.println(log);
      
    
        
    //   SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
      //  Date date = new Date(System.currentTimeMillis());
        // System.out.println(formatter.format(date));
        
       
        
        

            ChatServices chat=new ChatServices();
        
        //logService.modifierLogementById(15, logg);
        
     //   horService.modifierHorecatById(4, horr);
       // System.out.println(horService.getHorecaById(4));  
      //  horService.ajoutHoreca(hor);
    // logService.ajoutLogement(log);
       // chat.ajoutLogement(c1);
        //chat.suuprimerChat(4);
     //   System.out.println(chat.getChat(4));
        // chat.ajoutLogement(c1);
     //   horService.ajoutHoreca(hor);
      //  System.out.println(logService.getAllMaison());
    //logService.ajoutLogement(log);
      //System.out.println(logService.getAllMaison());
       // logService.suuprimerLogement(12);
         //System.out.println(logService.getAllAppartement());
         
         //System.out.println(logService.getAllLogements());
         //os.suuprimeroffre(1);
         //os.modifieroffr(2, o2);
      
         }
    }
    

