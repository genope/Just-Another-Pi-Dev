/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import shared.entities.Commentaire;
import shared.entities.Publication;
import static shared.gui.AffichagePublicationController.selectedItem;

import static shared.gui.AjoutPublicationController.selectedItemm;
import shared.services.CommentaireService;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AjouterCommentaireController implements Initializable {

    @FXML
    private TextField id_guest;
    
    @FXML
    private TextField comment;
    @FXML
    private TextField id;
    @FXML
    private Label note;
    @FXML
    private VBox Pubdetail;
    @FXML
    private TextField nom;
    @FXML
    private Button Ajouterbtncomment;
    @FXML
    private Rating Rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        id.setText(String.valueOf(selectedItem.getId()));
        Rating.ratingProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number>url,Number t,Number t1){
                note.setText(t1.toString());
            }
        }
        );
    }    

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        Commentaire c= new Commentaire();
        CommentaireService cs = new CommentaireService();
        c.setNom(nom.getText());       
        c.setComment(comment.getText());
        c.setDate_com(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        c.setId_publication(Integer.parseInt(id.getText()));
        c.setId_guest(Integer.parseInt(id_guest.getText()));
        c.setNote(Character.getNumericValue(note.getText().charAt(0)));
        
 
        cs.ajouterCommentaire(c);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire ajouté!");
                alert.showAndWait();
    }
    
}
