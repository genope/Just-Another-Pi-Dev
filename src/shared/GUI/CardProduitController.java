/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import shared.entities.Produit;



/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class CardProduitController implements Initializable {
    
    private Produit produit;
    @FXML
    private ImageView IvProd;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelregion;
    @FXML
    private Label LabelPrix;
    @FXML
    private Label labelNom;
    MyListener2 mylistener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    public void AddProduit(Produit produit,MyListener2 mylistener) throws SQLException, IOException{
        this.produit=produit;
        this.mylistener = mylistener;
        labelNom.setText(produit.getDesignation());
        labelDescription.setText(produit.getDescription());
        LabelPrix.setText(String.valueOf(produit.getPrix()));
        labelregion.setText(produit.getNomCategorie());
        Image imgee = new Image(new FileInputStream("C:\\xampp\\htdocs\\img\\"+produit.getImage()));

        IvProd.setImage(imgee);
               // SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null
                
        

        
    }


    @FXML
    private void click(MouseEvent event) {
        mylistener.onClickListener(produit);
    }
    
}
