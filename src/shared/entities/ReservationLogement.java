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
public class ReservationLogement extends Reservation {
    private int idLog;
     private Timestamp datedebutReservL;
     private Date datefinReservL;

    public ReservationLogement(int idReserv, int idGuest,int idLog, Timestamp datedebutReservL, Date datefinReservL) {
        super(idReserv, idGuest);
        this.idLog = idLog;
        this.datedebutReservL = datedebutReservL;
        this.datefinReservL = datefinReservL;
    }

    public ReservationLogement() {
        super();
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Timestamp getDatedebutReservL() {
        return datedebutReservL;
    }

    public void setDatedebutReservL(Timestamp datedebutReservL) {
        this.datedebutReservL = datedebutReservL;
    }

    public Date getDatefinReservL() {
        return datefinReservL;
    }

    public void setDatefinReservL(Date datefinReservL) {
        this.datefinReservL = datefinReservL;
    }

    


    @Override
    public String toString() {
        return "Reservation Logement"+super.toString()+" ,idLog=" + idLog + ", datedebutReservL=" + datedebutReservL + ", datefinReservL=" + datefinReservL ;
    }

   
    }
          

