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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setAddrese(rs.getString("adresse"));
                p.setRegion_id(rs.getInt("region_id"));
                p.setDatecreation(rs.getDate("datecreation"));
                
                publications.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return publications;
    }
    
    public void modifierPersonne( String nom, String description,String image,String adresse, int id){
        try {
            String sql = "UPDATE publication SET nom=?,description=?,image=?,adresse=? WHERE id=?";
            
            ste=mc.prepareStatement(sql);
           ste.setString(1,nom);
            ste.setString(2, description);
            ste.setString(3, image);
            ste.setString(4, adresse);
            ste.setInt(5, id);
           
           
            
            
            ste.executeUpdate();
            System.out.println("Personne modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerPersonne(int id) {
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
    
}
