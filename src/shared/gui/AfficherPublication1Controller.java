/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shared.entities.Publication;
import shared.services.MyListener;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AfficherPublication1Controller implements Initializable {

    @FXML
    private GridPane grid;
    private MyListener myListener;
    @FXML
    private Button AjouterPubbtn;
    private void setChosenPub(Publication pub)
    {}  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PublicationService rs= new PublicationService();
        myListener = new MyListener() { 
            @Override
            public void onClickListener(Publication pub) {
                setChosenPub(pub);
            }
        };
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rs.afficherPublication().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("pub.fxml"));
                VBox cardvbox = fxmlLoader.load();

               PubController RecController = fxmlLoader.getController();
                RecController.setData(rs.afficherPublication().get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(cardvbox, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                grid.setMargin(cardvbox, new Insets(10));
                
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    
     

        // TODO
    }    

    @FXML
    private void AjouterPublication(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterModifierPub.fxml"));
            Scene scene =new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
           
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
