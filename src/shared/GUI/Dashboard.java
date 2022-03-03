package dashboard;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dashboard extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            Scene scene = new Scene(root);
        
        primaryStage.setTitle("Gerer les produits");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
