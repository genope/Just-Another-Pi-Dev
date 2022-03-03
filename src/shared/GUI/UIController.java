package shared.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class UIController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Pane CouleurFond;
    @FXML
    private Button btnLesProduit;
    @FXML
    private Button btnToADD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("AjouterProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LesProduit(ActionEvent event) {
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("AffichageProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Ajouterlesprod(ActionEvent event) {
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("AjouterProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
