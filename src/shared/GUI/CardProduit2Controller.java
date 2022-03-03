/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import shared.entities.Produit;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class CardProduit2Controller implements Initializable {

    @FXML
    private ImageView ivProd;
    @FXML
    private TextField ref;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfquantite;
    private Produit produit;
    MyListener mylistener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     
    public void AddProduit(Produit produit,MyListener mylistener) throws SQLException, IOException{
        produit.getImage().getBinaryStream();
        this.produit=produit;
        this.mylistener = mylistener;
        ref.setText(produit.getRef_prod());
        tfprix.setText(String.valueOf(produit.getPrix()));
        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        
        
//        labelNom.setText(produit.getDesignation());
//        labelDescription.setText(produit.getDescription());
//        LabelPrix.setText(String.valueOf(produit.getPrix()));
//        labelregion.setText(produit.getNomCategorie());
//        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
//        
//
//        
    }
    
}
