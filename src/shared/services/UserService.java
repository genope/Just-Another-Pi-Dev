/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import com.jfoenix.controls.JFXRadioButton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import shared.connexion.MaConnexion;
import shared.entities.User;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import org.ini4j.Wini;
import org.mindrot.jbcrypt.BCrypt;
import shared.gui.Login_pageController;

public class UserService {

    public ResultSet rs;
    public int x;
    public String y, z;
    public String n, m;
    public String passwordF;
    public static UserSession userSession;

    public Integer GetuserBytel(String email) {
        User user = null;
        int number = 0;
        try {
            String requete = "Select Telephone from user where Email = ?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                number = rs.getInt("Telephone");
                user = new User(
                        rs.getInt("Telephone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public User GetUserByMail(String mail, String password) {
        User user = null;
        String pass = "";
        Role role = null;
        try {
            String requete = "Select role,password, Email from user where Email = ?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();

            if (rs.next()) {
                pass = rs.getString("password");
                role = Role.valueOf(rs.getString("role"));
            }

            if (BCrypt.checkpw(password, pass)) {
                System.out.println("aaaa");
                requete = "SELECT cin,nom,prenom,datedenaissance,telephone,etat,image_profile FROM user WHERE Email like ?";
                pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, mail);
                rs = pst.executeQuery();
                while (rs.next()) {
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
                            null,
                            rs.getString("image_profile")
                    );

                }
                System.out.println(user);
                if (Role.Host.equals(role)) {
                    requete = "SELECT image_cin,adress_host From user WHERE Email = ?";
                    pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                    pst.setString(1, mail);
                    rs = pst.executeQuery();

                    while (rs.next()) {

                        user.setAdress_host(rs.getString("adress_host"));
                        user.setImage_cin(rs.getString("image_cin"));
                    }
                } else if (Role.Admin.equals(role)) {
                    requete = "SELECT cin,nom,prenom,datedenaissance,telephone,etat,image_profile FROM user WHERE Email like ?";
                    pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                    pst.setString(1, mail);
                    rs = pst.executeQuery();
                    while (rs.next()) {
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
                                null,
                                rs.getString("image_profile")
                        );

                    }

                }

            } else {
                System.out.println("aaazzzzzz");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException ex) {
            Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
            return null;
        }
        return user;
    }

    //DATE_ADD(?, INTERVAL -22801 MONTH)
    public boolean register(User user) {
        Statement stmt;

        try {
            String requete = "insert into user (cin,nom,prenom,email,password,datedenaissance,telephone,role,etat,image_cin,adress_host,image_profile)values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, user.getCin());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt() ));
            pst.setDate(6, user.getDdn());
            pst.setInt(7, user.getNumber());
            pst.setString(8, user.getRole().toString());
            pst.setString(9, user.getEtat().toString());
            pst.setString(12, user.getImage_profile());

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

    public void updatePic(User user) {
        try {
            String requete = "update user set image_profile=? where cin=? ";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, user.getImage_profile());
            pst.setInt(2, user.getCin());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean UpdateUser(User user) {
        try {

            //if password exist

            if (!user.getPassword().equals("")) {

              String  requete = "UPDATE  user "
                        + "set nom=?,prenom=?,"
                        + "password=?,"
                        + "datedenaissance=?,"
                        + "telephone=?,"
                        + "adress_host=?"
                        + " where cin=? AND email=?";
                PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);

                pst.setString(1, user.getNom());
                pst.setString(2, user.getPrenom());
                pst.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

                pst.setDate(4, user.getDdn());
                pst.setInt(5, user.getNumber());

                if (Role.Host.equals(user.getRole())) {
                    pst.setString(6, user.getAdress_host());
                } else {

                    pst.setString(6, null);
                }
                pst.setInt(7, user.getCin());
                pst.setString(8, user.getEmail());

                if (pst.executeUpdate() > 0) {
                    System.out.println("You have updated successfully.");
                    return true;
                } else {
                    System.out.println("Something went wrong.");
                    return false;
                }
            } else {
               String requete = "UPDATE  user "
                        + "set nom=?,prenom=?,"
                        + "datedenaissance=?,"
                        + "telephone=?,"
                        + "adress_host=?"
                        + " where cin=? AND email=?";
                PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);

                pst.setString(1, user.getNom());
                pst.setString(2, user.getPrenom());

                pst.setDate(3, user.getDdn());
                pst.setInt(4, user.getNumber());

                if (Role.Host.equals(user.getRole())) {
                    pst.setString(5, user.getAdress_host());
                } else {

                    pst.setString(5, null);
                }
                pst.setInt(6, user.getCin());
                pst.setString(7, user.getEmail());

                if (pst.executeUpdate() > 0) {
                    System.out.println("You have updated successfully.");
                    return true;
                } else {
                    System.out.println("Something went wrong.");
                    return false;
                }
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

            pst.setInt(1, cin);
            ResultSet rs;
            rs = pst.executeQuery();

            while (rs.next()) {
                role = Role.valueOf(rs.getString("role"));
            }
            //decrypt pass :
            //pass = decrypt(pass);

            requete = "SELECT nom,prenom,email,password,datedenaissance,telephone,etat,image_profile FROM user WHERE cin=?";
            pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, cin);
            rs = pst.executeQuery();

            while (rs.next()) {

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
                        null,
                        rs.getString("image_profile")
                );
            }

            if (Role.Host.equals(role)) {
                requete = "SELECT image_cin,adress_host From user WHERE cin= ?";
                pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, cin);
                rs = pst.executeQuery();

                while (rs.next()) {

                    user.setAdress_host(rs.getString("adress_host"));
                    user.setImage_cin(rs.getString("image_cin"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return user;
    }

    public void createiniFile(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Email", user);
            wini.put("Login data", "Password", pass);
            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readinifile(String path, TextField userid, PasswordField passid, JFXRadioButton remember_me) {
        File file = new File(path);
        if (file.exists()) {
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data", "Email");
                String password = wini.get("Login data", "Password");
                if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
                    userid.setText(username);
                    passid.setText(password);
                    remember_me.setSelected(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Deleteinfo(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.remove("Login data", "Email");
            wini.remove("Login data", "Password");
            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String sendMail(String mail) throws SQLException {

        String requete = "SELECT email FROM user WHERE email=? ";
        PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, mail);
        ResultSet rs;
        rs = pst.executeQuery();
        while (rs.next()) {
            mail = rs.getString("email");
            //password = rs.getString("password");
        }
        return mail;

    }

    public String sendInfo(String mail) throws SQLException {
        String requete = "SELECT email,password FROM user WHERE email=? ";
        PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, sendMail(mail));
        ResultSet rs;
        rs = pst.executeQuery();
        System.out.println(m + "" + n + "hehhe");
        while (rs.next()) {
            mail = rs.getString("email");
            passwordF = rs.getString("password");

        }
        BCrypt.checkpw(mail, passwordF);
        System.out.println(passwordF);
        System.out.println("qqqq");
        return passwordF;

    }

}
