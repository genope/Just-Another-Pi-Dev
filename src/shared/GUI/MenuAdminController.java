/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import shared.services.UserService;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MenuAdminController implements Initializable {

    @FXML
    private Button btnOrders;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */

    
        UserService userservice=new UserService();
    UserSession usersession=new UserSession();
    @FXML
    private Button ApprouveOffres;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    




    @FXML
    private void DashboardAdmin(ActionEvent event) {
    }

    @FXML
    private void ApprouverOffres(ActionEvent event) {
            grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("DashboardAdmin.fxml"));
                 
            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ProfileAdmin(ActionEvent event) {
            grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ProfileAdmin.fxml"));
                 
            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
