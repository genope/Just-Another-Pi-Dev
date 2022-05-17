package shared.main;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shared.connexion.MaConnexion;

/**
 *
 * @author genop
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
                    MaConnexion.getInstance();
                    
                    

            Parent root =FXMLLoader.load(getClass().getResource("../gui/Register_user.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../gui/Style/controls.css").toExternalForm());
           primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
