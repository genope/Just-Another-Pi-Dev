/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import shared.entities.Panier;
import shared.entities.PanierDetails;
import shared.entities.Produit;
import shared.services.CategorieServices;
import shared.services.PanierDetailsService;
import shared.services.PanierService;
import shared.services.ProduitService;
import shared.services.UserSession;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class AffichageProduitController implements Initializable {

    private MyListener2 myListener;
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
    private Label tfdescr;
    @FXML
    private ImageView ivpro;

    private List<Produit> Prodss = new ArrayList<>();
    private final ObservableList<Produit> dataList = FXCollections.observableArrayList();
    @FXML
    private Button btnAddtoPan;
    @FXML
    private TextField reff;
    @FXML
    private Label btnPanier;
    Scene fxmlFile;
    Parent root;
    Stage window;
    Connection mc;
    PreparedStatement ste;
    private static Stage pStage;
    @FXML
    private TextField quantite;
    @FXML
    private ImageView Panierr;
    @FXML
    private Pane btnPann;
    @FXML
    private Label PanSize;
   UserSession connectedUser=new UserSession();
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
        showTaillePanier();
        
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
        //ivpro.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        tfdescr.setText(produit.getDescription());
        LabelV.setText(produit.getregion1());
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

    private void ConfirmerCateg2() throws SQLException, IOException {
        //grid.getChildren().clear(); //Effacer la table des cartes 
        ProduitService prods = new ProduitService();

        if (prods.getAllProdL().size() > 0) {
            //setChosenOffre(prods.getAllProdL().get(0));

            setChosenOffre(prods.getAllProdL().get(0));
            myListener = new MyListener2() {
                @Override
                public void onClickListener(Produit produit) {
                     try {
                        setChosenOffre(produit);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
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
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterAuPanier(ActionEvent event) throws SQLException {
        //Default idCommande = 3 ;
        Window owner = (Stage) reff.getScene().getWindow();

        ProduitService prods = new ProduitService();
        PanierDetailsService pann = new PanierDetailsService();
        PanierService panier =new PanierService();
        System.out.println(connectedUser.getUser().getCin());
                
        Panier paax=new Panier(connectedUser.getUser().getCin(),connectedUser.getUser().getCin(),0d);
        panier.ajouterPanier(paax);
        String ref = reff.getText();
        int qte = Integer.parseInt(quantite.getText());
        Double prixx = Double.parseDouble(LabelP.getText()) ;
        //System.out.println(ref);
        int p = 0;
        
        for (int i = 0; i < pann.ListPanierDetailsUser(connectedUser.getUser().getCin()).size(); i++) {
            if (pann.ListPanierDetailsUser(connectedUser.getUser().getCin()).get(i).getId_prod().equals(ref)) {
                p++;
            }
        }
        if (p == 0) {
            PanierDetails pandet = new PanierDetails(ref, connectedUser.getUser().getCin(), qte, prixx);
            PanierDetailsService pandelserv = new PanierDetailsService();
            pandelserv.ajouterPanierDetails(pandet);
                   
            System.out.println("ajouté");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conflit Panier");
            alert.setHeaderText(null);
            alert.setContentText("Le produit est deja dans le panier voulez vous ajouter " + qte + " sa quantité");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //Modifier la quantité dans le panierdetails
                int oldQte = pann.getPanDetByrefPr(ref).getQuantite();
                System.out.println(pann.getPanDetByrefPr(ref).getQuantite() + " 9dim");
                System.out.println(qte + " jdid");
                int newQte = oldQte + qte;
                System.out.println(newQte);
                
                PanierDetails p1 = new PanierDetails(connectedUser.getUser().getCin(), ref, newQte);
                
                System.out.println(p1);
                pann.modifierPanierDetailsss(p1);
                System.out.println("OKK");
            }
        }
        showTaillePanier();
    }


    //     public void TestStream(){
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
    
//    public boolean Valid

    @FXML
    private void AllerPanier2(MouseEvent event) {
        try {
            openModalwindow("PanierAffichage.fxml", "Gerer Categorie");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void showTaillePanier(){
        ProduitService prods = new ProduitService();
        PanSize.setText(String.valueOf(prods.getProdJoin(connectedUser.getUser().getCin()).size()));
        populateCategorie();
    }
    @FXML
    private void AllerPanier(MouseEvent event) {
    }
}
