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
        String sql = "insert into produit (ref_prod, designation, description, prix,image,qte_stock,nomCategorie, region1) Values(?,?,?,?,?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, prod.getRef_prod());
            ste.setString(2, prod.getDesignation());
            ste.setString(3, prod.getDescription());
            ste.setDouble(4, prod.getPrix());
            ste.setString(5, prod.getImage());
            ste.setInt(6, prod.getQte_stock());
            ste.setString(7, prod.getNomCategorie());
            ste.setString(8, prod.getregion1());
            ste.executeUpdate();
            System.out.println("produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void supprimerProduit(String ref) {
        String sql = "delete from `produit` where ref_prod = ?";
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
        String sql = "select * from produit";

        try {
            
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1"));
                        
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
        String sql = "Update produit set ref_prod=?, designation =?, description=?, prix=?, image=?, qte_stock=?, nomCategorie=?, region1=?  where ref_prod= ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, prod.getRef_prod());
            ste.setString(2, prod.getDesignation());
            ste.setString(3, prod.getDescription());
            ste.setDouble(4, prod.getPrix());
            ste.setString(5, prod.getImage());
            ste.setInt(6, prod.getQte_stock());
            ste.setString(7, prod.getNomCategorie());
            ste.setString(8, prod.getregion1());
            
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
        String sql = "select * from produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
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
        String sql = "select * from produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
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
        String sql = "select * from produit";
        
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
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
        String sql = "select * from produit where ref_prod = ?";
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
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
                        );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
            }
        return even;
    }
    
    public List<Produit> getProdJoin(int i){
        List<Produit> Allprod = new ArrayList();
        try {
            String sql = "select * from produit right join panierdetails on produit.ref_prod=panierdetails.idproduit where panierdetails.idCommande=?";
            ste= mc.prepareStatement(sql);
             ste.setInt(1, i);
            ResultSet rs = ste.executeQuery();
           
            
            while (rs.next()){
            Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
                        );
                System.out.println(prodAff);
            Allprod.add(prodAff);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Allprod;
    }
    
    public List<Produit> getProdJoinss(){
        List<Produit> Allprod = new ArrayList();
        PanierDetailsService pandet = new PanierDetailsService();
        try {
            String sql = "select * from produit  join panierdetails on produit.ref_prod=panierdetails.idproduit ";
            ste= mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
            Produit prodAff = new Produit(
                        rs.getString("ref_prod"),
                        rs.getString("designation"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
                        );
            Allprod.add(prodAff);
            PanierDetailsService panserv= new PanierDetailsService();
                    rs.getDouble("quantite");
                    pandet=panserv;
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Allprod;
    }
    ///Fonction zeyda lel test
    public Produit getPanierDetails(String ref){
        Produit even= new Produit();
        String sql = "select * from produit where ref_prod = ?";
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
                        rs.getString("image"),
                        rs.getInt("qte_stock"),
                        rs.getString("nomCategorie"),
                        rs.getString("region1")
                        );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
            }
        return even;
    }
    
}
