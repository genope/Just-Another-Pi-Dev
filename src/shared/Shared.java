
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
import static shared.entities.enums.TypeLogements.*;
import shared.services.ChatServices;
import shared.services.HorecaServices;
import shared.services.LogementsServices;
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
       
         Logements log=new Logements(110405018,"iheb", "hhhh", new Date(2021, 02, 07), new Date(2021, 02, 07), 15f, true, "bizerte", Maison);
         
         Logements logg=new Logements("l3ej", "hhhh", new Date(2021, 02, 07), new Date(2021, 02, 07), 15f, true, "bizerte", Maison);
         
       Horeca hor=new Horeca(110405018,"l3ej", "jjjj", new Date(2021, 02, 07), new Date(2021, 02, 07), 0, true, "tunis", new Date(2021, 02, 07), new Date(2021, 02, 07));
       
       
       
       Horeca horr=new Horeca("hhhhhh", "jjjj", new Date(2021, 02, 07), new Date(2021, 02, 07), 0, true, "tunis", new Date(2021, 02, 07), new Date(2021, 02, 07));
       // System.out.println(log);
      
        LogementsServices logService=new LogementsServices();
        HorecaServices horService = new HorecaServices();
        
    //   SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
      //  Date date = new Date(System.currentTimeMillis());
        // System.out.println(formatter.format(date));
        
       Chat c1=new Chat(110405018, 110405018, "bonjour");
        
        
        
        
        ChatServices chat=new ChatServices();
        
        //logService.modifierLogementById(15, logg);
        
     //   horService.modifierHorecatById(4, horr);
        System.out.println(horService.getHorecaById(4));  
      //  horService.ajoutHoreca(hor);
        //chat.ajoutLogement(c1);
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
    

