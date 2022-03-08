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
import javafx.scene.layout.Region;
import shared.entities.Offres;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardAdminController implements Initializable {

  
    @FXML
    private GridPane grid;
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

    
    private Offres offre;
    private ApprouveOffre approuve;
           
            
    
    
      private void Appro(Offres offre) {
        OffreServices offree = new OffreServices();
        offree.ApprouverOf(offre.getId());
                
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
         
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    public void afficher(){
         int column = 1;
        int row = 0;
        OffreServices offre=new OffreServices();
    for (int i = 0; i < offre.getAllFOffres().size(); i++) {
            
      
                   

            try {
                
              approuve = new ApprouveOffre() {
                  @Override
                  public void Approuve(Offres offre) {
                    Appro(offre);
                  }
                };
            
                FXMLLoader cardss = new FXMLLoader();
                cardss.setLocation(getClass().getResource("FalseOffres.fxml"));
                
              
                
                Pane anchorPane = cardss.load();
               
                
                FalseOffresController offreservice = cardss.getController();
                
                offreservice.setData(offre.getAllFOffres().get(i),approuve);
                
                //       offreservice.setData(offre, supp);
                if (column == 2) {
                    column = 1;
                    row++;
                  
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));
                
////                  
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
       
    }
}
