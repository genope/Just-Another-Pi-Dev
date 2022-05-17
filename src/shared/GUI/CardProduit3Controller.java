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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;
import shared.entities.Produit;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class CardProduit3Controller implements Initializable {
    MyListener2 mylistener;
    private Produit produit;
    @FXML
    private Label ref_prodLab;
    @FXML
    private Label designationLab;
    @FXML
    private Label descriptionLab;
    @FXML
    private Label quantiteLab;
    @FXML
    private Label CategorieLab;
    @FXML
    private Label regionLab;
    @FXML
    private Button prixLab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void AddProduit(Produit produit,MyListener2 mylistener) throws SQLException, IOException{
        this.produit=produit;
        Image imgee = new Image(new FileInputStream("C:\\xampp\\htdocs\\img\\"+produit.getImage()));

        this.mylistener = mylistener;
        ref_prodLab.setText(produit.getRef_prod());
        descriptionLab.setText(produit.getDescription());
        designationLab.setText(produit.getDesignation());
        prixLab.setText(String.valueOf(produit.getPrix()));
        CategorieLab.setText(produit.getNomCategorie());
        quantiteLab.setText(String.valueOf(produit.getQte_stock()));
        regionLab.setText(produit.getregion1());
       
        
    }

    @FXML
    private void click(MouseEvent event) {
        mylistener.onClickListener(produit);
    }
    
    
}
