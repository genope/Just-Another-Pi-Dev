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
public class ReservationHoreca extends Reservation{
    
     private int idHoreca; //fk
     private Date datedebutReservL;
          private Date datefinReservL;

    public ReservationHoreca(int idHoreca, Date datedebutReservL, Date datefinReservL, int idReserv, int idGuest) {
        super(idReserv, idGuest);
        this.idHoreca = idHoreca;
        this.datedebutReservL = datedebutReservL;
        this.datefinReservL = datefinReservL;
    }

    public int getIdHoreca() {
        return idHoreca;
    }

    public void setIdHoreca(int idHoreca) {
        this.idHoreca = idHoreca;
    }

    public Date getDatedebutReservL() {
        return datedebutReservL;
    }

    public void setDatedebutReservL(Date datedebutReservL) {
        this.datedebutReservL = datedebutReservL;
    }

    public Date getDatefinReservL() {
        return datefinReservL;
    }

    public void setDatefinReservL(Date datefinReservL) {
        this.datefinReservL = datefinReservL;
    }
          
          
          
          
}
