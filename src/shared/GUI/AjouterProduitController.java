/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.ToDoubleBiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import shared.entities.Produit;
import shared.services.ProduitService;
import shared.connexion.MaConnexion;
import shared.entities.CategorieProduit;
import shared.services.CategorieServices;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class AjouterProduitController implements Initializable {
//    ObservableList<String> region = FXCollections.observableArrayList("ARIANA","SOUSSE","TUNIS","NABEUL","MONASTIR","BIZERTE","BEN AROUS","BEJA","BEJA","GABES","GAFSA","JENDOUBA","KAIROUAN","KASSERINE","KEBILI","KEF","MAHDIA","MANOUBA","MANOUBA","MEDNINE","SFAX","SIDI BOUZID","SILIANA","TATAOUINE","TOZEUR","ZAGHOUAN");

    Scene fxmlFile;
    Parent root;
    Stage window;
    Connection mc;
    PreparedStatement ste;
    File file;
    File fileOut;

    private static Stage pStage;
    @FXML
    private GridPane grids;

    public AjouterProduitController() {
        mc = MaConnexion.getInstance().getCnx();
    }

    @FXML
    private TextField GPref_prod;
    @FXML
    private TextField GPdesignation;
    private ComboBox<String> GPcategorie;
    @FXML
    private TextField GPprix;
    @FXML
    private TextField GPquantite;
    @FXML
    private TextArea GPdescription;
    @FXML
    private ComboBox<String> GPregion;
    @FXML
    private Button GPbtnajout;
    @FXML
    private Button GPbtnModif;
    @FXML
    private Button GPbtnSuppr;
    @FXML
    private Button GPimagesss;
    @FXML
    private Button modifCatbtn;
    @FXML
    private ComboBox<String> GPcombocategorie;
    @FXML
    private ImageView ivProduit;
    private InputStream input;
    Image image;
    private List<Produit> Prodss = new ArrayList<>();
    private MyListener myListener;
    String oldRef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GPregion.getItems().add("ARIANA");
        GPregion.getItems().add("SOUSSE");
        GPregion.getItems().add("TUNIS");
        GPregion.getItems().add("NABEUL");
        GPregion.getItems().add("MONASTIR");
        GPregion.getItems().add("BIZERTE");
        GPregion.getItems().add("BEN AROUS");
        GPregion.getItems().add("BEJA");
        GPregion.getItems().add("GABES");
        GPregion.getItems().add("GAFSA");
        GPregion.getItems().add("JENDOUBA");
        GPregion.getItems().add("KAIROUAN");
        GPregion.getItems().add("KASSERINE");
        GPregion.getItems().add("KEBILI");
        GPregion.getItems().add("KEF");
        GPregion.getItems().add("MAHDIA");
        GPregion.getItems().add("MANOUBA");
        GPregion.getItems().add("MEDNINE");
        GPregion.getItems().add("SFAX");
        GPregion.getItems().add("SIDI BOUZID");
        GPregion.getItems().add("SILIANA");
        GPregion.getItems().add("TATAOUINE");
        GPregion.getItems().add("TOZEUR");
        GPregion.getItems().add("ZAGHOUAN");

        Connection mc = MaConnexion.getInstance().getCnx();
        try {
            try {
                showCardProds();
            } catch (IOException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //addListenerProduit();
        populateCategorie();

    }

    public void setPrimaryStage(Stage pStage) {
        AjouterProduitController.pStage = pStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    
    public void showCardProds() throws SQLException, IOException{
        grids.getChildren().clear();
        ProduitService prods = new ProduitService();
        if (prods.getAllProdL().size() > 0) {
            //setChosenProd(prods.getAllProdL().get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    try {
                        setChosenProd(produit);
                        System.out.println("test");
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            };
        }
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < prods.getAllProdL().size(); i++) {
                System.out.println(prods.getAllProd().get(i));
                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("CardProduit3.fxml"));
                AnchorPane anchorPane = cards.load();
                CardProduit3Controller ProduitServ = cards.getController();
                ProduitServ.AddProduit(prods.getAllProdL().get(i), myListener);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grids.add(anchorPane, column++, row);
                grids.setMinWidth(Region.USE_COMPUTED_SIZE);
                grids.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grids.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grids.setMinHeight(Region.USE_COMPUTED_SIZE);
                grids.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grids.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    private ObservableList<Produit> getProduitList() {
        ObservableList<Produit> ProduitListe = FXCollections.observableArrayList();
        ProduitService prods = new ProduitService();
        ProduitListe = prods.getAllProd();

        return ProduitListe;
    }
    //String Image = "";

    @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException, IOException {
        if(validtext(GPref_prod) & (validnumber(GPprix)) & (validnumber(GPquantite)) & validtextSpace(GPdesignation)){
        String ref_prod = GPref_prod.getText();
        String Designation = GPdesignation.getText();
        String Description = GPdescription.getText();
        Double Prix = Double.parseDouble(GPprix.getText());
        Integer Qte_stock = Integer.parseInt(GPquantite.getText());
        String nomCategorie = GPcombocategorie.getValue().toString();
        String region = getRegion();
        Window owner = (Stage) GPref_prod.getScene().getWindow();
        if (ref_prod.isEmpty() || Designation.isEmpty() || Description.isEmpty() || (Prix == null)
                || (Qte_stock == null) || nomCategorie.isEmpty() || region.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Remplisser les champs correctement !", "Form Erreur !");
        } else {
            System.out.println(file.toString());
            if (file == null) {
                showAlert(Alert.AlertType.ERROR, owner, "Il faut choisir une image", "Erreur Image");
            } else {
                try {
                    FileInputStream fin = new FileInputStream(file);

                    int len = (int) file.length();
                    String sql = "insert into `Produit` (ref_prod, designation, description, prix, image, qte_stock, nomCategorie, region) Values(?,?,?,?,?,?,?,?)";
                    ste = mc.prepareStatement(sql);
                    ste.setString(1, ref_prod);
                    ste.setString(2, Designation);
                    ste.setString(3, Description);
                    ste.setDouble(4, Prix);
                    ste.setBlob(5, fin);
                    ste.setInt(6, Qte_stock);
                    ste.setString(7, nomCategorie);
                    ste.setString(8, region);
                    int res = ste.executeUpdate();
                    if (res > 0) {
                        showAlert(Alert.AlertType.INFORMATION, owner, "Produit Ajouté", "Succès");
                        System.out.println(ste);
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("3");
                    System.out.println(ex.getMessage());
                }

            }
        }
        showCardProds();
        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String message, String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(owner);
        alert.showAndWait();
    }

    @FXML
    private void modifProduit(MouseEvent event) throws SQLException, IOException {
        Window owner = (Stage) GPref_prod.getScene().getWindow();
        String ref_prod = GPref_prod.getText();
        String designation = GPdesignation.getText();
        String description = GPdescription.getText();
        Double prix = Double.parseDouble(GPprix.getText());
        String nomCategorie = GPcombocategorie.getValue();
        Integer quantite = Integer.parseInt(GPquantite.getText());
        String region = GPregion.getValue();
        //String oldRef_prod = GPtable.getSelectionModel().getSelectedItem().ref_prod;
        try {
            FileInputStream fin = new FileInputStream(file);

                String sql = "Update Produit set  designation =?, description=?, prix=?, image=?, qte_stock=?, nomCategorie=?, region=?  where ref_prod= ?";
                    ste = mc.prepareStatement(sql);
                    ste.setString(8, ref_prod);
                    ste.setString(1, designation);
                    ste.setString(2, description);
                    ste.setDouble(3, prix);
                    ste.setBlob(4, fin);
                    ste.setInt(5, quantite);
                    ste.setString(6, nomCategorie);
                    ste.setString(7, region);
                    int res = ste.executeUpdate();
                    if (res > 0) {
                        showAlert(Alert.AlertType.INFORMATION, owner, "Produit Modifié", "Succès");
                    }
        } catch (FileNotFoundException ex) {
            System.out.println("Image not found");
        }

        showCardProds();
    }

    @FXML
    private void SupprProd(MouseEvent event) throws SQLException, IOException {
        String ref = GPref_prod.getText();
        ProduitService prods = new ProduitService();
        prods.supprimerProduit(ref);
//
        showCardProds();
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
    private void setChosenProd(Produit produit) throws SQLException, IOException {
        GPdesignation.setText(produit.getDesignation());
        ivProduit.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        GPdescription.setText(produit.getDescription());
        GPregion.setValue(produit.getRegion());
        GPprix.setText(String.valueOf(produit.getPrix()));
        GPref_prod.setText(produit.getRef_prod());

    }

    @FXML
    private String getRegion() {
        switch (GPregion.selectionModelProperty().get().getSelectedItem()) {
            case "ARIANA":
                return "ARIANA";

            case "BEJA":
                return "BEJA";

            case "BEN AROUS":
                return "BEN AROUS";

            case "GABES":
                return "GABES";

            case "GAFSA":
                return "GAFSA";

            case "JENDOUBA":
                return "JENDOUBA";

            case "KAIROUAN":
                return "KAIROUAN";

            case "KASSERINE":
                return "KASSERINE";

            case "MANOUBA":
                return "MANOUBA";

            case "KEF":
                return "KEF";

            case "MAHDIA":
                return "MAHDIA";

            case "MEDNINE":
                return "MEDNINE";

            case "SFAX":
                return "SFAX";

            case "MONASTIR":
                return "MONASTIR";

            case "NABEUL":
                return "NABEUL";

            case "SIDI BOUZID":
                return "SIDI BOUZID";

            case "ZAGHOUAN":
                return "ZAGHOUAN";

            case "SOUSSE":
                return "SOUSSE";

            case "SILIANA":
                return "SILIANA";

            case "TOZEUR":
                return "TOZEUR";

            case "TATAOUINE":
                return "TATAOUINE";

            case "BIZERTE":
                return "BIZERTE";

            case "TUNIS":
                return "TUNIS";
        }
        return "KEBILI";
    }

    @FXML
    private void modifCategorie(MouseEvent event) {
        try {
            openModalwindow("ModifierCategorie.fxml", "Gerer Categorie");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void populateCategorie() {
        CategorieServices cats = new CategorieServices();
        ObservableList list = cats.getAllCategorieProduitName();

        GPcombocategorie.setItems(null);
        GPcombocategorie.setItems(list);

    }
    @FXML
    private void imageBrowse(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("JPEG files(*.jpeg)", "*.jpeg");
            FileChooser.ExtensionFilter ext3 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.png");
            fc.getExtensionFilters().addAll(ext1, ext2, ext3);
            file = fc.showOpenDialog(AjouterProduitController.getPrimaryStage());
            BufferedImage bf = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bf, null);
            ivProduit.setImage(image);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean validtext(TextField tf){
        Pattern p = Pattern.compile("[a-zA-Z0-9 ]+");
        Matcher m = p.matcher(tf.getText());
        if ((m.find() && m.group().equals(tf.getText()))) {
            tf.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tf).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tf.setEffect(in);
            return false;
        }
    }
    private boolean validtextSpace(TextField tf){
        Pattern p = Pattern.compile("[a-zA-Z0-9 ]+");
        Matcher m = p.matcher(tf.getText());
        if ((m.find() && m.group().equals(tf.getText()))) {
            tf.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tf).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tf.setEffect(in);
            return false;
        }
    }
    private boolean validtextArea(TextArea tf){
        Pattern p = Pattern.compile("[a-zA-Z0-9]+%s");
        Matcher m = p.matcher(tf.getText());
        if ((m.find() && m.group().equals(tf.getText()))) {
            tf.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tf).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tf.setEffect(in);
            return false;
        }
    }
    
    private boolean validnumber(TextField tf){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tf.getText());
        if ((m.find() && m.group().equals(tf.getText()))) {
            tf.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tf).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tf.setEffect(in);
            return false;
        }
    }
}
