/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.entities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author l3ej
 */
public class Produit {

    private int id_prod;
    public String ref_prod;
    public String designation;
    public String description;
    public double prix;
    public Blob image;
    public int qte_stock;
    public String nomCategorie;
    public String region;

    public Produit() {
    }

    public Produit(String ref_prod, String designation, String description, double prix, Blob image, int qte_stock, String nomCategorie, String region) {

        this.ref_prod = ref_prod;
        this.designation = designation;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.qte_stock = qte_stock;
        this.nomCategorie = nomCategorie;
        this.region = region;
    }

    public String getRef_prod() {
        return ref_prod;
    }

    public void setRef_prod(String ref_prod) {
        this.ref_prod = ref_prod;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", ref_prod=" + ref_prod + ", designation=" + designation + ", description=" + description + ", prix=" + prix + ", image=" + image + ", qte_stock=" + qte_stock + ", categorie=" + nomCategorie + '}';
    }

}
