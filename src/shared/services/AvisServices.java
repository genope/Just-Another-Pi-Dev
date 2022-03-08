/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shared.connexion.MaConnexion;
import shared.entities.Avis;

/**
 *
 * @author user
 */
public class AvisServices {
    Connection mc;
    PreparedStatement ste;

    public AvisServices() {
        mc= MaConnexion.getInstance().getCnx();
    }
            public void ajouterAvis(Avis A) {

        try { 

            String requete = "INSERT INTO `avis`(`id`,`idGuest`,`idOffre`,`note`,`dateCreation`) VALUES (?,?,?,?,DATE_ADD(?, INTERVAL -22801 MONTH))";
            ste=mc.prepareStatement(requete);
            ste.setInt(1, A.getId());
            ste.setInt(2, A.getIdGuest());
            ste.setInt(3, A.getIdOffre());
            ste.setInt(4, A.getNote()); 
            ste.setDate(5, A.getDateCreation());
            ste.executeUpdate();
            System.out.println("Avis Ajouté");
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public List<Avis> afficherAvis(){
        List<Avis> Avis = new ArrayList<>();
        String requete="select * from avis";
         try {
            ste=mc.prepareStatement(requete);;
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Avis A = new Avis();
                A.setId(rs.getInt("id"));
                A.setIdGuest(rs.getInt("idGuest"));
                A.setIdOffre(rs.getInt("idOffre"));
                A.setNote(rs.getInt("note"));
                A.setDateCreation(rs.getDate("DateCreation"));
                Avis.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Avis;
    }
    public void supprimerAvis(int id){
        
        String requete="DELETE FROM `avis` WHERE id=?";
        
        try {
                ste=mc.prepareStatement(requete);
                ste.setInt(1,id);
                ste.executeUpdate();
                System.out.println("Avis supprimé");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }    
     public void modifierAvis(int id,Avis A) {
        
        String req="UPDATE `avis` SET `idGuest`=?,`idOffre`=?,`note`=?,`dateCreation`=DATE_ADD(?, INTERVAL -22801 MONTH) WHERE id=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setInt(1, A.getIdGuest());
               ste.setInt(2, A.getIdOffre());
               ste.setInt(3, A.getNote());
               ste.setDate(4, A.getDateCreation());
               ste.setInt(5, A.getId());
               ste.executeUpdate();
               System.out.println("Avis modifié");
               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
}
         
}
