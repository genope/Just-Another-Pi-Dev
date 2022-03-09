/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import shared.entities.User;
import shared.entities.enums.Role;
import shared.services.UserService;
import static shared.services.UserService.userSession;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author genop
 */
public class ProfileAdminController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
    @FXML
    private JFXTextField adminpagename;
    @FXML
    private JFXTextField adminpagelastname;
    @FXML
    private JFXTextField adminpagetel;
    @FXML
    private JFXTextField adminpageaddress;
    @FXML
    private JFXDatePicker adminpagedate;
    @FXML
    private JFXButton updateadmin;
    @FXML
    private Circle HostPic;
    @FXML
    private JFXPasswordField adminpagepass;
    File file;

    /**
     * Initializes the controller class.
     */
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
                    HostPic.setFill(new ImagePattern(imge));
                    cir2.setFill(new ImagePattern(imge));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        LocalDate ddn = userSession.getUser().getDdn().toLocalDate();
        adminpagename.setText(userSession.getUser().getNom());
        adminpagelastname.setText(userSession.getUser().getPrenom());
        adminpagetel.setText(String.valueOf(userSession.getUser().getNumber()));
        adminpageaddress.setText(userSession.getUser().getAdress_host());
        adminpagedate.setValue(ddn);
        adminpagepass.setText("");
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void updtahost(ActionEvent event) {
          java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(adminpagedate.getValue());
        UserService u = new UserService();
        UserSession session = new UserSession();
        int cin = session.getUser().getCin();
        Role role = session.getUser().getRole();
        String email = session.getUser().getEmail();

        String name = adminpagename.getText();
        String lastname = adminpagelastname.getText();
        int tel = Integer.parseInt(adminpagetel.getText());
        String address = adminpageaddress.getText();
        String password = adminpagepass.getText();
        User user = new User(name, lastname, password, gettedDatePickerDate, tel, address, cin, email, role);
        u.UpdateUser(user);
        LocalDate ddn = userSession.getUser().getDdn().toLocalDate();
    }

    @FXML
    private void updatePIc(ActionEvent event) throws FileNotFoundException, IOException {
          UserService u = new UserService();
        UserSession session = new UserSession();
        FileInputStream fl = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        String fileName3 = file.getName();
        fl.read(data);
        fl.close();
        int cin = session.getUser().getCin();
        String email = session.getUser().getEmail();
        User user = new User(cin, email, fileName3);
        u.updatePic(user);
    }

    @FXML
    private void image_admin(MouseEvent event) {
         Path to11 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\uploads\\images";
        JFileChooser chooser1 = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser1.setFileFilter(filter);
        int returnVal = chooser1.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser1.getSelectedFile().getAbsolutePath();

            file = chooser1.getSelectedFile();
            String fileName = file.getName();
            if (chooser1.getSelectedFile() != null) {

                try {

                    Path from1 = Paths.get(chooser1.getSelectedFile().toURI());
                    to11 = Paths.get(path + "\\" + fileName);

                    CopyOption[] options1 = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };

                    Files.copy(from1, to11, options1);
                    //  System.out.println(file);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }
    
}
