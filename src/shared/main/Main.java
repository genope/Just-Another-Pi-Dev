/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.main;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.Publication;
import shared.services.PublicationService;


/**
 *
 * @author user
 */
public class Main {    
    public static void main(String[] args) {
        
        MaConnexion m = MaConnexion.getInstance();
       // Publication p =new Publication(2,2,"louhichi", "description", "image", "adresse", 5, new Date(07, 02, 2021));
        PublicationService ps = new PublicationService();
       // ps.ajouterPublication(p);
        
       // ps.modifierPersonne("soudani","wissal","xxxx","azerty",1);
        System.out.println(ps.afficherPublication());
        ps.supprimerPersonne(2);
         }
    
}