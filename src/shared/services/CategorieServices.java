/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.connexion.MaConnexion;
import shared.entities.Evenement;
import shared.entities.MoyenDeTransport;
import shared.entities.enums.TypeDeTransport;
import shared.entities.CategorieProduit;
/**
 *
 * @author l3ej
 */
public class CategorieServices {
    Connection mc;
    PreparedStatement ste;
    
    public CategorieServices(){
        mc = MaConnexion.getInstance().getCnx();

    }
    
    public void AjouterCategorieProd(CategorieProduit cat){
    String sql = "insert into CategorieProduit (nomCategorie) Values (?)";
        try {
            ste=mc.prepareStatement(sql);
            //ste.setInt(1, cat.getIdCategorie());
            ste.setString(1, cat.getNomCategorie());
            ste.executeUpdate();
            System.out.println("Categorie de produit ajoutée.");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    public void supprimerCategorieProduit(int i){
        String sql = "delete from CategorieProduit where idCategorie = ?";
        
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, i);
            
            int catdel = ste.executeUpdate();
            if (catdel > 0)
                System.out.println("Categorie Produit supprimée");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());        
        }
    }
    public void supprimerCategorieProduitbyname(String nom){
        String sql = "delete from CategorieProduit where nomCategorie = ?";
        
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, nom);
            
            int catdel = ste.executeUpdate();
            if (catdel > 0)
                System.out.println("Categorie Produit supprimée");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());        
        }
    }
    
    public List<CategorieProduit> afficherCategorieProduit(){
        List<CategorieProduit> catprod = new ArrayList<>();
        String sql = "select * from CategorieProduit";
        
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                CategorieProduit Affcatprod = new CategorieProduit(
                        rs.getInt("idCategorie"),
                        rs.getString("nomCategorie")
                );
                catprod.add(Affcatprod);
                System.out.println(Affcatprod);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
            }   
        
    return catprod;
    }
    
    public void modifierCategorieProduit(CategorieProduit catprod, int id){
        String sql = "update CategorieProduit set  nomCategorie=?";
        
        try {
            ste=mc.prepareStatement(sql);
            //ste.setInt(1, catprod.getIdCategorie());
            ste.setString(1, catprod.getNomCategorie());
            
            int catupd = ste.executeUpdate();
            if (catupd>0)
                System.out.println("La categorie de produit a ete modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    public ObservableList<CategorieProduit> getAllCategorieProduit(){
        ObservableList<CategorieProduit> Allcatprod =  FXCollections.observableArrayList();
        String sql = "select * from CategorieProduit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                CategorieProduit CatAff = new CategorieProduit(
                        rs.getInt("idCategorie"),
                        rs.getString("nomCategorie"));   
                Allcatprod.add(CatAff);
            } 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return Allcatprod;
    }
    public ObservableList<String> getAllCategorieProduitName(){
        ObservableList<String> Allcatprod =  FXCollections.observableArrayList();
        String sql = "select * from CategorieProduit";
        String Catname ="";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Allcatprod.add(rs.getString("nomCategorie") );
            } 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return Allcatprod;
    }
    
   public String getNomByid(int id){
       String sql = "select nomcategorie from Categorie where id = ?";
       String nom=""; 
       try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            rs.next();
                nom = rs.getString("nomCategorie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
       return nom;
   }


}

