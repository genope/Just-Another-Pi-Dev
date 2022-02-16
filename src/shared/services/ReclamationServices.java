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
import shared.entities.Reclamation;
import shared.entities.enums.Statut;

/**
 *
 * @author user
 */
public class ReclamationServices {
        Connection mc;
        PreparedStatement ste;

    public ReclamationServices() {
        mc= MaConnexion.getInstance().getCnx();
    }

        public void ajouterReclamation(Reclamation R) {

        try { 

            String requete = "INSERT INTO `reclamation`(`id`, `idUser`, `idOffre`, `dateCreation`, `dateTraitement`, `objet`, `description`, `statut`, `email`, `image`) VALUES (?,?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -22801 MONTH),?,?,?,?,?)";
            ste=mc.prepareStatement(requete);
            ste.setInt(1, R.getId());
            ste.setInt(2, R.getIdUser());
            ste.setInt(3, R.getIdOffre());
            ste.setDate(4, R.getDateCreation());
            ste.setDate(5, R.getDateTraitement());
            ste.setString(6, R.getDescription());
            ste.setString(7, R.getObjet());
            ste.setString(8, R.getStatut().toString());
            ste.setString(9, R.getEmail());
            ste.setString(10, R.getImage());
            ste.executeUpdate();

            System.out.println("Reclamation Ajoutée");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
        public List<Reclamation> afficherReclamation(){
     List<Reclamation> Reclamation = new ArrayList<>();
        String requete="select * from reclamation";
         try {
            ste=mc.prepareStatement(requete);;
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reclamation R;
                R = new Reclamation(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("idOffre"),
                        rs.getDate("DateCreation"),
                        rs.getDate("DateTraitement"),
                        rs.getString("Description"),
                        rs.getString("Objet"),
                        Statut.valueOf(rs.getString("Statut")),
                        rs.getString("Email"),
                        rs.getString("Image"));
                Reclamation.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Reclamation;
    }
    
    
    public void supprimerReclamation(int id){
        
        String requete="DELETE FROM `reclamation` WHERE id=?";
        
        try {
                ste=mc.prepareStatement(requete);
                ste.setInt(1,id);
                ste.executeUpdate();
                System.out.println("Reclamation supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
        
    public void modifierReclamation(int id,Reclamation R) {
        
        String req="UPDATE `reclamation` SET `idUser`=?,`idOffre`=?,`dateCreation`=DATE_ADD(?, INTERVAL -22801 MONTH),`dateTraitement`=DATE_ADD(?, INTERVAL -22801 MONTH),`objet`=?,`description`=?,`statut`=?,`email`=?,`image`=? WHERE id=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setInt(1, R.getIdUser());
               ste.setInt(2, R.getIdOffre());
               ste.setDate(3, R.getDateCreation());
               ste.setDate(4, R.getDateTraitement());
               ste.setString(5, R.getDescription());
               ste.setString(6, R.getObjet());
               ste.setString(7, R.getStatut().toString());
               ste.setString(8, R.getEmail());
               ste.setString(9, R.getImage());
               ste.setInt(10, id);
               ste.executeUpdate();
               System.out.println("Reclamation modifiée");
               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
}
}
