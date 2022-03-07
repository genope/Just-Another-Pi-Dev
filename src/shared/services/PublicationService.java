/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.services;

/**
 *
 * @author Fatma
 */
import java.sql.*;
import java.util.*;

import shared.connexion.MaConnexion;
import shared.entities.Publication;



public class PublicationService {
    
    Connection mc;
    PreparedStatement ste;

    public PublicationService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterPublication(Publication p){
        String sql ="insert into publication(id_guest,nom,description,image,adresse,datecreation,region_id) VALUES(?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, p.getId_guest());
            ste.setString(2, p.getNom());
            ste.setString(3, p.getDescription());
            ste.setString(4, p.getImage());
            ste.setString(5, p.getAdresse());
            ste.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ste.setInt(7, p.getRegion_id());
            ste.executeUpdate();
            System.out.println("Publication Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Publication> afficherPublication(){
        List<Publication> publications = new ArrayList<>();
        String sql="select * from publication";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                
                publications.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return publications;
    }
    
    public void modifierPublication( Publication p, int id){
        try {
            String sql = "UPDATE publication SET nom=?,description=?,image=?,adresse=? WHERE id=?";
            
            ste=mc.prepareStatement(sql);
           ste.setString(1,p.getNom());
            ste.setString(2, p.getDescription());
            ste.setString(3, p.getImage());
            ste.setString(4, p.getAdresse());
            ste.setInt(5, id);
           
           
            
            
            ste.executeUpdate();
            System.out.println("Publication modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    public void supprimerPublication(int id) {
         try {
            String sql = "DELETE FROM publication WHERE id=?;";
            
            ste=mc.prepareStatement(sql);
			
			ste.setInt(1,id);
			
			int executeUpdate = ste.executeUpdate();
			
			if(executeUpdate ==1){
				System.out.println("Publication supprimée avec ID::"+id);
			}
		} catch (SQLException e) {
		}
	
	}
   public List<String> ListRegion() {

            List<String> list = new ArrayList<String>();
            try {
                String sqlStationName = " select nom from region ";
                Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
                ResultSet rs = st3.executeQuery(sqlStationName);

                while (rs.next()) {

                    list.add(rs.getString("nom"));

                }

                rs.close();
                st3.close();

            } catch (SQLException ex) {
                System.err.println("ERR" + ex);
            }
            return list;
        }
   public String GetRegion(int id) {
        String reg = null;
        try {
            String sqlStationName = " select nom from region where id=" + id;
            Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                reg = rs.getString("nom");

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return reg;
    }
   
   
   public List<Publication> filtrerRegion(String options) {
        List<Publication> myList = new ArrayList<Publication>();
        if (options.contains("ARIANA")) {
            options = options.replace("ARIANA", "1");
        }
        if (options.contains("SOUSSE")) {
            options = options.replace("SOUSSE", "2");
        }
        if (options.contains("TUNIS")) {
            options = options.replace("TUNIS", "3");
        }
        if (options.contains("NABEUL")) {
            options = options.replace("NABEUL", "4");
        }
        if (options.contains("MONASTIR")) {
            options = options.replace("MONASTIR", "5");
        }
        if (options.contains("BIZERTE")) {
            options = options.replace("BIZERTE", "6");
        }
        if (options.contains("BEN AROUS")) {
            options = options.replace("BEN AROUS", "7");
        }

        if (options.contains("BEJA")) {
            options = options.replace("BEJA", "8");
        }
        if (options.contains("GABES")) {
            options = options.replace("GABES", "9");
        }
        if (options.contains("GAFSA")) {
            options = options.replace("GAFSA", "10");
        }
        if (options.contains("JENDOUBA")) {
            options = options.replace("JENDOUBA", "11");
        }
        if (options.contains("KAIROUAN")) {
            options = options.replace("KAIROUAN", "12");
        }
        if (options.contains("KASSERINE")) {
            options = options.replace("KASSERINE", "13");
        }
        if (options.contains("KEBILI")) {
            options = options.replace("KEBILI", "14");
        }
        if (options.contains("KEF")) {
            options = options.replace("KEF", "15");
        }
        if (options.contains("MAHDIA")) {
            options = options.replace("MAHDIA", "16");
        }
        if (options.contains("MANOUBA")) {
            options = options.replace("MANOUBA", "17");
        }
        if (options.contains("MEDNINE")) {
            options = options.replace("MEDNINE", "18");
        }
        if (options.contains("SFAX")) {
            options = options.replace("SFAX", "19");
        }
        if (options.contains("SIDI BOUZID")) {
            options = options.replace("SIDI BOUZID", "20");
        }
        if (options.contains("SILIANA")) {
            options = options.replace("SILIANA", "21");
        }
        if (options.contains("TATAOUINE")) {
            options = options.replace("TATAOUINE", "22");
        }
        if (options.contains("TOZEUR")) {
            options = options.replace("TOZEUR", "23");
        }
        if (options.contains("ZAGHOUAN")) {
            options = options.replace("ZAGHOUAN", "24");
        }
        try {

            if (options.length() > 0) {
                //  options = options.replaceAll(",", " AND ");
                String requete3 = "SELECT * From publication  WHERE region_id in (" + options + ")";

                Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
                ResultSet rs = st3.executeQuery(requete3);
                while (rs.next()) {
                    Publication p = new Publication();
                     p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                   
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
   
    
    
    public List<Publication> trierpublication(){
        List<Publication> publications = new ArrayList<>();
        String sql="select * from publication ORDER BY datecreation DESC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                
                publications.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return publications;
    }
    
    public List<Publication> Recherchepublication(String nom) {
        List<Publication> myList = new ArrayList<Publication>();

        try {
            String requete3 = "SELECT * From publication where nom like '%" + nom + "%'";
            Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
    
    public int GetItemId(String nomtable, String comparethis, String comparable) {

        int i = 0;
        try {
            String sqlStationName = " select id from " + nomtable + " where " + comparethis + "='" + comparable + "'";
            Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                i = rs.getInt("id");

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return i;
    }
    public List<Publication> recherchePublication(String type, String valeur) {
        List<Publication> myList = new ArrayList();
        String requete = null;

        try {
            if (type.equals("Nom")) {
                requete = "SELECT * from Publication where nom like '%" + valeur + "%'";
            } else if (type.equals("datecreation")) {
                requete = "SELECT * from Publication where datecreation like '%" + valeur + "%'";
            } else if (type.equals("id")) {
                requete = "SELECT * from Publication where id like '%" + valeur + "%'";
            } else if (type.equals("description")) {
                requete = "SELECT * from Publication where description like '%" + valeur + "%'";
            } 
             else if (type.equals("Tout")) {
                requete = "SELECT * from Publication where  nom like '%" + valeur + "%' or datecreation like '%" + valeur + "%' or id like '%" + valeur + "%' or description like '%" + valeur + "%' ";
            }

            ste = mc.prepareStatement(requete);

            Publication p;
            for(ResultSet rs = ste.executeQuery(requete); rs.next(); myList.add(p)) {
                p = new Publication();
                 p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }
    public List<Publication> afficherPublicationparuser(int id){
        List<Publication> publications = new ArrayList<>();
        String sql=" select* from publication where id_guest=" + id;
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAdresse(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                
                publications.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return publications;
    }
   
}
