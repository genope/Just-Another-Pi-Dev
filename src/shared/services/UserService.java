/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import static java.awt.SystemColor.window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import shared.connexion.MaConnexion;
import shared.entities.User;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;

public class UserService {

    public User GetUserByMail(String mail, String password) {
        User user = null;
        String pass = "";
        Role role = null;
        try {
            String requete = "Select role,password, Email from user where Email = ?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);

            System.out.println(MaConnexion.getInstance().getCnx());

            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();

            while (rs.next()) {
                pass = rs.getString("password");
                role = Role.valueOf(rs.getString("role"));
            }
            //decrypt pass :
            //pass = decrypt(pass);

            if (password.equals(pass)) {
                System.out.println("bon pass");
                requete = "SELECT cin,nom,prenom,datedenaissance,telephone,etat FROM user WHERE Email like ?";
                pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, mail);
                rs = pst.executeQuery();

                while (rs.next()) {
                    System.out.println("bon pass");

                    user = new User(
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            mail,
                            pass,
                            rs.getDate("datedenaissance"),
                            rs.getInt("telephone"),
                            role,
                            Etat.valueOf(rs.getString("etat")),
                            null,
                            null
                    );
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++" + user);
                }
                System.out.println(role);
                if (Role.Host.equals(role)) {
                    requete = "SELECT image_cin,adress_host From user WHERE Email = ?";
                    pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                    pst.setString(1, mail);
                    rs = pst.executeQuery();

                    while (rs.next()) {

                        user.setAdress_host(rs.getString("adress_host"));
                        user.setImage_cin(rs.getString("image_cin"));
                        System.out.println("____________________" + user);
                    }
                }

            } else {
                System.out.println("!!!!!!!!!!!!!!");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return user;
    }

    //DATE_ADD(?, INTERVAL -22801 MONTH)
    public boolean register(User user) {
        Statement stmt;

        try {
            String requete = "insert into user (cin,nom,prenom,email,password,datedenaissance,telephone,role,etat,image_cin,adress_host)values (?,?,?,?,?,DATE_ADD(?, INTERVAL -22801 MONTH),?,?,?,?,?)";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, user.getCin());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            pst.setDate(6, user.getDdn());
            pst.setInt(7, user.getNumber());
            pst.setString(8, user.getPassword());
            pst.setDate(6, user.getDdn());
            pst.setInt(7, user.getNumber());
            pst.setString(8, user.getRole().toString());
            pst.setString(9, user.getEtat().toString());
            if (Role.Host.equals(user.getRole())) {
                pst.setString(10, user.getImage_cin());
                pst.setString(11, user.getAdress_host());
            } else {
                pst.setString(10, null);
                pst.setString(11, null);
            }

            if (pst.executeUpdate() > 0) {
                System.out.println("You have registered successfully.");
                return true;
            } else {
                System.out.println("Something went wrong.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean UpdateUser(User user) {
        try {
            String requete = "UPDATE  user "
                    + "set nom=?,prenom=?,"
                    + "password=?,"
                    + "datedenaissance=?,"
                    + "telephone=?,role=?,etat=?,"
                    + "image_cin=?,adress_host=?"
                    + " where cin=? AND email=?";

            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getPassword());
            pst.setDate(4, user.getDdn());
            pst.setInt(5, user.getNumber());
            pst.setString(6, user.getRole().toString());
            pst.setString(7, user.getEtat().toString());

            if (Role.Host.equals(user.getRole())) {
                pst.setString(8, user.getImage_cin());
                pst.setString(9, user.getAdress_host());
            } else {
                pst.setString(8, null);
                pst.setString(9, null);
            }

            //set block where 
            pst.setInt(10, user.getCin());
            pst.setString(11, user.getEmail());
            System.out.println(pst);
            if (pst.executeUpdate() > 0) {
                System.out.println("You have updated successfully.");
                return true;
            } else {
                System.out.println("Something went wrong.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        //return false;
    }

    public User GetUserByCin(int cin) {
        User user = null;
        Role role = null;
        try {
            String requete = "Select role, cin from user where cin = ?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);

            System.out.println(MaConnexion.getInstance().getCnx());

            pst.setInt(1, cin);
            ResultSet rs;
            rs = pst.executeQuery();

            while (rs.next()) {
                role = Role.valueOf(rs.getString("role"));
            }
            //decrypt pass :
            //pass = decrypt(pass);

            System.out.println("bon pass");
            requete = "SELECT nom,prenom,email,password,datedenaissance,telephone,etat FROM user WHERE cin=?";
            pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, cin);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("bon pass");

                user = new User(
                        cin,
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("datedenaissance"),
                        rs.getInt("telephone"),
                        role,
                        Etat.valueOf(rs.getString("etat")),
                        null,
                        null
                );
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++" + user);
            }
            System.out.println(role);
            if (Role.Host.equals(role)) {
                requete = "SELECT image_cin,adress_host From user WHERE cin= ?";
                pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, cin);
                rs = pst.executeQuery();

                while (rs.next()) {

                    user.setAdress_host(rs.getString("adress_host"));
                    user.setImage_cin(rs.getString("image_cin"));
                    System.out.println("____________________" + user);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return user;
    }
}
