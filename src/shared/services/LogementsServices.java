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
import java.util.logging.LogManager;
import shared.connexion.MaConnexion;
import shared.entities.Logements;
import shared.entities.Offres;

/**
 *
 * @author user
 */
public class LogementsServices {
      Connection mc;
      PreparedStatement ste;

    public LogementsServices() {
        this.mc = MaConnexion.getInstance().getCnx();
    }
    
       public void ajoutLogement(Logements l){
        
        String req="insert into logement(nom,description,datedebut,datefin,prix,etat,ville,type) Values(?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -22801 MONTH),?,?,?,?)";
        
         try {
            ste=mc.prepareStatement(req);
            ste.setString(1, l.getNom());
            ste.setString(2, l.getDescription());
            ste.setDate(3, l.getDatedebut());
            ste.setDate(4, l.getDatefin());
            ste.setFloat(5, l.getPrix());
            ste.setBoolean(6, l.isEtat());
            ste.setString(7, l.getVille());
            ste.setString(8, l.getType());
            ste.executeUpdate();
            System.out.println("Logement ajoutée");
        } catch (SQLException ex) {
             System.out.println(ste);
            System.out.println(ex.getMessage());
        }
    }
       
       
       
        public List<Logements> getAllMaison(){
     List<Logements> log = new ArrayList<>();
        String req="select * from logement where type='Maison'";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Logements o = new Logements();
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDatedebut(rs.getDate("datedebut"));
                o.setDatefin(rs.getDate("datefin"));
                o.setPrix(rs.getFloat("prix"));
                o.setEtat(rs.getBoolean("etat"));
                o.setVille(rs.getString("ville"));
                o.setType(rs.getString("type"));
                o.setId(rs.getInt("id"));
                log.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return log;
    }
        
        public List<Logements> getAllAppartement(){
     List<Logements> log = new ArrayList<>();
        String req="select * from logement where type='Appartement'";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Logements o = new Logements();
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDatedebut(rs.getDate("datedebut"));
                o.setDatefin(rs.getDate("datefin"));
                o.setPrix(rs.getFloat("prix"));
                o.setEtat(rs.getBoolean("etat"));
                o.setVille(rs.getString("ville"));
                o.setType(rs.getString("type"));
                o.setId(rs.getInt("id"));
      
                log.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return log;
    }
        
        
        public List<Logements> getAllChambres(){
     List<Logements> log = new ArrayList<>();
        String req="select * from logement where type='Chambre'";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Logements o = new Logements();
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDatedebut(rs.getDate("datedebut"));
                o.setDatefin(rs.getDate("datefin"));
                o.setPrix(rs.getFloat("prix"));
                o.setEtat(rs.getBoolean("etat"));
                o.setVille(rs.getString("ville"));
                o.setType(rs.getString("type"));
                o.setId(rs.getInt("id"));
      
                log.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return log;
    }
        
        public Logements getLogementById(int i){
            String req="SELECT * FROM `logement` WHERE id=?";
             Logements logement = new Logements();
             
            try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,i);
                ResultSet rs=ste.executeQuery();
                while(rs.next()){                
                logement.setNom(rs.getString("nom"));
                logement.setDescription(rs.getString("description"));
                logement.setDatedebut(rs.getDate("datedebut"));
                logement.setDatefin(rs.getDate("datefin"));
                logement.setPrix(rs.getFloat("prix"));
                logement.setEtat(rs.getBoolean("etat"));
                logement.setVille(rs.getString("ville"));
                logement.setType(rs.getString("type"));
                logement.setId(rs.getInt("id"));
                
               
            }  
            } catch (Exception e) {
                System.out.println(ste);
                System.out.println(e.getMessage());
            }
            return logement;
          
        }
        
        
         
       public void suuprimerLogement(int o){
        
        String req="DELETE FROM `logement` WHERE id=?";
        
        try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,o);
                ste.executeUpdate();
                System.out.println("Logement supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
       
       
           public void modifierLogementById(int i,Logements o) {
        
        String req="UPDATE `logement`  SET `nom`=?,`description`=?,"
                + "`datedebut`=DATE_ADD(?, INTERVAL -22801 MONTH),`datefin`=DATE_ADD(?, INTERVAL -22801 MONTH),`prix`=?,`etat`=?,`ville`=?   WHERE id=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setString(1,o.getNom());
               ste.setString(2,o.getDescription());
               ste.setDate(3, o.getDatedebut());
               ste.setDate(4, o.getDatefin());
               ste.setFloat(5,o.getPrix());
               ste.setBoolean(6,o.isEtat());
               ste.setString(7,o.getVille());
               ste.setInt(8,i);
               ste.executeUpdate();
               System.out.println("logement modifier");
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
       
         public List<Logements> getAllLogements(){
     List<Logements> logements = new ArrayList<>();
        String req="SELECT * FROM `logement`";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Logements loge = new Logements();
                loge.setNom(rs.getString("nom"));
                loge.setDescription(rs.getString("description"));
                loge.setDatedebut(rs.getDate("datedebut"));
                loge.setDatefin(rs.getDate("datefin"));
                loge.setPrix(rs.getFloat("prix"));
                loge.setEtat(rs.getBoolean("etat"));
                loge.setVille(rs.getString("ville"));
                loge.setType(rs.getString("type"));
                loge.setId(rs.getInt("id"));
                 
                
      
                logements.add(loge);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
        return logements;
    }   
       
         
       
        
        
        
       
}
