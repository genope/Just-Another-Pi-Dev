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
import shared.API.SendMsg;
import shared.entities.Offres;
import shared.services.OffreServices;
import shared.services.UserService;
import shared.services.UserSession;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import static shared.gui.ForgetPasswordController.ACCOUNT_SID;
import static shared.gui.ForgetPasswordController.AUTH_TOKEN;

 
/**
 * FXML Controller class
 *
 * @author user
 */

public class DashboardAdminController implements Initializable {

  
    @FXML
    private GridPane grid;
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
    UserSession userConnected=new UserSession();
    UserService userS=new UserService();
      public static final String ACCOUNT_SID =System.getenv("TWILIO_ACCOUNT_SID");
              ;
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    
    
    
      private void Appro(Offres offre) {
         
 
//            Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
//      
//            String msg="Hello Mr/Mrs "+userS.GetUserByCin(offre.getIdhost()).getNom()+" "+"you offer wich you posted recentely has been approuved and posted in our application ";
//            System.out.println(userS.GetUserByCin(offre.getIdhost()).getNom());
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216"+String.valueOf(userS.GetUserByCin(offre.getIdhost()).getNumber())),
//                    new PhoneNumber("+19108308627"), msg ).create();
//            
//            
            
            
            
            OffreServices offree = new OffreServices();
            offree.ApprouverOf(offre.getId());
            
            String a=userS.GetUserByCin(offre.getIdhost()).getEmail();
            
            // SendMsg mail=new SendMsg();
      
        
  
                
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
         
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
