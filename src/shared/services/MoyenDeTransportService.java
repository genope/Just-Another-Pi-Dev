/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.connexion.MaConnexion;
import shared.entities.*;
import shared.entities.enums.TypeDeTransport;

/**
 *
 * @author l3ej
 */
public class MoyenDeTransportService {

    Connection mc;
    PreparedStatement ste;

    public MoyenDeTransportService() {
        mc = MaConnexion.getInstance().getCnx();
    }

    //DATE_ADD(?, INTERVAL -22801 MONTH)
    public void ajouterMoyenDeTransport(MoyenDeTransport mdt) {
        String sql = "insert into MoyenDeTransport (nom, description, datedebut, datefin, prix, etat, ville, TypeDeTransport, matricule) Values(?,?,DATE_ADD(?, INTERVAL -22801 MONTH),DATE_ADD(?, INTERVAL -1901 YEAR),?,?,?,?,?)";
        try {
            System.out.println(mc);
            ste = mc.prepareStatement(sql);
            ste.setString(1, mdt.getNom());
            ste.setString(2, mdt.getDescription());
            ste.setDate(3, mdt.getDatedebut());
            ste.setDate(4, mdt.getDatefin());
            ste.setFloat(5, mdt.getPrix());
            ste.setBoolean(6, mdt.isEtat());
            ste.setString(7, mdt.getVille());
            ste.setString(8, mdt.getTypeDeTransport().toString());
            ste.setInt(9, mdt.getMatricule());
            ste.executeUpdate();
            System.out.println("Moyen De transport ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerMoyenDeTransport(int i) {
        String sql = "Delete from MoyenDeTransport where matricule = ?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, i);

            int mdtDete = ste.executeUpdate();
            if (mdtDete > 0) {
                System.out.println("Moyen de Transport supprimé");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<MoyenDeTransport> afficherMoyenDeTransport() {
        List<MoyenDeTransport> mdt = new ArrayList<>();
        String sql = "select * from MoyenDeTransport";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                MoyenDeTransport mdtAff = new MoyenDeTransport(
                        rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
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
    public void modifierMoyenDeTransport(MoyenDeTransport mdt, int id){
        String sql = "Update MoyenDeTransport SET nom=?, description=?, datedebut=DATE_ADD(?, INTERVAL -22801 MONTH), datefin=DATE_ADD(?, INTERVAL -22801 MONTH), prix=?, etat=?, ville=?, TypeDeTransport=?, matricule=? where id= ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, mdt.getNom());
            ste.setString(2, mdt.getDescription());
            ste.setDate(3, mdt.getDatedebut());
            ste.setDate(4, mdt.getDatefin());
            ste.setFloat(5, mdt.getPrix());
            ste.setBoolean(6, mdt.isEtat());
            ste.setString(7, mdt.getVille());
            ste.setString(8, mdt.getTypeDeTransport().toString());
            ste.setInt(9, mdt.getMatricule());
            ste.setInt(10, id);
            int pubUpdated = ste.executeUpdate();
            if (pubUpdated > 0) {
                System.out.println("La publication existante a ete modifié avec succes!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<MoyenDeTransport> getAllVoiture(){
        List<MoyenDeTransport> Allvoit = new ArrayList();
        String sql = "select * from MoyenDeTransport where type=Voiture";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                MoyenDeTransport mdtAff = new MoyenDeTransport(rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                Allvoit.add(mdtAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Allvoit;
    }
    public List<MoyenDeTransport> getAllMoto(){
        List<MoyenDeTransport> AllMoto = new ArrayList();
        String sql = "select * from MoyenDeTransport where type=Moto";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                MoyenDeTransport mdtAff = new MoyenDeTransport(rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                AllMoto.add(mdtAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return AllMoto;
    }
    public List<MoyenDeTransport> getAllVelo(){
        List<MoyenDeTransport> AllVelo = new ArrayList();
        String sql = "select * from MoyenDeTransport where type=Velo";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                MoyenDeTransport mdtAff = new MoyenDeTransport(rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                AllVelo.add(mdtAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return AllVelo;
    }
    public List<MoyenDeTransport> getAllMoyenDeTransport(){
        List<MoyenDeTransport> AllMoyenDeTransports = new ArrayList();
        String sql = "select * from MoyenDeTransport";

        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                MoyenDeTransport mdtAff = new MoyenDeTransport(rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                AllMoyenDeTransports.add(mdtAff);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return AllMoyenDeTransports;
    }
    
    public MoyenDeTransport getMoyenDeTransportByID(int id){
        MoyenDeTransport mdtAff = new MoyenDeTransport();
        String sql = "select * from MoyenDeTransport where id = ?";

        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            
            rs.next();
                MoyenDeTransport mdt = new MoyenDeTransport(rs.getInt("matricule"),
                        TypeDeTransport.valueOf(rs.getString("TypeDeTransport")),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        rs.getInt("prix"),
                        rs.getBoolean("etat"),
                        rs.getString("ville"));
                mdt.setId(rs.getInt("id"));
                        
                 mdtAff = mdt;      
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return mdtAff;
    }
}
