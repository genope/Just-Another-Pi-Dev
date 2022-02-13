/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
