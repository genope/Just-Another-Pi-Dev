/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.main;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.*;
import shared.services.OffresServices;

/**
 *
 * @author user
 */
public class Main {    
    public static void main(String[] args) {
          MaConnexion m = MaConnexion.getInstance();
          //Offres o1=new Offres("bent somai", "aasfourti", new Date(07, 02, 2021), new Date(06, 12, 2000), 15f, true);
          //Offres o2=new Offres("aasfourti", "NHEBEK", new Date(07, 02, 2021), new Date(06, 12, 2000), 15f, true);
          //OffresServices os=new OffresServices();
         //os.ajoutOffres(o1);
         
         //System.out.println(os.getAllOffres());
         
         
         //os.suuprimeroffre(1);
         //os.modifieroffr(2, o2);
         Logements i=new Logements( "hhh",  "ggggg", new Date(07, 02, 2021), new Date(06, 12, 2000),  15f,  true,Maison);
         System.out.println(i);
         }
    
}