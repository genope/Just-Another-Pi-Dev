/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shared.connexion.MaConnexion;
import shared.entities.*;
import shared.entities.enums.TypeLogements;

/**
 *
 * @author user
 */
public class ChatServices {
          Connection mc;
      PreparedStatement ste;
      
      public ChatServices() {
        this.mc = MaConnexion.getInstance().getCnx();
    }
      
     public void ajoutLogement(Chat chat){
        
        String req="insert into chat(id_user1,id_user2,message,dateEnvoi) Values(?,?,?,?)";
        
         try {
             
             java.util.Date utilDate = new java.util.Date();
             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());

            ste=mc.prepareStatement(req);
            ste.setInt(1, chat.getIdUser1());
            ste.setInt(2,chat.getIdUser2());
            ste.setString(3,chat.getMessage());
            ste.setTimestamp(4, sqlTS);
            ste.executeUpdate();
            System.out.println("Chat ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
      
     
             public Chat getChat(int i){
            String req="SELECT * FROM `chat` WHERE id_chat=?";
             Chat chat = new Chat();
             
            try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,i);
                ResultSet rs=ste.executeQuery();
                while(rs.next()){                
                chat.setIdUser1(rs.getInt("id_user1"));
                chat.setIdUser2(rs.getInt("id_user2"));
                chat.setMessage(rs.getString("message"));
                chat.setDateEnvoi(rs.getTimestamp("dateEnvoi"));

                chat.setId(rs.getInt("id_chat"));
                
               
            }  
            } catch (Exception e) {
                System.out.println(ste);
                System.out.println(e.getMessage());
            }
            return chat;
          
        }
             
             
             
             public void suuprimerChat(int o){
        
        String req="DELETE FROM `chat` WHERE id_chat=?";
        
        try {
                ste=mc.prepareStatement(req);
                ste.setInt(1,o);
                ste.executeUpdate();
                System.out.println("Chat supprimée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
