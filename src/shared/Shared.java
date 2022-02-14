
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.*;
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
       
  Logements log=new Logements("iheb", "hhhh", new Date(2021, 02, 07), new Date(2021, 02, 07), 15f, true, "bizerte", "Maison");
         Horeca hor=new Horeca("l3ej", "jjjj", new Date(2021, 02, 07), new Date(2021, 02, 07), 0, true, "tunis", new Date(2021, 02, 07), new Date(2021, 02, 07));
  
        LogementsServices logService=new LogementsServices();
        HorecaServices horService = new HorecaServices();
     //   horService.ajoutHoreca(hor);
        
      //  logService.ajoutLogement(log);
       System.out.println(logService.getAllMaison());
       // logService.suuprimerLogement(12);
         //System.out.println(logService.getLogementById(11));
         
         
         //os.suuprimeroffre(1);
         //os.modifieroffr(2, o2);
      
         }
    }
    

