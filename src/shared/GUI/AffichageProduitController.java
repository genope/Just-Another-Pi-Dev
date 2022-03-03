/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import shared.entities.CategorieProduit;
import shared.entities.PanierDetails;
import shared.entities.Produit;
import shared.services.CategorieServices;
import shared.services.PanierDetailsService;
import shared.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class AffichageProduitController implements Initializable {

    private MyListener myListener;
    @FXML
    private VBox chosenOffreCard;
    private Label LabelDateD;
    private Label LabelDateF;
    @FXML
    private Label LabelV;
    @FXML
    private Label LabelP;
    @FXML
    private ComboBox<String> choisirCateg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label nomProduit;
    @FXML
    private TextArea tfdescr;
    @FXML
    private ImageView ivpro;

    private List<Produit> Prodss = new ArrayList<>();
    @FXML
    private TextField LowPrix;
    @FXML
    private TextField Highprix;
    private final ObservableList<Produit> dataList = FXCollections.observableArrayList();
    @FXML
    private Button btnAddtoPan;
    @FXML
    private TextField reff;
    @FXML
    private Button btnPanier;
    Scene fxmlFile;
    Parent root;
    Stage window;
    Connection mc;
    PreparedStatement ste;
    private static Stage pStage;
    @FXML
    private TextField quantite;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                ConfirmerCateg2();
            } catch (IOException ex) {
                Logger.getLogger(AffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        populateCategorie();
        //Test();

    }

    private void populateCategorie() {
        CategorieServices cats = new CategorieServices();
        ObservableList list = cats.getAllCategorieProduitName();

        choisirCateg.setItems(null);
        choisirCateg.setItems(list);

    }

    private void setChosenOffre(Produit produit) throws SQLException, IOException {
        nomProduit.setText(produit.getDesignation());
        ivpro.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        tfdescr.setText(produit.getDescription());
        LabelV.setText(produit.getRegion());
        LabelP.setText(String.valueOf(produit.getPrix()));
        reff.setText(produit.getRef_prod());

    }
    private void openModalwindow(String resource, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.setTitle(title);
        setPrimaryStage(window);
        window.showAndWait();

    }

    public void setPrimaryStage(Stage pStage) {
        AffichageProduitController.pStage = pStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }
    @FXML
    private void ConfirmerCateg2() throws SQLException, IOException {
        //grid.getChildren().clear();
        ProduitService prods = new ProduitService();

        if (prods.getAllProdL().size() > 0) {
            //setChosenOffre(prods.getAllProdL().get(0));
            
                setChosenOffre(prods.getAllProdL().get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Produit produit) {
                        try {
                            setChosenOffre(produit);
                        } catch (SQLException ex) {
                            Logger.getLogger(AffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(AffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                };
            
        }

        int column = 1;
        int row = 0;

        try {

            for (int i = 0; i < prods.getAllProdL().size(); i++) {

                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("CardProduit.fxml"));
                AnchorPane anchorPane = cards.load();
                CardProduitController ProduitServ = cards.getController();
                ProduitServ.AddProduit(prods.getAllProdL().get(i), myListener);

                if (column == 3) {
                    column = 1;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }

////                  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
//     public void Test(){
//        ProduitService pro = new ProduitService();
//        List<Produit> list = pro.getAllProdL();
//         List<String> nomEmployes = 
//      list.stream().sorted((a,b) -> (int)a.getPrix() -(int) b.getPrix());
////              .forEach(e -> System.out.println(e));
//
//    for (String nom : nomEmployes) {
//      System.out.println(nom);
//    } 
//    }
                  

    @FXML
    private void AjouterAuPanier(ActionEvent event) {
        //Default CIN = 11 ;
        ProduitService prods = new ProduitService();
        String ref = reff.getText();
        int qte = Integer.parseInt(quantite.getText());
        Double prixx = Double.parseDouble(quantite.getText());
        //System.out.println(ref);
        PanierDetails pandet= new PanierDetails(ref, 3, qte, prixx);
        PanierDetailsService pandelserv = new PanierDetailsService();
        pandelserv.ajouterPanierDetails(pandet);
        
        
        
    }

    @FXML
    private void AllerPanier(ActionEvent event) {
        try {
            openModalwindow("PanierAffichage.fxml", "Gerer Categorie");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    }

