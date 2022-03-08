/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;

import java.sql.Date;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;

/**
 *
 * @author genop
 */
public class User {

    private int cin;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date ddn;
    private int number;
    private Role role;
    private Etat etat;
    private String adress_host;
    private String image_cin;
    private String image_profile;

    public User(int cin, String nom, String prenom, String email, String password, Date ddn, int number, Role role, Etat etat, String adress_host, String image_cin ,String image_profile) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.ddn = ddn;
        this.number = number;
        this.role = role;
        this.etat = etat;
        this.image_profile =image_profile ;
        if (Role.Host.equals(role)) {
            this.adress_host = adress_host;
            this.image_cin = image_cin;
        }else{
            this.adress_host = null;
            this.image_cin = null;
        }
    }

    public User() {
    }

    public User(int number) {
        this.number = number;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public User(String nom, String prenom, String password, Date ddn, int number, String adress_host,int cin,String email,Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.ddn = ddn;
        this.number = number;
        this.cin=cin;
        this.email=email;
        this.role = role;
        if (Role.Host.equals(role)) {
            this.adress_host = adress_host;
        }else{
            this.adress_host = null;
        
        }
    }
    

    public User(int cin, String email, String image_profile) {
        this.cin = cin;
        this.email = email;
        this.image_profile = image_profile;
    }
    

    @Override
    public String toString() {
        return "User{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", ddn=" + ddn + ", number=" + number + ", role=" + role + ", etat=" + etat + ", adress_host=" + adress_host + ", image_cin=" + image_cin +",image_profile"+ '}';
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getAdress_host() {
        return adress_host;
    }

    public void setAdress_host(String adress_host) {
        this.adress_host = adress_host;
    }

    public String getImage_cin() {
        return image_cin;
    }

    public void setImage_cin(String image_cin) {
        this.image_cin = image_cin;
    }

}
