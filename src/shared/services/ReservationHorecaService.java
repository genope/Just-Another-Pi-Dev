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
import shared.entities.ReservationHoreca;

/**
 *
 * @author USER
 */
public class ReservationHorecaService {
          Connection mc;
    PreparedStatement ste;
 public ReservationHorecaService() { 
        mc=MaConnexion.getInstance().getCnx(); //recuperer la connexion cnx
    }
    
    public void ajouterReservationL(ReservationHoreca ml){
     String sql ="insert into reservationhoreca(idguest,idhoreca,datedebutreservh,datefinreservh) Values(?,?,?,?)"; 
        try {
              java.util.Date utilDate = new java.util.Date();
              java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
            ste=mc.prepareStatement(sql);
            ste.setInt(1, ml.getIdGuest()); 
            ste.setInt(2, ml.getIdHoreca()); 
            ste.setTimestamp(3,ml.getDatedebutReservH());
            ste.setDate(4, ml.getDatefinReservH());
            ste.executeUpdate();
            System.out.println("Reservation de horeca ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }
     public List<ReservationHoreca> afficherReservationH(){
       
            List<ReservationHoreca> ReservationsHor = new ArrayList<>();
        String sql="select * from reservationhoreca";
        try {
          
            ste=mc.prepareStatement(sql); 
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               ReservationHoreca ml = new ReservationHoreca();
                ml.setIdGuest(rs.getInt("idguest"));
                ml.setIdHoreca(rs.getInt("idhoreca")); 
                ml.setDatedebutReservH(rs.getTimestamp("datedebutreservh"));
                ml.setDatefinReservH(rs.getDate("datefinreservh"));
                ReservationsHor.add(ml); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationsHor; //list  
    }
     
          public int modifierReservationHoreca(ReservationHoreca mt,int idReserv) {
        int nbModif = 0;
        try {
            String sql = "UPDATE reservationhoreca SET idhoreca=?,datedebutreservh=? , datefinreservh=? where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1,mt.getIdHoreca());
            ste.setTimestamp(2,mt.getDatedebutReservH());
            ste.setDate(3,mt.getDatefinReservH());
            ste.setInt(4, idReserv);

            nbModif = ste.executeUpdate();
            System.out.println("Reservation de horeca modifiée");
            //System.out.println(ste);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbModif;
    }
    
      public int supprimerReservationHoreca(int idReserv) {
            int nb = 0;
        try {
            String sql = "DELETE FROM Reservationhoreca where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1, idReserv);
            nb = ste.executeUpdate();
            System.out.println("réservation de horeca supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
     
}
}
