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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MenuHostController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private GridPane grid;

    
    
     UserSession connectedUser=new UserSession();
    @FXML
    private ImageView imgUser;
    @FXML
    private Label NameUs;
    /**
     * Initializes the controller class.
     */
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           grid.getChildren().clear();
           NameUs.setText(connectedUser.getUser().getNom()+" "+connectedUser.getUser().getPrenom());
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("DahshbordHost.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);
                
            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void Dashboard(ActionEvent event) {
         grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("DahshbordHost.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterOffre(ActionEvent event) {
                 grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("AjouterOffre.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ListeOffre(ActionEvent event) {
                 grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("GererOffres.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ProfileHost(ActionEvent event) {
           
        grid.getChildren().clear();
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ProfileHost.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

   
    

