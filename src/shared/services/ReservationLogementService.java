/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;
import shared.entities.ReservationLogement;
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
public class ReservationLogementService {
      Connection mc;
    PreparedStatement ste;
 public ReservationLogementService() { 
        mc=MaConnexion.getInstance().getCnx(); //recuperer la connexion cnx
    }
    
    public void ajouterReservationL(ReservationLogement ml){
     String sql ="insert into reservationlogement(idguest,idlog,datedebutreservl,datefinreservl) Values(?,?,?,?)"; 
        try {
              java.util.Date utilDate = new java.util.Date();
              java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
            ste=mc.prepareStatement(sql);
            ste.setInt(1, ml.getIdGuest()); 
            ste.setInt(2, ml.getIdLog()); 
            ste.setTimestamp(3,ml.getDatedebutReservL());
            ste.setDate(4, ml.getDatefinReservL());
            ste.executeUpdate();
            System.out.println("Reservation de logement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }
     public List<ReservationLogement> afficherReservationLog(){
       
            List<ReservationLogement> ReservationsLog = new ArrayList<>();
        String sql="select * from reservationlogement";
        try {
          
            ste=mc.prepareStatement(sql); 
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               ReservationLogement ml = new ReservationLogement();
                ml.setIdGuest(rs.getInt("idguest"));
                ml.setIdLog(rs.getInt("idlog")); 
                ml.setDatedebutReservL(rs.getTimestamp("datedebutreservl"));
                ml.setDatefinReservL(rs.getDate("datefinreservl"));
                ReservationsLog.add(ml); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationsLog; //list  
    }
}
