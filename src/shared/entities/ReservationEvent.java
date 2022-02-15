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
public class ReservationEvent extends Reservation{
    private int idEvent;
     private Timestamp datedebutReserE;
          private Date datefinReserE;

    public ReservationEvent(int idReserv, int idGuest,int idEvent,  Timestamp datedebutReserE, Date datefinReserE) {
        super(idReserv, idGuest);
        this.idEvent = idEvent;
        this.datedebutReserE = datedebutReserE;
        this.datefinReserE = datefinReserE;
    }

    public ReservationEvent() {
        super();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public  Timestamp getDatedebutReservE() {
        return datedebutReserE;
    }

    public void setDatedebutReservE( Timestamp datedebutReserE) {
        this.datedebutReserE = datedebutReserE;
    }

    public Date getDatefinReservE() {
        return datefinReserE;
    }

    public void setDatefinReservE(Date datefinReserE) {
        this.datefinReserE = datefinReserE;
    }

    @Override
    public String toString() {
        return "ReservationEvent" +super.toString()+ "idEvent=" + idEvent + ", datedebutReserE=" + datedebutReserE + ", datefinReserE=" + datefinReserE ;

    }
          
}
