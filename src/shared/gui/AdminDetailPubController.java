/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import shared.entities.Commentaire;
import shared.entities.Publication;
import static shared.gui.AdminPublicationController.selectedItem5;
import static shared.gui.AffichagePublicationController.selectedItem;
import static shared.gui.AjoutPublicationController.selectedItemm;
import static shared.gui.DetailPublicationController.selectedItemm3;
import shared.services.CommentaireService;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AdminDetailPubController implements Initializable {
static Commentaire selectedItem4;
    @FXML
    private VBox Pubdetail;
 
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private ListView<Commentaire> L_afficher;
    @FXML
    private TextField adresse;
    @FXML
    private TextField region;
    @FXML
    private TextField Moynote;
    @FXML
    private Button supprimerbtn;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        CommentaireService cs = new CommentaireService();
        
        String file = selectedItem5.getImage();
        Image image = new Image(file,241,94,false,true);
        img.setImage(image);
        nom.setText(selectedItem5.getNom());
        description.setText(selectedItem5.getDescription());
        adresse.setText(selectedItem5.getAdresse());
        region.setText(pubService.GetRegion(selectedItem5.getRegion_id()));
        Moynote.setText(String.valueOf(cs.GEtMoyRating(selectedItem5.getId())));
        
        L_afficher.getItems().addAll(cs.afficherCommentairee(selectedItem5.getId()));
    }    

    @FXML
    private void indexx(MouseEvent event) {
       selectedItem4 = L_afficher.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) {
        Commentaire c = new Commentaire();
        CommentaireService cs = new CommentaireService();
        
        
    
       selectedItem4=L_afficher.getSelectionModel().getSelectedItem();
        
        
        cs.supprimerCommentaire(L_afficher.getSelectionModel().getSelectedItem());
        int selecteditemm3 = L_afficher.getSelectionModel().getSelectedIndex();
        L_afficher.getItems().remove(selecteditemm3);
        
       
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire supprimé!");
                alert.showAndWait();
    }
    
}
