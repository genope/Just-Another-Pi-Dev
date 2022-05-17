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

/**
 *
 * @author l3ej
 */
public class PanierService {
    Connection mc;
    PreparedStatement ste;

    public PanierService() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterPanier(Panier p1) {
        String req = "insert into Panier (id_panier,CIN,total) values (?,?,?)";
        
        try {
            PreparedStatement ps = mc.prepareStatement(req);
//            ps.setInt(1, p1.getId_panier());
            ps.setInt(1, p1.getCIN());
            ps.setInt(2, p1.getId_Panier());
            ps.setDouble(3, p1.getTotal());

            ps.executeUpdate();
            System.out.println("Panier Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierpanier(Panier p1) {
        try {
            //requete pre compiler
            PreparedStatement pt = mc.prepareStatement("update Panier set total=?, where CIN= ? and id_panier = ?");
            pt.setDouble(1, p1.getTotal());
            pt.setInt(3, p1.getCIN());
            pt.setInt(4, p1.getId_Panier());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void supprimerPanier(int id) {

        try {
            PreparedStatement pt = mc.prepareStatement("delete from Panier where id_panier=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Panier> ListPanier() {
        List ALLproducts = new ArrayList();
        try {
            String sql = "select * from Panier";
            ste=mc.prepareStatement(sql);
            ResultSet rest = ste.executeQuery(sql);
            while (rest.next()) {
                Panier pr = new Panier(

                rest.getInt("id_panier"),
                rest.getInt("CIN"),
                rest.getDouble("total"));
                
                //pr.setId_panier(rest.getInt("id_panier"));

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ALLproducts;

    }
    
    public List<Panier> ListPanierUser(int CIN) {
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
                
                //pr.setId_panier(rest.getInt("id_panier"));

                ALLproducts.add(pr);

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ALLproducts;

    }
    
    
    
}
