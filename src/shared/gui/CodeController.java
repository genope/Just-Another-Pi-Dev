/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import com.jfoenix.controls.JFXTextField;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import shared.services.UserService;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author genop
 */
public class CodeController implements Initializable {

    @FXML
    private ImageView anchorPane;
    @FXML
    private JFXTextField mailid;
    @FXML
    private Button btnid;

    public int x;
    public String y, z;
    public String username, pass, mesg, email, code;
    UserService userService = new UserService();
    UserSession userSession = new UserSession();
    Stage stage = new Stage();

    public static final String ACCOUNT_SID = "AC90ac083ebfd6f6485124fb25d08fbbb0";
    public static final String AUTH_TOKEN = "d9964c385389b51c5fe3ae2464a8b1b0";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
        System.out.println("====>" + code);
    }

    

    @FXML
    private void SendMail(ActionEvent event) throws AddressException, MessagingException, IOException, SQLException {

        if (mailid.getText().equals(this.code)) {
            System.out.println(mailid);
      mesg = "votre nom d'utilisateur: " + email + "\n" + "votre mode de pass: " + userService.sendInfo(email);
//
            String from = "kiraamv1337@gmail.com";
            String pass = "A5i3zow0jn";
            String[] to = {mailid.getText()};
            String host = "mail.javatpoint.com";
            String sub = "Password Recovery";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            //get Session   
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, pass);
                }
            });
            //compose message                
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.email));
            message.setSubject(sub);
            message.setText(mesg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
            
   //                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//                
                com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21651095151"),
                        new PhoneNumber("+19108308627"), mesg).create();

            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login_page.fxml")));
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Code Invalide");
        }
    }

    @FXML
    private void mini(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void max(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

}
