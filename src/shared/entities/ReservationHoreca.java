/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author USER
 */
public class ReservationHoreca extends Reservation{
    
     private int idHoreca; //fk
     private Timestamp datedebutReservH;
          private Date datefinReservH;

    public ReservationHoreca(int idReserv, int idGuest,int idHoreca, Timestamp datedebutReservH, Date datefinReservH) {
        super(idReserv, idGuest);
        this.idHoreca = idHoreca;
        this.datedebutReservH = datedebutReservH;
        this.datefinReservH = datefinReservH;
    }

    public int getIdHoreca() {
        return idHoreca;
    }

    public void setIdHoreca(int idHoreca) {
        this.idHoreca = idHoreca;
    }

    public ReservationHoreca() {
        super();
    }

    public Timestamp getDatedebutReservH() {
        return datedebutReservH;
    }

    public void setDatedebutReservH( Timestamp datedebutReservH) {
        this.datedebutReservH = datedebutReservH;
    }

    public Date getDatefinReservH() {
        return datefinReservH;
    }

    public void setDatefinReservH(Date datefinReservH) {
        this.datefinReservH = datefinReservH;
    }

    @Override
    public String toString() {
        return "ReservationHoreca" +super.toString()+ "idHoreca=" + idHoreca + ", datedebutReservH=" + datedebutReservH + ", datefinReservH=" + datefinReservH  ;
    }
          
          
          
          
}
