/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;
import java.sql.*;
import java.util.*;
import shared.connexion.MaConnexion;
import shared.entities.Commentaire;
/**
 *
 * @author Fatma
 */
public class CommentaireService {
    
    Connection mc;
    PreparedStatement ste;
    
     public CommentaireService() {
        mc=MaConnexion.getInstance().getCnx();
    }
     public void ajouterCommentaire(Commentaire p){
        String sql ="insert into commentaire(id,id_guest,id_publication,nom,comment,date_com) VALUES(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.setInt(2, p.getId_guest());
            ste.setInt(3, p.getId_publication());
            ste.setString(4, p.getNom());
            ste.setString(5, p.getComment());
            ste.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ste.executeUpdate();
            System.out.println("Commentaire Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public List<Commentaire> afficherCommentaire(){
        List<Commentaire> commentaires = new ArrayList<>();
        String sql="select * from commentaire";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Commentaire p = new Commentaire();
                p.setId(rs.getInt("id"));
                p.setId_guest(rs.getInt("id_guest"));
                p.setId_publication(rs.getInt("id_publication"));
                p.setNom(rs.getString("nom"));
                p.setComment(rs.getString("comment"));
                p.setDate_com(rs.getDate("date_com"));
                
                commentaires.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commentaires;
    }
    
    public void modifierCommentaire( Commentaire p, int id){
        try {
            String sql = "UPDATE commentaire SET nom=?,comment=? WHERE id=?";
            
            ste=mc.prepareStatement(sql);
            
            ste.setString(1,p.getNom());
            ste.setString(2, p.getComment());
            ste.setInt(3, id);
           
           
            
            
            ste.executeUpdate();
            System.out.println("C0mmentaire modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerCommentaire(int id) {
         try {
            String sql = "DELETE FROM commentaire WHERE id=?;";
            
            ste=mc.prepareStatement(sql);
			
			ste.setInt(1,id);
			
			int executeUpdate = ste.executeUpdate();
			
			if(executeUpdate ==1){
				System.out.println("Commentaire supprimé avec ID::"+id);
			}
		} catch (SQLException e) {
		}
	
	}
    
}
