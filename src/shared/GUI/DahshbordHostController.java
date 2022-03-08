/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.entities.enums.TypeOffres;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DahshbordHostController implements Initializable {

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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label TotalOffres;
    @FXML
    private Label TotalLogs;
    @FXML
    private Label MoysTra;
    @FXML
    private Label Hore;
    @FXML
    private VBox pnItems;

      int a=0;
        int b=0;
        int c=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OffreServices offreService=new OffreServices();
        TotalOffres.setText(String.valueOf(offreService.getNbrMyOffres(110405018)));

        
        TotalLogs.setText(String.valueOf(offreService.getNbrLogemts(110405018)));
     MoysTra.setText(String.valueOf(offreService.getNbrMoyTransports(110405018)));
     Hore.setText(String.valueOf(offreService.getNbrMyHoreca(110405018)));
     
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
