/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.*;

/**
 *
 * @author user
 */
public class Chat {
    private int id;
    private int idUser1;
    private int idUser2;
    private String message;
    private Timestamp dateEnvoi;

    public Chat() {
    }

    
    
    
    public Chat(int idUser1, int idUser2,String message) {
        this.id = id;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.message=message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public Timestamp getDateEnvoi() {
        return dateEnvoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public void setDateEnvoi(Timestamp dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Chat{" + "id=" + id + ", idUser1=" + idUser1 + ", idUser2=" + idUser2 + ", message=" + message+", dateEnvoi=" + dateEnvoi + '}';
    }
    
    
    
}


