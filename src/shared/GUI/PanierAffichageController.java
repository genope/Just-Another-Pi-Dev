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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javax.imageio.ImageIO;
import shared.entities.PanierDetails;
import shared.entities.Produit;
import shared.services.PanierDetailsService;
import shared.services.PanierService;
import shared.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class PanierAffichageController implements Initializable {

    @FXML
    private ImageView ivProd;
    private TextField tfRef;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    @FXML
    private Label totall;
    private Suppression sup;

    /**
     * Initializes the controller class.
     */
    public void Suppri(Produit pandet){
        PanierDetailsService pand = new PanierDetailsService();
        pand.supprimerPanierDetailsbyRefProd(pandet.ref_prod);
        try {
            AfficherProdHori();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
        try {
            AfficherProdHori();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PanierAffichageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanierAffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        totall.setText(String.valueOf(AffTotal()));
    }

    private void setChosenOffre(Produit produit) throws SQLException, IOException {
        ivProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        tfRef.setText(produit.getRef_prod());
        //total.setText("totall");

    }
    public double AffTotal(){
        double total2 = 0;
        PanierDetailsService prods = new PanierDetailsService();
        for (int i=0; i < prods.ListPanierDetailsUser(11).size(); i++){
            double total1 = prods.ListPanierDetailsUser(11).get(i).getPrix() ;
            System.out.println(prods.ListPanierDetailsUser(11).get(i));
            System.out.println(prods.ListPanierDetailsUser(11).get(i).getPrix());
            System.out.println(total1);
            total2 += total1;
        }
        return total2;
    }

    private void AfficherProdHori() throws SQLException, IOException {
        grid.getChildren().clear();
        ProduitService prods = new ProduitService();

        PanierService panserv = new PanierService();
        PanierDetailsService pandets = new PanierDetailsService();
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < prods.getProdJoin().size(); i++) {
                sup = new Suppression() {
                    @Override
                    public void supprimer(Produit pandet) {
                        Suppri(pandet);
                    }
                };
                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("CardProduit2.fxml"));
                AnchorPane anchorPane = cards.load();
                prods.getProdJoin().get(i).getPrix();
                CardProduit2Controller ProduitServ = cards.getController();
                ProduitServ.AddProduit(prods.getProdJoin().get(i), myListener, sup);

                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column, row++); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(0));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
