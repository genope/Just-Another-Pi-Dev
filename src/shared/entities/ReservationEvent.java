/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.util.Date;

/**
 *
 * @author USER
 */
public class ReservationEvent extends Reservation{
    private int idEvent;
     private Date datedebutReservE;
          private Date datefinReservE;

    public ReservationEvent(int idEvent, Date datedebutReservE, Date datefinReservE, int idReserv, int idGuest) {
        super(idReserv, idGuest);
        this.idEvent = idEvent;
        this.datedebutReservE = datedebutReservE;
        this.datefinReservE = datefinReservE;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Date getDatedebutReservE() {
        return datedebutReservE;
    }

    public void setDatedebutReservE(Date datedebutReservE) {
        this.datedebutReservE = datedebutReservE;
    }

    public Date getDatefinReservE() {
        return datefinReservE;
    }

    public void setDatefinReservE(Date datefinReservE) {
        this.datefinReservE = datefinReservE;
    }
          
}
