/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.entities;

/**
 *
 * @author l3ej
 */
public class PanierDetails {
    private int idCommande;
    private String id_prod;
    private int quantite;
    private Double prix;

    public PanierDetails(String id_prod,int id_panier, int quantite, Double prix) {
        this.idCommande = id_panier;
        this.id_prod = id_prod;
        this.quantite = quantite;
        this.prix = prix*quantite;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getId_prod() {
        return id_prod;
    }

    public void setId_prod(String id_prod) {
        this.id_prod = id_prod;
    }

    

    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "PanierDetails{" + "idCommande=" + idCommande + ", id_prod=" + id_prod + ", quantite=" + quantite + ", prix=" + prix + '}';
    }

    
    
    
    
    
    
    
}
