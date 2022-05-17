/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import shared.services.UserService;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author genop
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private ImageView anchorPane;
    @FXML
    private JFXTextField mailid;
    @FXML
    private Button btnid;
    private DataSource cnx;
    public ResultSet rs;
    public String y, z;
    public String username, pass, mesg;
    UserService userService = new UserService();
    UserSession userSession ;
    public static final String ACCOUNT_SID = "AC90ac083ebfd6f6485124fb25d08fbbb0";
    public static final String AUTH_TOKEN = "d9964c385389b51c5fe3ae2464a8b1b0";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
       

    }

    @FXML
    private void SendMail(ActionEvent event) throws MessagingException, IOException {
        
        
        if (mailid.getText().isEmpty()) {
            mailid.setText("remarque : email vide");
        } else if (!mailid.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2}")) {
            mailid.setText("remarque : email non valide");
        } else {
           userService.GetuserBytel(mailid.getText()).toString();
            
            y = getSaltString();
            z = mailid.getText();
            try {
                String user = userService.sendMail(mailid.getText());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            mesg = "votre code est : " + y;
             
              Twilio.init(ACCOUNT_SID, AUTH_TOKEN);               
                com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216"+userService.GetuserBytel(mailid.getText()).toString()),
                        new PhoneNumber("+19108308627"), y).create();

FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Code.fxml"));
Parent root = loader.load();
CodeController ccc = loader.getController();
ccc.setEmail(z);
ccc.setCode(y);
mailid.getScene().setRoot(root);

        }
    }

    public JFXTextField getMailid() {
        return mailid;
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
