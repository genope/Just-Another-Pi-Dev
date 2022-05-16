/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import shared.services.UserService;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author genop
 */
public class ProfileGuestController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private JFXTextField guestpagename;
    @FXML
    private JFXTextField guestpagelastname;
    @FXML
    private JFXTextField guestpagetel;
    @FXML
    private JFXButton updateguest;
    @FXML
    private Circle guestPic;
    @FXML
    private JFXButton updatePIcguest;
    @FXML
    private JFXDatePicker guestpagedate;
    @FXML
    private JFXTextField guestpagepass;
      public List<File> findAllFilesInFolder(File folder) {
        List<File> list = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                list.add(file);

            } else {
                findAllFilesInFolder(file);
            }
        }
        return list;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     UserSession userSession = new UserSession();
        UserService userService = new UserService();

        File folder = new File("C:\\xampp\\htdocs\\uploads\\images");
        Circle cir2 = new Circle(250, 250, 100);
        for (int i = 0; i < findAllFilesInFolder(folder).size(); i++) {
            if (findAllFilesInFolder(folder).get(i).getName().equals(userSession.getUser().getImage_profile())) {
                try {
                    Image imge = new Image(new FileInputStream("C:\\xampp\\htdocs\\uploads\\images\\" + userSession.getUser().getImage_profile()));
                    guestPic.setFill(new ImagePattern(imge));
                    cir2.setFill(new ImagePattern(imge));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        LocalDate ddn = userSession.getUser().getDdn().toLocalDate();
        guestpagename.setText(userSession.getUser().getNom());
        guestpagelastname.setText(userSession.getUser().getPrenom());
        guestpagetel.setText(String.valueOf(userSession.getUser().getNumber()));
        guestpagedate.setValue(ddn);
        guestpagepass.setText("");
    }    


    @FXML
    private void updtaguest(ActionEvent event) {
    }

    @FXML
    private void image_admin(MouseEvent event) {
    }

    @FXML
    private void updatePIcguest(ActionEvent event) {
    }
    
}
