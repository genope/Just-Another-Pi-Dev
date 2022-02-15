/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import shared.entities.ReservationMoyenTransport;
import shared.connexion.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author USER
 */
public class ReservationMoyenTransportService {
       Connection mc;
    PreparedStatement ste;

    public ReservationMoyenTransportService() { 
        mc=MaConnexion.getInstance().getCnx(); //recuperer la connexion cnx
    }
    
    public void ajouterReservationMT(ReservationMoyenTransport mt){
        String sql ="insert into reservationmoyentransport(idguest,idmoyt,datedebutreservmt,datefinreservmt) Values(?,?,?,?)"; 
        try {
              java.util.Date utilDate = new java.util.Date();
                          java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
            ste=mc.prepareStatement(sql);
            ste.setInt(1, mt.getIdGuest()); 
            ste.setInt(2, mt.getIdMoyt()); 
            ste.setTimestamp(3,mt.getDatedebutReservMt());
            ste.setDate(4,mt.getDatefinReservMt());
          
            ste.executeUpdate();
            System.out.println("Reservation de moyen de transport ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<ReservationMoyenTransport> afficherReservationMT(){
        List<ReservationMoyenTransport> ReservationsMT = new ArrayList<>();
        String sql="select * from reservationmoyentransport";
        try {
          
            ste=mc.prepareStatement(sql); 
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               ReservationMoyenTransport mt = new ReservationMoyenTransport();
                mt.setIdGuest(rs.getInt("idguest"));
                mt.setIdMoyt(rs.getInt("idmoyt")); 
                mt.setDatedebutReservMt(rs.getTimestamp("datedebutreservmt"));
                mt.setDatefinReservMt(rs.getDate("datefinreservmt"));
                ReservationsMT.add(mt); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ReservationsMT; //list 
    }  
    
     public int modifierReservationMT(ReservationMoyenTransport mt,int idReserv) {
        int nbModif = 0;
        try {
            String sql = "UPDATE reservationmoyentransport SET idmoyt=?,datedebutreservmt=? , datefinreservmt=? where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1,mt.getIdMoyt());
            ste.setTimestamp(2,mt.getDatedebutReservMt());
            ste.setDate(3,mt.getDatefinReservMt());
            ste.setInt(4, idReserv);

            nbModif = ste.executeUpdate();
            System.out.println("Reservation de moyen de transport modifiée");
            System.out.println(ste);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbModif;
    }
    
      public int supprimerReservationMoyenTransport(int idReserv) {
            int nb = 0;
        try {
            String sql = "DELETE FROM ReservationMoyenTransport where idreserv=?";
            ste=mc.prepareStatement(sql);
            ste.setInt(1, idReserv);
            nb = ste.executeUpdate();
            System.out.println("réservation de moyen de transport supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
     
}
}