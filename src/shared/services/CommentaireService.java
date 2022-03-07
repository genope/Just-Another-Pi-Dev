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
        String sql ="insert into commentaire(id,id_guest,id_publication,nom,comment,date_com,note) VALUES(?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.setInt(2, p.getId_guest());
            ste.setInt(3, p.getId_publication());
            ste.setString(4, p.getNom());
            ste.setString(5, p.getComment());
            ste.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ste.setInt(7, p.getNote());
            ste.executeUpdate();
            System.out.println("Commentaire Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public List<Commentaire> afficherCommentairee(int id){
        List<Commentaire> commentaires = new ArrayList<>();
        String sql="select id_guest,nom,comment,date_com,note from commentaire where id_publication='" + id + "'";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Commentaire p = new Commentaire();
              
                p.setId_guest(rs.getInt("id_guest"));
                p.setNom(rs.getString("nom"));
                p.setComment(rs.getString("comment"));
                p.setDate_com(rs.getDate("date_com"));
                p.setNote(rs.getInt("note"));
                
                
                commentaires.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commentaires;
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
                p.setNote(rs.getInt("note"));
                
                commentaires.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commentaires;
    }
    
    public void modifierCommentaire( Commentaire p, int id){
        try {
            String sql = "UPDATE commentaire SET nom=?,comment=?,note=? WHERE id=?";
            
            ste=mc.prepareStatement(sql);
            
            ste.setString(1,p.getNom());
            ste.setString(2, p.getComment());
            ste.setInt(3, p.getNote());
           ste.setInt(4, id);
           
            
            
            ste.executeUpdate();
            System.out.println("C0mmentaire modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerCommentairee(int id , int id_guest) {
         try {
            String sql = "DELETE FROM commentaire WHERE id=? AND id_guest=?;";
            
            ste=mc.prepareStatement(sql);
			
			ste.setInt(1,id);
                        ste.setInt(2,id_guest);
			
			int executeUpdate = ste.executeUpdate();
			
			if(executeUpdate ==1){
				System.out.println("Commentaire supprimé avec ID::"+id);
			}
		} catch (SQLException e) {
		}
	
	}
    
       public void supprimerCommentaire(Commentaire c) {
    int n=0;
		PreparedStatement st;
		try {
			
			
			st= mc.prepareStatement("DELETE FROM `commentaire` WHERE `id`=?");
			
			st.setInt(1,c.getId());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}}
//    public void supprimerCommentaire(int id) {
//         try {
//            String sql = "DELETE FROM commentaire WHERE id=?;";
//            
//            ste=mc.prepareStatement(sql);
//			
//			ste.setInt(1,id);
//			
//			int executeUpdate = ste.executeUpdate();
//			
//			if(executeUpdate ==1){
//				System.out.println("Commentaire supprimé avec ID::"+id);
//			}
//		} catch (SQLException e) {
//		}
//	
//	}
      
//    

//    public int getNbrCommentaire() {
//        String sql="SELECT COUNT(*) FROM commentaire where ";
//        ResultSet rs;
//        int countIdRec=0;
//        try {
//            PreparedStatement st= con.prepareStatement(sql);
//			ResultSet res= st.executeQuery(); 
//                        while(res.next()) {
//                           countIdRec= res.getInt(1);
//                        }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return countIdRec;
//    }

public int GEtMoyRating(int id) {  

        int i = 0;
        try {
            String sqlStationName = " select AVG(note) as moyenne from commentaire where id_publication="+id;
            Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                i = (int)rs.getDouble("moyenne");
              //  System.out.println("i= "+i);

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return i;
    }
public String GetNomuser(int id) {
        String reg = null;
        try {
            String sqlStationName = " select Nom from user where CIN=" + id;
            Statement st3 = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                reg = rs.getString("Nom");

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return reg;
    }
   
}



