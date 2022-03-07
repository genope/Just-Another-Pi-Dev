/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class DetailPublicationController implements Initializable {
static Commentaire selectedItemm3;

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField adresse;
    @FXML
    private TextField region;
    
    @FXML
    private Button AjouterCommentbtn;
    @FXML
    private ListView<Commentaire> L_afficher;
    @FXML
    private Button supprimerbtn;
    @FXML
    private TextField Moynote;
    @FXML
    private VBox Pubdetail;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        CommentaireService cs = new CommentaireService();
        
        String file = selectedItem.getImage();
        Image image = new Image(file,241,94,false,true);
        img.setImage(image);
        nom.setText(selectedItem.getNom());
        description.setText(selectedItem.getDescription());
        adresse.setText(selectedItem.getAdresse());
        region.setText(pubService.GetRegion(selectedItem.getRegion_id()));
        Moynote.setText(String.valueOf(cs.GEtMoyRating(selectedItem.getId())));
        
        L_afficher.getItems().addAll(cs.afficherCommentairee(selectedItem.getId()));
        
    }    

//    private void AjouterCommentaire(ActionEvent event) {
//          Publication pub = new Publication();
//        PublicationService pubService = new PublicationService();
//        Commentaire c= new Commentaire();
//        CommentaireService cs = new CommentaireService();
//        c.setNom(nomc.getText());       
//        c.setComment(comment.getText());
//        c.setDate_com(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
//        c.setId_publication(Integer.parseInt(id.getText()));
//        c.setId_guest(Integer.parseInt(id_guest.getText()));
//        
// 
//        cs.ajouterCommentaire(c);
//    }

    @FXML
    private void AjouterCommentairee(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterCommentaire.fxml"));
            Scene scene =new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
           
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) {
        Commentaire c = new Commentaire();
        CommentaireService cs = new CommentaireService();
        
        
    
      //  selectedItemm=L_afficher.getSelectionModel().getSelectedItem();
        
        
        cs.supprimerCommentaire(L_afficher.getSelectionModel().getSelectedItem());
        int selecteditemm3 = L_afficher.getSelectionModel().getSelectedIndex();
        L_afficher.getItems().remove(selecteditemm3);
        
       
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire supprimé!");
                alert.showAndWait();
    }

    @FXML
    private void indexx(MouseEvent event) {
        selectedItemm3 = L_afficher.getSelectionModel().getSelectedItem();
    }
    
}
