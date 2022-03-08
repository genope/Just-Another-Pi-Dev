/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.connexion.MaConnexion;
import shared.entities.*;
import shared.entities.enums.*;
import static shared.entities.enums.TypeOffres.Horeca;
/**
 *
 * @author user
 */
public class OffreServices {
          Connection mc;
      PreparedStatement ste;

    public OffreServices() {
        this.mc = MaConnexion.getInstance().getCnx();
    }
     

          public void ajoutOffre(Offres offre){
        
        String req="insert into offres(id_host,nom,description,datedebut,datefin,prix,etat,ville,type,categ,image) Values(?,?,?,?,?,?,?,?,?,?,?)";
        
         try {
            ste=mc.prepareStatement(req);
            ste.setInt(1, offre.getIdhost());
            ste.setString(2, offre.getNom());
            ste.setString(3, offre.getDescription());
            ste.setDate(4, offre.getDatedebut());
            ste.setDate(5, offre.getDatefin());
            ste.setFloat(6, offre.getPrix());
            ste.setBoolean(7, offre.isEtat());
            ste.setString(8, offre.getVille());
            ste.setString(11, offre.getFile());
           
         if(offre.getCateg()!=null){  
           switch(offre.getCateg().toString()){
               case "Maison" :
                    ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "Logement");
                            break;
               case "Appartement" :
                    ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "Logement");
                            break;            
               case "Chambre" : ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "Logement");
                            break;            
               case "Voiture" :
                    ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "MoyenDeTransport");
                            break;   
               case "Moto" :
                    ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "MoyenDeTransport");
                            break;   
               case "Velo" :
                    ste.setString(10, offre.getCateg().toString());
                            ste.setString(9, "MoyenDeTransport");
                            break;
                        }
         }
           if(offre.getCateg()==null){
               ste.setString(10, null);
           ste.setString(9, "Horeca");
           }            
            ste.executeUpdate();
            System.out.println("offres ajoutée");
        } catch (SQLException ex) {
             System.out.println(ste);
            System.out.println(ex.getMessage());
        }
    }  
    
          
// public List<Offres> getAllMaisons(){
//     List<Offres> Maisons = new ArrayList<>();
//        String req="SELECT * FROM `offres` where categ='Maison'";
//         try {
//            ste=mc.prepareStatement(req);
//            ResultSet rs=ste.executeQuery();
//            while(rs.next()){
//                Offres maison = new Offres();
//                maison.setNom(rs.getString("nom"));
//                maison.setDescription(rs.getString("description"));
//                maison.setDatedebut(rs.getDate("datedebut"));
//                maison.setDatefin(rs.getDate("datefin"));
//                maison.setPrix(rs.getFloat("prix"));
//                maison.setEtat(rs.getBoolean("etat"));
//                maison.setVille(rs.getString("ville"));
//                maison.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
//                maison.setIdhost(rs.getInt("id_host"));
//                maison.setId(rs.getInt("id_offre"));
//                maison.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
//                 
//                
//                
//                Maisons.add(maison);
//              
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//           
//       return Maisons;
//    }  
 
 
//  public List<Offres> getAllAppartements(){
//     List<Offres> Appartements = new ArrayList<>();
//        String req="SELECT * FROM `offres` where categ='Appartement'";
//         try {
//            ste=mc.prepareStatement(req);
//            ResultSet rs=ste.executeQuery();
//            while(rs.next()){
//                Offres appartement = new Offres();
//                appartement.setNom(rs.getString("nom"));
//                appartement.setDescription(rs.getString("description"));
//                appartement.setDatedebut(rs.getDate("datedebut"));
//                appartement.setDatefin(rs.getDate("datefin"));
//                appartement.setPrix(rs.getFloat("prix"));
//                appartement.setEtat(rs.getBoolean("etat"));
//                appartement.setVille(rs.getString("ville"));
//                appartement.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
//                appartement.setIdhost(rs.getInt("id_host"));
//                appartement.setId(rs.getInt("id_offre"));
//                appartement.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
//                 
//                
//                
//                Appartements.add(appartement);
//              
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//           
//       return Appartements;
//    }
  
  
  
    public List<Offres> getAllOffres(CategorieOffres categorie){
     List<Offres> Offres = new ArrayList<>();
        String req="SELECT * FROM `offres` where categ=?";
         try {
            ste=mc.prepareStatement(req);
            ste.setString(1,categorie.toString());
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres chambre = new Offres();
                chambre.setNom(rs.getString("nom"));
                chambre.setDescription(rs.getString("description"));
                chambre.setDatedebut(rs.getDate("datedebut"));
                chambre.setDatefin(rs.getDate("datefin"));
                chambre.setPrix(rs.getFloat("prix"));
                chambre.setEtat(rs.getBoolean("etat"));
                chambre.setVille(rs.getString("ville"));
                chambre.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
                chambre.setIdhost(rs.getInt("id_host"));
                chambre.setId(rs.getInt("id_offre"));
                chambre.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
                chambre.setFile(rs.getString("image"));
                 
                
                
                Offres.add(chambre);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
       return Offres;
    }
    
    
     public ObservableList<Offres> getAllOffresById2(int i){
     ObservableList<Offres> Offres = FXCollections.observableArrayList();
        String req="SELECT * FROM `offres` where id_Host=?";
         try {
            ste=mc.prepareStatement(req);
            ste.setInt(1,i);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres chambre = new Offres();
                chambre.setNom(rs.getString("nom"));
                chambre.setDescription(rs.getString("description"));
                chambre.setDatedebut(rs.getDate("datedebut"));
                chambre.setDatefin(rs.getDate("datefin"));
                chambre.setPrix(rs.getFloat("prix"));
                chambre.setEtat(rs.getBoolean("etat"));
                chambre.setVille(rs.getString("ville"));
                chambre.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
                chambre.setIdhost(rs.getInt("id_host"));
                chambre.setId(rs.getInt("id_offre"));
                chambre.setFile(rs.getString("image"));
                
                if(!chambre.getTypeOff().equals(Horeca))
                chambre.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
                 
                
                
                Offres.add(chambre);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
       return Offres;
    }
     
          public ObservableList<Offres> getAllFOffres(){
     ObservableList<Offres> Offres = FXCollections.observableArrayList();
        String req="SELECT * FROM `offres` where etat='0'";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres chambre = new Offres();
                chambre.setNom(rs.getString("nom"));
                chambre.setDescription(rs.getString("description"));
                chambre.setDatedebut(rs.getDate("datedebut"));
                chambre.setDatefin(rs.getDate("datefin"));
                chambre.setPrix(rs.getFloat("prix"));
                chambre.setEtat(rs.getBoolean("etat"));
                chambre.setVille(rs.getString("ville"));
                chambre.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
                chambre.setIdhost(rs.getInt("id_host"));
                chambre.setId(rs.getInt("id_offre"));
                chambre.setFile(rs.getString("image"));
                
                if(!chambre.getTypeOff().equals(Horeca))
                chambre.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
                 
                
                
                Offres.add(chambre);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
       return Offres;
    }
       public List<Offres> getAllMyOffres(int i){
     List<Offres> Offres = new ArrayList<>();
        String req="SELECT * FROM `offres` where id_Host=?";
         try {
            ste=mc.prepareStatement(req);
            ste.setInt(1,i);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres offre = new Offres();
                offre.setNom(rs.getString("nom"));
                offre.setDescription(rs.getString("description"));
                offre.setDatedebut(rs.getDate("datedebut"));
                offre.setDatefin(rs.getDate("datefin"));
                offre.setPrix(rs.getFloat("prix"));
                offre.setEtat(rs.getBoolean("etat"));
                offre.setVille(rs.getString("ville"));
                offre.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
                offre.setIdhost(rs.getInt("id_host"));
                offre.setId(rs.getInt("id_offre"));
                offre.setFile(rs.getString("image"));
                
                if(!offre.getTypeOff().equals(Horeca))
                offre.setCateg(CategorieOffres.valueOf(rs.getString("categ")));
                 
               
                
                Offres.add(offre);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
       return Offres;
    }    
            public List<Offres> getAllHorecaById(){
     List<Offres> Horecas = new ArrayList<>();
        String req="SELECT * FROM `offres` where type='Horeca'";
         try {
            ste=mc.prepareStatement(req);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offres Horeca = new Offres();
                Horeca.setNom(rs.getString("nom"));
                Horeca.setDescription(rs.getString("description"));
                Horeca.setDatedebut(rs.getDate("datedebut"));
                Horeca.setDatefin(rs.getDate("datefin"));
                Horeca.setPrix(rs.getFloat("prix"));
                Horeca.setEtat(rs.getBoolean("etat"));
                Horeca.setVille(rs.getString("ville"));
                Horeca.setTypeOff(TypeOffres.valueOf(rs.getString("type")));
                Horeca.setIdhost(rs.getInt("id_host"));
                Horeca.setId(rs.getInt("id_offre"));
                
                
                Horecas.add(Horeca);
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
           
       return Horecas;
    }
            
            
   public void suuprimerLogement(int o){
        
        String req="DELETE FROM `offres` WHERE id_offre=?";
        
        try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,o);
                ste.executeUpdate();
                System.out.println("Offres supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
   
   public void modifierOffreById(int i,Offres o) {
        
        String req="UPDATE `offres`  SET `description`=?,"
                + "`datedebut`=?,`datefin`=?,`prix`=?   WHERE id_offre=?";
        try {
               ste=mc.prepareStatement(req);
               ste.setString(1,o.getDescription());
               ste.setDate(2, o.getDatedebut());
               ste.setDate(3,  o.getDatefin());
               ste.setFloat(4,o.getPrix());
               ste.setInt(5,i);
               ste.executeUpdate();
               System.out.println("Offres modifier");
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
   
public int nombreOffres(){
       String req="select * from `offres` ";
  
       int count=0;
       try {
           ste=mc.prepareStatement(req);
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
         
           System.out.println(e.getMessage());
                   
       }
       return count;
   }


public int getNbrMyOffres(int cin){
       String req="select * FROM `offres` WHERE id_Host=?";
  
       int count=0;
       try {
           
           ste=mc.prepareStatement(req);
           ste.setInt(1, cin);
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
           System.out.println(e.getMessage());
                   
       }
       return count;
   }
public int getNbrLogemts(int cin){
       String req="select * FROM `offres` WHERE id_Host=? and type='Logement' ";
  
       int count=0;
       try {
           
           ste=mc.prepareStatement(req);
           ste.setInt(1, cin);
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
           System.out.println(e.getMessage());
                   
       }
       return count;
   }

public int getNbrMoyTransports(int cin){
       String req="select * FROM `offres` WHERE id_Host=? and type='MoyenDeTransport' ";
  
       int count=0;
       try {
           
           ste=mc.prepareStatement(req);
           ste.setInt(1, cin);
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
           System.out.println(e.getMessage());
                   
       }
       return count;
   }

public int getNbrMyHoreca(int cin){
       String req="select * FROM `offres` WHERE id_Host=? and type='Horeca'";
  
       int count=0;
       try {
           
           ste=mc.prepareStatement(req);
           ste.setInt(1, cin);
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
           System.out.println(e.getMessage());
                   
       }
       return count;
   }


public int getNbrMyLogements(int cin,TypeOffres type){
       String req="select * FROM `offres` WHERE id_Host=? and type=?";
  
       int count=0;
       try {
           
           ste=mc.prepareStatement(req);
           ste.setInt(1, cin);
           ste.setString(2, type.toString());
           ResultSet rs=ste.executeQuery();
            
                 while (rs.next()) {
               count++;
            }         
       } catch (Exception e) {
           System.out.println(e.getMessage());
                   
       }
       return count;
   }
//getFalseOffres
public void ApprouverOf(int i){

    
      String req="UPDATE `offres` SET etat='1' where id_offre=?"; 
    try {
                ste=mc.prepareStatement(req);
                 ste.setInt(1, i);
                ste.executeUpdate();
                System.out.println("Offres Approuved");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}

}



