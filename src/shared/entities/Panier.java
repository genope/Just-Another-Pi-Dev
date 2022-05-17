/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.entities;

/**
 *
 * @author l3ej
 */
public class Panier {
    private int id_Panier;
    private int CIN;
    private Double total;
    
    public Panier(int id_Panier, int CIN, Double total) {
        this.id_Panier = id_Panier;
        this.CIN = CIN;
        this.total = total;
    }
    

    public int getId_Panier() {
        return id_Panier;
    }

    public void setId_Panier(int id_Panier) {
        this.id_Panier = id_Panier;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_Panier=" + id_Panier + ", CIN=" + CIN + ", total=" + total + '}';
    }

   
    
    
    
    
}
