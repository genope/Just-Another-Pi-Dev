package shared.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Session;
import shared.entities.User;
import shared.entities.enums.Role;
import shared.services.UserService;
import shared.services.UserSession;

public class Login_pageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView anchorPane;
    @FXML
    private Button login_button;
    @FXML
    private Button Register_button;
    @FXML
    private JFXTextField emailtxt;
    @FXML
    private JFXPasswordField passwordtxt;
    @FXML
    private Hyperlink fp_hyperlink;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane parentContainer;
    @FXML
    private JFXRadioButton remember_me;
    private final String path = "src\\LoginData.ini";
    UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService userService = new UserService();
        userService.readinifile(path, emailtxt, passwordtxt, remember_me);
    }

    public JFXTextField getEmailtxt() {
        return emailtxt;
    }

    public void setEmailtxt(JFXTextField emailtxt) {
        this.emailtxt = emailtxt;
    }

    public JFXPasswordField getPasswordtxt() {
        return passwordtxt;
    }

    public void setPasswordtxt(JFXPasswordField passwordtxt) {
        this.passwordtxt = passwordtxt;
    }

    @FXML
    private void attempteToLogin(ActionEvent event) throws IOException {
        if (event.getSource() == login_button) {
            if (validatenGuest_Email(emailtxt) & validatenGuest_Password(passwordtxt)) {
               
                User user = userService.GetUserByMail(emailtxt.getText(), passwordtxt.getText());
                 UserService.userSession = new UserSession();

                Role role = user.getRole();
                if (null != role) {
                    switch (role) {
                        case Guest:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileGuest.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, emailtxt.getText(), passwordtxt.getText());
                                UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileGuest.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                            break;
                        case Host:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileHost.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, emailtxt.getText(), passwordtxt.getText());
                                UserService.userSession.setUserCin(user.getCin());
                                System.out.println(UserSession.userString);
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileHost.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            }
                            break;
                        case Admin:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                UserService.userSession = new UserSession();
                                UserService.userSession.setUserCin(user.getCin());
                                System.out.println(UserService.userSession.getUser());
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileAdmin.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, emailtxt.getText(), passwordtxt.getText());
                                UserService.userSession = new UserSession();
                                UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ProfileAdmin.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @FXML
    private void forgetPassword(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("forgetPassword.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.translateYProperty().set(stage.getHeight());
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event3) -> {
                parentContainer.getChildren().remove(rootPane);

            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void registerClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register_user.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.translateYProperty().set(stage.getHeight());
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                parentContainer.getChildren().remove(rootPane);

            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
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

    @FXML
    private void signUp(ActionEvent event) {
    }

}
