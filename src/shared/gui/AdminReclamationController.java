/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private Button Ajouterpubbtn;
    @FXML
    private HBox HboxAvis;
    @FXML
    private Label HboxReclamation;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouterpub(ActionEvent event) {
    }

    @FXML
    private void GestionAvis(MouseEvent event) {
    }

    @FXML
    private void GestionReclamation(MouseEvent event) {
    }
    
}
