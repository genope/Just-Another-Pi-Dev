/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import shared.entities.Publication;
import static shared.gui.AffichagePublicationController.selectedItem;
import shared.services.CommentaireService;
import shared.services.MyListener;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class PubController  {

    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label description;
    @FXML
    private Label region;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label id_guest;
    private Publication pub;
    private MyListener myListener;
    @FXML
    private ImageView imagepub;
    @FXML
    private ImageView one;
    @FXML
    private VBox cardvbox;
    @FXML
    private TextField comment;
    @FXML
    private Rating Rating;
    @FXML
    private Button AjouterCommentairebtn;
    @FXML
    private Label nomc;
    @FXML
    private ImageView two;
    @FXML
    private ImageView three;
    @FXML
    private ImageView four;
    @FXML
    private ImageView five;
    @FXML
    private ImageView zero;
    @FXML
    private GridPane gridd;
    
    
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(pub);
    }
    

    /**
     * Initializes the controller class.
     */
   public void setData (Publication p , MyListener myListener) 
   {
       CommentaireService cs = new CommentaireService();
       PublicationService pubService = new PublicationService();
       this.myListener=myListener ;
       this.pub=p;
       nom.setText(p.getNom());
       adresse.setText(p.getAdresse());
       description.setText(p.getDescription());
       region.setText(pubService.GetRegion(p.getRegion_id()));
       date.setText(String.valueOf(p.getDatecreation()));
       id_guest.setText(String.valueOf(p.getId_guest()));
       Image image = new Image(p.getImage(),306,115,false,true);
       imagepub.setImage(image);
       id.setText(String.valueOf(p.getId()));
       if (cs.GEtMoyRating(p.getId())==1){
           one.setVisible(true);
       }
       else if (cs.GEtMoyRating(p.getId())==2){
           two.setVisible(true);
       }
      else if (cs.GEtMoyRating(p.getId())==3){
           three.setVisible(true);
       }
      else if (cs.GEtMoyRating(p.getId())==4){
           four.setVisible(true);
       }
      else if (cs.GEtMoyRating(p.getId())==5){
           five.setVisible(true);
       }
      else {
           zero.setVisible(true);
       }
       int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < cs.afficherCommentairee(p.getId()).size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("comment.fxml"));
                AnchorPane cardcomment = fxmlLoader.load();

               CommentController RecController = fxmlLoader.getController();
                RecController.setData1(cs.afficherCommentairee(p.getId()).get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridd.add(cardcomment, column++, row); //(child,column,row)
                //set grid width
                gridd.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridd.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridd.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridd.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridd.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridd.setMaxHeight(Region.USE_PREF_SIZE);

                gridd.setMargin(cardvbox, new Insets(10));
                
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
       
       
       
       

       
       
   }

    
}
