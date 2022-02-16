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
public class Reservation {
     private int idReserv,idGuest;
   

    public Reservation() {
    }

    public Reservation(int idReserv, int idGuest) {
        this.idReserv = idReserv;
        this.idGuest = idGuest;
       

    }

    @Override
    public String toString() {
        return "Reservation{" + "id Reservation=" + idReserv + ", id Guest=" + idGuest+  '}';
    }

    public int getIdReserv() {
        return idReserv;
    }

    public void setIdReserv(int idReserv) {
        this.idReserv = idReserv;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    

   

    
    
}
