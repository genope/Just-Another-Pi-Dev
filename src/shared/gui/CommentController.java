/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import shared.entities.Commentaire;
import shared.entities.Publication;
import shared.services.CommentaireService;
import shared.services.MyListener;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class CommentController  {

    @FXML
    private Label nom_guest;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private ImageView Three;
    @FXML
    private ImageView five;
    @FXML
    private ImageView one;
    @FXML
    private ImageView two;
    @FXML
    private AnchorPane cardcomment;
    @FXML
    private ImageView zero;
    @FXML
    private ImageView four;
    @FXML
    private Label note;
    private MyListener myListener;
    private Commentaire com;
    @FXML
    private Label id;
    @FXML
    private Label nomc;
    
@FXML
    private void click2(MouseEvent event) {
//        myListener.onClickListener2(com);
    }
    
    /**
     * Initializes the controller class.
     */
  
        public void setData1 (Commentaire c) 
   {
       CommentaireService cs = new CommentaireService();
       PublicationService pubService = new PublicationService();
       this.myListener=myListener ;
       this.com=c;
       nomc.setText(c.getNom());
       description.setText(c.getComment());
       nom_guest.setText(cs.GetNomuser(c.getId_guest()));
       date.setText(String.valueOf(c.getDate_com()));
       note.setText(String.valueOf(c.getNote()));
       id.setText(String.valueOf(c.getId()));
       if (c.getNote()==1){
           one.setVisible(true);
       }
       else if (c.getNote()==2){
           two.setVisible(true);
       }
      else if (c.getNote()==3){
           Three.setVisible(true);
       }
      else if (c.getNote()==4){
           four.setVisible(true);
       }
      else if (c.getNote()==5){
           five.setVisible(true);
       }
      else {
           zero.setVisible(true);
       }
       
    }    

    
}
