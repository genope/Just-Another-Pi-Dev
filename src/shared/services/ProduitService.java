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
import shared.entities.Produit;
/**
 *
 * @author l3ej
 */
public class ProduitService {
    Connection mc;
    PreparedStatement ste;

    public ProduitService() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterProduit(Produit prod) {
        String sql = "insert into Produit (ref_prod, designation, description, prix,image,qte_stock,nomCategorie, region) Values(?,?,?,?,?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, prod.getRef_prod());
            ste.setString(2, prod.getDesignation());
            ste.setString(3, prod.getDescription());
            ste.setDouble(4, prod.getPrix());
            ste.setBlob(5, prod.getImage());
            ste.setInt(6, prod.getQte_stock());
            ste.setString(7, prod.getNomCategorie());
            ste.setString(8, prod.getRegion());
            ste.executeUpdate();
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void supprimerProduit(String ref) {
        String sql = "delete from `Produit` where ref_prod = ?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, ref);

            ste.executeUpdate();
            int prodDel = ste.executeUpdate();
            if (prodDel > 0) {
                System.out.println("Evenement supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Produit> afficherProduit() {
        List<Produit> prod = new ArrayList<>();
        String sql = "select * from Produit";

        try {
            
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region"));
                        
                        prodAff.setId(rs.getInt("id_prod"));
                        System.out.println(prodAff);
                prod.add(prodAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }
    
    public void modifierProduit(Produit prod, String oldref){
        String sql = "Update Produit set ref_prod=?, designation =?, description=?, prix=?, image=?, qte_stock=?, nomCategorie=?, region=?  where ref_prod= ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, prod.getRef_prod());
            ste.setString(2, prod.getDesignation());
            ste.setString(3, prod.getDescription());
            ste.setDouble(4, prod.getPrix());
            ste.setBlob(5, prod.getImage());
            ste.setInt(6, prod.getQte_stock());
            ste.setString(7, prod.getNomCategorie());
            ste.setString(8, prod.getRegion());
            
            ste.setString(9, oldref);
            int prodUpdated = ste.executeUpdate();
            if (prodUpdated > 0) {
                System.out.println("Le prod existant a ete modifé avec succes!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public ObservableList<Produit> getAllProd(){
        ObservableList<Produit> Allprod = FXCollections.observableArrayList();
        String sql = "select * from Produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region")
                        );
                Allprod.add(prodAff);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return Allprod;
    }
    
    public List<Produit> getAllProdL(){
        List<Produit> Allprod = new ArrayList();
        String sql = "select * from Produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region")
                        );
                Allprod.add(prodAff);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return Allprod;
    }
    
    public List<Produit> getAllProdByPrix(Double Lprix, Double Hprix){
        List<Produit> Allprod = new ArrayList();
        String sql = "select * from Produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region")
                        );
                Allprod.add(prodAff);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return Allprod;
    }
    public Produit getProdByName(String ref){
        Produit even= new Produit();
        String sql = "select * from Produit where ref_prod = ?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, ref);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
               Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region")
                        );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
            }
        
        
        return even;
    }
    
    public List<Produit> getProdJoin(){
        List<Produit> Allprod = new ArrayList();
        try {
            String sql = "select * from Produit right join PanierDetails on Produit.ref_prod=PanierDetails.idProduit ";
            ste= mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
            Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getBlob("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region")
                        );
            Allprod.add(prodAff);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Allprod);
        return Allprod;
    }
}
