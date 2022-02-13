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
import shared.entities.Evenement;
import shared.entities.MoyenDeTransport;
import shared.entities.enums.TypeDeTransport;

/**
 *
 * @author l3ej
 */
public class EvenementService {
    Connection mc;
    PreparedStatement ste;

    public EvenementService() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
     public void ajouterEvenement(Evenement even) {
        String sql = "insert into Evenement (nom, description, datedebut, datefin, prix, etat, ville, lieu) Values(?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -1901 YEAR),?,?,?,?)";
        try {
            System.out.println(mc);
            ste = mc.prepareStatement(sql);
            ste.setString(1, even.getNom());
            ste.setString(2, even.getDescription());
            ste.setDate(3, even.getDatedebut());
            ste.setDate(4, even.getDatefin());
            ste.setFloat(5, even.getPrix());
            ste.setBoolean(6, even.isEtat());
            ste.setString(7, even.getVille());
            ste.setString(8, even.getLieu());
            ste.executeUpdate();
            System.out.println("Evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerEvenement(int i) {
        String sql = "Delete from Evenement where matricule = ?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, i);

            int mdtDete = ste.executeUpdate();
            if (mdtDete > 0) {
                System.out.println("Evenement supprimé");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Evenement> afficherEvenement() {
        List<Evenement> mdt = new ArrayList<>();
        String sql = "select * from Evenement";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Evenement mdtAff = new Evenement(
                        rs.getString("lieu"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                        
                        mdtAff.setId(rs.getInt("id"));
                mdt.add(mdtAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return mdt;
    }
    public void modifierEvenement(Evenement even, int id){
        String sql = "Update Evenement SET nom=?, description=?, datedebut=DATE_ADD(?, INTERVAL -22801 MONTH), datefin=DATE_ADD(?, INTERVAL -22801 MONTH), prix=?, etat=?, ville=?, lieu=? where id= ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, even.getNom());
            ste.setString(2, even.getDescription());
            ste.setDate(3, even.getDatedebut());
            ste.setDate(4, even.getDatefin());
            ste.setFloat(5, even.getPrix());
            ste.setBoolean(6, even.isEtat());
            ste.setString(7, even.getVille());
            ste.setString(8, even.getLieu());
            ste.setInt(10, id);
            int pubUpdated = ste.executeUpdate();
            if (pubUpdated > 0) {
                System.out.println("L'evenement existant a ete modifé avec succes!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
