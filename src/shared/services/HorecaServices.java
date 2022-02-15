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
import shared.entities.Horeca;
import shared.entities.Logements;

/**
 *
 * @author user
 */
public class HorecaServices {
    Connection mc;
      PreparedStatement ste;

    public HorecaServices() {
        this.mc = MaConnexion.getInstance().getCnx();
    }
    
           public void ajoutHoreca(Horeca h){
        
        String req="insert into horeca(nom,description,datedebut,datefin,prix,etat,ville,dateOuverture,dateFermeture,id_host) Values(?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -22801 MONTH),?,?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -22801 MONTH),?)";
        
         try {
            ste=mc.prepareStatement(req);
            ste.setString(1, h.getNom());
            ste.setString(2, h.getDescription());
            ste.setDate(3, h.getDatedebut());
            ste.setDate(4, h.getDatefin());
            ste.setFloat(5, h.getPrix());
            ste.setBoolean(6, h.isEtat());
            ste.setString(7, h.getVille());
            ste.setDate(8, h.getDateOuverture());
            ste.setDate(9, h.getDateFermeture());
            ste.setInt(10, h.getIdhost());
            ste.executeUpdate();
            System.out.println("Horeca ajoutée");
        } catch (SQLException ex) {
             System.out.println(ste);
            System.out.println(ex.getMessage());
        }
    }
           
           
           public void suuprimerLogement(int h){
        
        String req="DELETE FROM `horeca` WHERE id=?";
        
        try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,h);
                ste.executeUpdate();
                System.out.println("Horeca supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
           
           
              public void modifierHorecatById(int i,Horeca o) {
        
        String req="UPDATE `horeca`  SET `nom`=?,`description`=?,"
                + "`datedebut`=DATE_ADD(?, INTERVAL -22801 MONTH),`datefin`=DATE_ADD(?, INTERVAL -22801 MONTH),`prix`=?,`etat`=?,`ville`=? ,`dateOuverture`=DATE_ADD(?, INTERVAL -22801 MONTH),`dateFermeture`=DATE_ADD(?, INTERVAL -22801 MONTH)   WHERE id=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setString(1,o.getNom());
               ste.setString(2,o.getDescription());
               ste.setDate(3, o.getDatedebut());
               ste.setDate(4, o.getDatefin());
               ste.setFloat(5,o.getPrix());
               ste.setBoolean(6,o.isEtat());
               ste.setString(7,o.getVille());
               ste.setDate(8,o.getDateOuverture());
               ste.setDate(9,o.getDateOuverture());
               ste.setInt(10,i);
               ste.executeUpdate();
               System.out.println("Horeca modifier");
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
              
              
              public List<Horeca> getAllHoreca(){
     List<Horeca> Horecas = new ArrayList<>();
        String req="SELECT * FROM `horeca`";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Horeca horeca = new Horeca();
                
                horeca.setNom(rs.getString("nom"));
                horeca.setDescription(rs.getString("description"));
                horeca.setDatedebut(rs.getDate("datedebut"));
                horeca.setDatefin(rs.getDate("datefin"));
                horeca.setPrix(rs.getFloat("prix"));
                horeca.setEtat(rs.getBoolean("etat"));
                horeca.setVille(rs.getString("ville"));
                horeca.setDateOuverture(rs.getDate("dateOuverture"));
                horeca.setDateFermeture(rs.getDate("dateFermeture"));
                horeca.setIdhost(rs.getInt("id_host"));
                horeca.setId(rs.getInt("id"));
      
             
              Horecas.add(horeca);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
        return Horecas;
    } 
              
              
              
              public Horeca getHorecaById(int i){
            String req="SELECT * FROM `horeca` WHERE id=?";
             Horeca horec = new Horeca();
             
            try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,i);
                ResultSet rs=ste.executeQuery();
                while(rs.next()){                
                horec.setNom(rs.getString("nom"));
                horec.setDescription(rs.getString("description"));
                horec.setDatedebut(rs.getDate("datedebut"));
                horec.setDatefin(rs.getDate("datefin"));
                horec.setPrix(rs.getFloat("prix"));
                horec.setEtat(rs.getBoolean("etat"));
                horec.setVille(rs.getString("ville"));
                horec.setDateOuverture(rs.getDate("dateOuverture"));
                horec.setDateFermeture(rs.getDate("dateFermeture"));
                horec.setIdhost(rs.getInt("id_host"));
                
                horec.setId(rs.getInt("id"));
                
                    System.out.println("hhhh");
            }  
            } catch (Exception e) {
                System.out.println(ste);
                System.out.println(e.getMessage());
            }
            return horec;
          
        }
              
              
             
}
