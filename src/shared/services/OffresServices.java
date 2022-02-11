/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import java.sql.*;
import java.util.*;
import shared.connexion.MaConnexion;
import shared.entities.Offres;





public class OffresServices {
    Connection mc;
    PreparedStatement ste;

    public OffresServices() {
        this.mc = MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajoutOffres(Offres o){
        
        String req="insert into offres(nom,description,datedebut,datefin,prix,etat) Values(?,?,?,?,?,?)";
        
         try {
            ste=mc.prepareStatement(req);
            ste.setString(1, o.getNom());
            ste.setString(2, o.getDescription());
            ste.setDate(3, o.getDatedebut());
            ste.setDate(4, o.getDatefin());
            ste.setFloat(5, o.getPrix());
            ste.setBoolean(6, o.isEtat());
            ste.executeUpdate();
            System.out.println("Offre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Offres> getAllOffres(){
     List<Offres> offres = new ArrayList<>();
        String req="select * from offres";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres o = new Offres();
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDatedebut(rs.getDate("datedebut"));
                o.setDatefin(rs.getDate("datefin"));
                o.setPrix(rs.getFloat("prix"));
                o.setEtat(rs.getBoolean("etat"));
      
                offres.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return offres;
    }
    
    
    public void suuprimeroffre(int o){
        
        String req="DELETE FROM `offres` WHERE id=?";
        
        try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,o);
                ste.executeUpdate();
                System.out.println("Offre supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
        
    public void modifieroffr(int i,Offres o) {
        
        String req="UPDATE `offres`  SET `id`=? ,`nom`=?,`description`=?,"
                + "`datedebut`=?,`datefin`=?,`prix`=?,`etat`=? WHERE id=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setInt(1,i);
               ste.setString(2,o.getNom());
               ste.setString(3,o.getDescription());
               ste.setDate(4, o.getDatedebut());
               ste.setDate(5, o.getDatefin());
               ste.setFloat(6,o.getPrix());
               ste.setBoolean(7,o.isEtat());
               ste.setInt(8,+i);
               ste.executeUpdate();
               System.out.println("Offre modifier");
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
   
    
}
