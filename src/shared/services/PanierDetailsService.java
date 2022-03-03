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
import shared.connexion.MaConnexion;
import shared.entities.Panier;
import shared.entities.PanierDetails;

/**
 *
 * @author l3ej
 */
public class PanierDetailsService {
Connection mc;
    PreparedStatement ste;

        
    public PanierDetailsService() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterPanierDetails(PanierDetails p1) {
        String req = "insert into PanierDetails (idProduit,idCommande,quantite,prix) values (?,?,?,?)";
//        private int idCommande;
//    private int id_prod;
//    private int quantite;
//    private int prix;
        try {
            PreparedStatement ps = mc.prepareStatement(req);
//            ps.setInt(1, p1.getId_panier());
            ps.setString(1, p1.getId_prod());
            ps.setInt(2, p1.getIdCommande());
            ps.setInt(3, p1.getQuantite());
            ps.setDouble(4, p1.getPrix());

            ps.executeUpdate();
            System.out.println("Panier detail Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierpanierDetails(PanierDetails p1) {
        try {
            //requete pre compiler
            PreparedStatement pt = mc.prepareStatement("update PanierDetails set quantite=?, where idCommande= ? and idProduit = ?");
            pt.setInt(1, p1.getQuantite());
            pt.setInt(2, p1.getIdCommande());
            pt.setString(3, p1.getId_prod());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void supprimerPanierDetails(PanierDetails p) {

        try {
            PreparedStatement pt = mc.prepareStatement("delete from PanierDetails where idProduit= ? and idCommande= ?");
            pt.setInt(1, p.getIdCommande());
            pt.setString(2, p.getId_prod());
            pt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Panier> ListPanierDetails() {
        List ALLproducts = new ArrayList();
        try {
            String sql = "select * from PanierDetails";
            ste=mc.prepareStatement(sql);
            ResultSet rest = ste.executeQuery(sql);
            while (rest.next()) {
                PanierDetails pr = new PanierDetails(

                
                rest.getString("idProduit"),
                rest.getInt("idCommande"),
                rest.getInt("quantite"),
                rest.getDouble("prix"));
                
                //pr.setId_panier(rest.getInt("id_panier"));

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ALLproducts;

    }
    
    public List<Panier> ListPanierDetailsUser(int CIN) {
        List<Panier> ALLproducts = new ArrayList();
        try {
            PreparedStatement st = mc.prepareStatement("select * from Panier where CIN = ?");
            st.setInt(1, CIN);
            ResultSet rest = st.executeQuery();
            while (rest.next()) {
//                Panier pr = new Panier();
//
//                pr.setId_Panier(rest.getInt("id"));
//                pr.setCIN(rest.getInt("CIN"));
//                pr.setTotal(rest.getDouble("total"));
                Panier pr = new Panier(
                rest.getInt("id_panier"),
                rest.getInt("id_panier"),
                rest.getDouble("total"));
                
                ALLproducts.add(pr);

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ALLproducts;

    }
    
    
    
}
