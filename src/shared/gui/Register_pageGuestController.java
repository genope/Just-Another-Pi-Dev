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
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import shared.entities.User;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;
import shared.services.UserService;
import shared.services.UserSession;
import shared.utils.UploadAPI;

/**
 * FXML Controller class
 *
 * @author genop
 */
public class Register_pageGuestController implements Initializable {

    UserService userService = new UserService();
    UserSession userSession = new UserSession();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private AnchorPane anchorpane2;
    @FXML
    private TabPane tabpane;
    @FXML
    private Tab tabGuest;
    @FXML
    private JFXTextField cinGuest;
    @FXML
    private JFXTextField nameGuest;
    @FXML
    private JFXTextField LastnameGuest;
    @FXML
    private JFXTextField EmailGuest;
    @FXML
    private JFXPasswordField passwordGuest;
    @FXML
    private JFXTextField telNumberGuest;
    @FXML
    private JFXDatePicker ddnGuest;
    @FXML
    private Tab tabHost;
    @FXML
    private JFXTextField cinHost;
    @FXML
    private JFXTextField nameHost;
    @FXML
    private JFXTextField lastNameHost;
    @FXML
    private JFXTextField emailHost;
    @FXML
    private JFXPasswordField passwordHost;
    @FXML
    private JFXTextField telNumberHost;
    @FXML
    private JFXDatePicker ddnHost;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblGuest;
    @FXML
    private Label lblHost;
    @FXML
    private Label lblStatus;

    private static Stage pStage;

    File file;
    File file1;
    File file2;
    File file3;
    public static Stage getpStage() {
        return pStage;
    }

    public static void setpStage(Stage pStage) {
        Register_pageGuestController.pStage = pStage;
    }
    @FXML
    private JFXTextField telNumberHost1;
    @FXML
    private Button register_guest;
    @FXML
    private JFXButton id_user;
    @FXML
    private Button register_host;
    @FXML
    private JFXButton profilePic_guest;
    @FXML
    private JFXButton profilePic_host;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void openGuestTab(MouseEvent event) {
        TranslateTransition toRightAnimation = new TranslateTransition(new Duration(200), lblStatus);
        toRightAnimation.setToX(slidingPane.getTranslateX() + (lblStatus.getPrefWidth() - slidingPane.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> {
            lblStatus.setText("Join Us As a Host");

        });
        tabpane.getSelectionModel().select(tabHost);
    }

    @FXML
    private void openHostTab(MouseEvent event) {
        TranslateTransition toLeftAnimation = new TranslateTransition(new Duration(200), lblStatus);
        toLeftAnimation.setToX(slidingPane.getTranslateX());
        toLeftAnimation.play();
        toLeftAnimation.setOnFinished((ActionEvent event2) -> {
            lblStatus.setText("Join Us As a Guest");

        });
        tabpane.getSelectionModel().select(tabGuest);

    }

//    private void upload_cin(ActionEvent event) {
//        try{        
//            FileChooser fc = new FileChooser();
//            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(.jpg)", ".jpg");
//            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("JPEG files(.jpeg)", ".jpeg");
//            FileChooser.ExtensionFilter ext3 = new FileChooser.ExtensionFilter("PNG files(.png)", ".png");
//            //fc.getExtensionFilters().addAll(ext1, ext2, ext3);
//            
//  file = fc.showOpenDialog(Register_pageGuestController.getpStage());
//            BufferedImage bf;
//            bf = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bf, null);
//        }catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }
//    }
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

    private void loginP(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.translateYProperty().set(stage.getHeight());
            anchorpane2.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                anchorpane2.getChildren().remove(anchorpane2);

            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void registerguest(MouseEvent event) throws IOException {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(ddnGuest.getValue());
        if (event.getSource() == register_guest) {
            if (validatenGuest_Name(nameGuest) & validatenGuest_Name(LastnameGuest) & validateCin(cinGuest) & validateTel(telNumberGuest) & validatenGuest_Email(EmailGuest) & validatenGuest_Password(passwordGuest)) {
               FileInputStream f3 = new FileInputStream(file3);
                byte[] data3 = new byte[(int) file3.length()];
                String fileName3 = file.getName();
                f3.read(data3);
                f3.close();
                User user = new User(Integer.parseInt(cinGuest.getText()), nameGuest.getText(), LastnameGuest.getText(),
                        EmailGuest.getText(), passwordGuest.getText(),
                        gettedDatePickerDate,
                        Integer.parseInt(telNumberGuest.getText()),
                        Role.Guest, "[\"Approved\"]", telNumberHost1.getText(), id_host.getText(),fileName3,"[\"ROLE_GUEST\"]");
                userService.register(user);
                Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    private void uploadID(MouseEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\uploads\\images";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {

                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };

                    System.out.println(to1);
                    System.out.println("hhhh");
                    System.out.println(from);
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    //  System.out.println(file);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }

    @FXML
    private void registerhost(MouseEvent event) throws IOException, Exception {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(ddnHost.getValue());
        if (event.getSource() == register_host) {
            if (validatenGuest_Name(nameHost) & validatenGuest_Name(lastNameHost) & validatenGuest_Name(telNumberHost1) & validateCin(cinHost) & validateTel(telNumberHost) & validatenGuest_Email(emailHost) & validatenGuest_Password(passwordHost)) {
                FileInputStream fl = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                String fileName = file.getName();
                fl.read(data);
                fl.close();
                  FileInputStream f2 = new FileInputStream(file2);
                byte[] data1 = new byte[(int) file2.length()];
                String fileP2 = file2.getName();
                f2.read(data1);
                f2.close();
                User user = new User(Integer.parseInt(cinHost.getText()), nameHost.getText(), lastNameHost.getText(),
                        emailHost.getText(), passwordHost.getText(),
                        gettedDatePickerDate,
                        Integer.parseInt(telNumberHost.getText()),
                        Role.Host,"[\"Approved\"]", telNumberHost1.getText(), fileName ,fileP2,"[\"ROLE_HOST\"]");
                userService.register(user);
                Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

    }

    private boolean validatenGuest_Name(TextField name) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(name.getText());
        if ((name.getText().length() == 0)
                || (!m.find() && m.group().equals(name.getText()))) {
            new animatefx.animation.Shake(name).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            name.setEffect(in);
            return false;
        } else {
            name.setEffect(null);
            return true;
        }
    }

    private boolean validateCin(TextField cin) {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(cin.getText());

        if ((cin.getText().length() == 8)
                || (m.find() && m.group().equals(cin.getText()))) {
            cin.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(cin).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            cin.setEffect(in);
            return false;
        }
    }

    private boolean validateTel(TextField tel) {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(tel.getText());

        if ((tel.getText().length() == 8)
                || (m.find() && m.group().equals(tel.getText()))) {
            tel.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tel).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tel.setEffect(in);
            return false;
        }
    }

    private boolean validatenGuest_Email(TextField email) {

        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email.getText());
        if (((email.getText().length() > 8))
                && (m.find() && m.group().equals(email.getText()))) {
            email.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(email).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            email.setEffect(in);
            return false;
        }

    }

    private boolean validatenGuest_Password(TextField password) {

        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(password.getText());
        if (((password.getText().length() > 7))
                && (m.find() && m.group().equals(password.getText()))) {
            password.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(password).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            password.setEffect(in);
            return false;
        }

    }

    @FXML
    private void redirect_login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login_page.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void uploadProfilePicGuest(ActionEvent event) {
        Path to13 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\uploads\\images";
        JFileChooser chooser3 = new JFileChooser();

        FileNameExtensionFilter filter3 = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser3.setFileFilter(filter3);
        int returnVal = chooser3.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser3.getSelectedFile().getAbsolutePath();

           file3 = chooser3.getSelectedFile();
            String fileName = file3.getName();

            if (chooser3.getSelectedFile() != null) {

                try {

                    Path from3 = Paths.get(chooser3.getSelectedFile().toURI());
                    to13 = Paths.get(path + "\\" + fileName);

                    CopyOption[] options3 = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };

                    Files.copy(from3, to13, options3);
                    //  System.out.println(file);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }
    

    @FXML
    private void uploadProfilePicHost(ActionEvent event) {
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

           file2 = chooser1.getSelectedFile();
            String fileName = file2.getName();

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