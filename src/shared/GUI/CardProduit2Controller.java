/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import shared.entities.PanierDetails;
import shared.entities.Produit;
import shared.services.PanierDetailsService;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class CardProduit2Controller implements Initializable {

    @FXML
    private ImageView ivProd;
    @FXML
    private Label ref;
    @FXML
    private Label tfprix;
    @FXML
    private Label tfquantite;
    private Produit produit;
    private PanierDetails panierdetails;
    MyListenerProd mylistener;
    @FXML
    private Button removebtn;
    private Suppression sup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void AddProduit(Produit produit,MyListenerProd mylistener, Suppression s) throws SQLException, IOException{
        PanierDetailsService pandet = new PanierDetailsService();
        produit.getImage().getBinaryStream();
        this.produit=produit;
        this.mylistener = mylistener;
        this.sup = s;
        ref.setText(produit.getRef_prod());
        tfprix.setText(String.valueOf(produit.getPrix()));
        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        tfquantite.setText(String.valueOf(pandet.getPanDetByrefPr(String.valueOf(produit.getRef_prod())).getQuantite()));
        //tfquantite.setText(produit.);

//        labelNom.setText(produit.getDesignation());
//        labelDescription.setText(produit.getDescription());
//        LabelPrix.setText(String.valueOf(produit.getPrix()));
//        labelregion.setText(produit.getNomCategorie());
//        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
//
//
//
    }

    public void AddProddd(Produit produit, MyListenerProd mylistener, PanierDetails panierdetails,Suppression s) throws SQLException, IOException{
        this.produit = produit;
        this.mylistener = mylistener;
        this.panierdetails= panierdetails;
        this.sup = s;
        String qte = String.valueOf(panierdetails.getQuantite());
        ref.setText(produit.getRef_prod());
        tfprix.setText(String.valueOf(produit.getPrix()));
        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        tfquantite.setText(qte);
    }

    @FXML
    private void SupPanDet(ActionEvent event) {
        sup.supprimer(produit);
    }

}
