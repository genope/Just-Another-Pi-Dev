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
public class ReservationMoyenTransport extends Reservation {
     private int idMoyt; //fk 
     private Timestamp datedebutReservMt;
          private Date datefinReservMt;

 
    public ReservationMoyenTransport(int idReserv, int idGuest,int idMoyt, Timestamp datedebutReservMt, Date datefinReservMt) {
       super(idReserv, idGuest);
        this.idMoyt = idMoyt;
        this.datedebutReservMt = datedebutReservMt;
        this.datefinReservMt = datefinReservMt; 
    }

    public int getIdMoyt() {
        return idMoyt;
    }

    public void setIdMoyt(int idMoyt) {
        this.idMoyt = idMoyt;
    }

    public Timestamp getDatedebutReservMt() {
        return datedebutReservMt;
    }

    public void setDatedebutReservMt(Timestamp datedebutReservMt) {
        this.datedebutReservMt = datedebutReservMt;
    }

    public Date getDatefinReservMt() {
        return datefinReservMt;
    }

    public void setDatefinReservMt(Date datefinReservMt) {
        this.datefinReservMt = datefinReservMt;
    }

    public ReservationMoyenTransport() {
    }

    @Override
    public String toString() {
        return "ReservationMoyenTransport{" + "idMoyt=" + idMoyt + ", datedebutReservMt=" + datedebutReservMt + ", datefinReservMt=" + datefinReservMt + '}';
    }

}
