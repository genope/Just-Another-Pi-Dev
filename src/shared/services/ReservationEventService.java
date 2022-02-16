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
import java.util.ArrayList;
import java.util.List;
import shared.connexion.MaConnexion;
import shared.entities.ReservationEvent;

/**
 *
 * @author USER
 */
public class ReservationEventService {
       Connection mc;
    PreparedStatement ste;
 public ReservationEventService() { 
        mc=MaConnexion.getInstance().getCnx(); //recuperer la connexion cnx
    }
    
    public void ajouterReservationE(ReservationEvent ml){
     String sql ="insert into reservationevent(idguest,idevent,datedebutresere,datefinresere) Values(?,?,?,?)"; 
        try {
              java.util.Date utilDate = new java.util.Date();
              java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
            ste=mc.prepareStatement(sql);
            ste.setInt(1, ml.getIdGuest()); 
            ste.setInt(2, ml.getIdEvent()); 
            ste.setTimestamp(3,ml.getDatedebutReservE());
            ste.setDate(4, ml.getDatefinReservE());
            ste.executeUpdate();
            System.out.println("Reservation d'evenement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }
     public List<ReservationEvent> afficherReservationH(){
       
            List<ReservationEvent> ReservationsEv = new ArrayList<>();
        String sql="select * from reservationevent";
        try {
          
            ste=mc.prepareStatement(sql); 
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               ReservationEvent ml = new ReservationEvent();
                ml.setIdGuest(rs.getInt("idguest"));
                ml.setIdEvent(rs.getInt("idevent")); 
                ml.setDatedebutReservE(rs.getTimestamp("datedebutreserve"));
                ml.setDatefinReservE(rs.getDate("datefinreserve"));
                ReservationsEv.add(ml); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationsEv; //list  
    }
     
          public int modifierReservationEvent(ReservationEvent mt,int idReserv) {
        int nbModif = 0;
        try {
            String sql = "UPDATE reservationevent SET idevent=?,datedebutresere=? , datefinresere=? where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1,mt.getIdEvent());
            ste.setTimestamp(2,mt.getDatedebutReservE());
            ste.setDate(3,mt.getDatefinReservE());
            ste.setInt(4, idReserv);
            nbModif = ste.executeUpdate();
            System.out.println("Reservation de evenement modifiée");
            //System.out.println(ste);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbModif;
    }
    
      public int supprimerReservationEvent(int idReserv) {
            int nb = 0;
        try {
            String sql = "DELETE FROM ReservationEvent where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1, idReserv);
            nb = ste.executeUpdate();
            System.out.println("réservation de evenement supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
     
}
}
