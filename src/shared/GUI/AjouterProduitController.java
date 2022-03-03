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
import java.util.ResourceBundle;
import java.util.function.ToDoubleBiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TableView<Produit> GPtable;
    @FXML
    private TableColumn<Produit, String> GPcolRef_prod;
    @FXML
    private TableColumn<Produit, String> GPcolDesignation;
    @FXML
    private TableColumn<Produit, String> GPcolDescription;
    @FXML
    private TableColumn<Produit, Double> GPcolPrix;
    @FXML
    private TableColumn<Produit, Integer> GPcolquantite;
    @FXML
    private TableColumn<Produit, String> GPcolCategorie;
    @FXML
    private TableColumn<Produit, String> GPcolRegion;
    @FXML
    private Button modifCatbtn;
    @FXML
    private ComboBox<String> GPcombocategorie;
    @FXML
    private ImageView ivProduit;
    private InputStream input;
    Image image;
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
        showProdTable();
        addListenerProduit();
        populateCategorie();

    }

    public void setPrimaryStage(Stage pStage) {
        AjouterProduitController.pStage = pStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    public void showProdTable() {
        ObservableList<Produit> list = getProduitList();
        GPcolRef_prod.setCellValueFactory(new PropertyValueFactory<Produit, String>("ref_prod"));
        GPcolDesignation.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        GPcolDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        GPcolPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        GPcolquantite.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte_stock"));
        GPcolCategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("nomCategorie"));
        GPcolRegion.setCellValueFactory(new PropertyValueFactory<Produit, String>("region"));
        GPtable.setItems(list);
    }

    private ObservableList<Produit> getProduitList() {
        ObservableList<Produit> ProduitListe = FXCollections.observableArrayList();
        ProduitService prods = new ProduitService();
        ProduitListe = prods.getAllProd();

        return ProduitListe;
    }
    //String Image = "";

    @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException {
        String ref_prod = GPref_prod.getText();
        String Designation = GPdesignation.getText();
        String Description = GPdescription.getText();
        Double Prix = Double.parseDouble(GPprix.getText());
        //String Image = GPimage.getText();
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
                    System.out.println("2");
                    String sql = "insert into `Produit` (ref_prod, designation, description, prix, image, qte_stock, nomCategorie, region) Values(?,?,?,?,?,?,?,?)";
                    System.out.println("2.5");
                    ste = mc.prepareStatement(sql);
                    System.out.println("3");
                    ste.setString(1, ref_prod);
                    ste.setString(2, Designation);
                    ste.setString(3, Description);
                    ste.setDouble(4, Prix);
                    //ste.setString(5, Image);
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
//        Produit P = new Produit(ref_prod, Designation, Description, Prix, Image, Qte_stock, nomCategorie, region); 
//        ProduitService prodserv = new ProduitService();
//        prodserv.ajouterProduit(P);

        showProdTable();

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
    private void modifProduit(MouseEvent event) throws SQLException {
        Window owner = (Stage) GPref_prod.getScene().getWindow();
        String ref_prod = GPref_prod.getText();
        String designation = GPdesignation.getText();
        String description = GPdescription.getText();
        Double prix = Double.parseDouble(GPprix.getText());
        String nomCategorie = GPcombocategorie.getValue();
        Integer quantite = Integer.parseInt(GPquantite.getText());
        String region = GPregion.getValue();
        String oldRef_prod = GPtable.getSelectionModel().getSelectedItem().ref_prod;
        try {
            FileInputStream fin = new FileInputStream(file);

                String sql = "Update Produit set ref_prod=?, designation =?, description=?, prix=?, image=?, qte_stock=?, nomCategorie=?, region=?  where ref_prod= ?";
                    System.out.println("2.5");
                    ste = mc.prepareStatement(sql);
                    System.out.println("3");
                    ste.setString(1, ref_prod);
                    ste.setString(2, designation);
                    ste.setString(3, description);
                    ste.setDouble(4, prix);
                    //ste.setString(5, Image);
                    ste.setBlob(5, fin);
                    ste.setInt(6, quantite);
                    ste.setString(7, nomCategorie);
                    ste.setString(8, region);
                    int res = ste.executeUpdate();
                    if (res > 0) {
                        showAlert(Alert.AlertType.INFORMATION, owner, "Produit Ajouté", "Succès");
                        System.out.println(ste);
                    }
        } catch (FileNotFoundException ex) {
            System.out.println("Image not found");
        }
        //            Produit p = new Produit(ref_prod, designation, description, prix, fin, quantite, nomCategorie, region);
//            ProduitService prods = new ProduitService();
//            prods.modifierProduit(p, oldRef_prod);

//            System.out.println(p);

        

        showProdTable();
    }

    @FXML
    private void SupprProd(MouseEvent event) {
        String ref = GPtable.getSelectionModel().getSelectedItem().ref_prod;
        ProduitService prods = new ProduitService();
        prods.supprimerProduit(ref);

        showProdTable();
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

    private void addListenerProduit() {
        GPtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    GPbtnSuppr.setDisable(false);
                    GPbtnModif.setDisable(false);
                    GPref_prod.setText(newSelection.getRef_prod());
                    GPdesignation.setText(newSelection.getDesignation());
                    GPcombocategorie.setValue(newSelection.getNomCategorie());
                    GPprix.setText(String.valueOf(newSelection.getPrix()));
                    GPquantite.setText(String.valueOf(newSelection.getQte_stock()));
                    GPdescription.setText(newSelection.getDescription());
                    GPregion.setValue(newSelection.getRegion());
                    try {
                        ivProduit.setImage(SwingFXUtils.toFXImage(ImageIO.read(newSelection.getImage().getBinaryStream()), null));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                GPbtnSuppr.setDisable(true);
                GPbtnModif.setDisable(true);
                GPref_prod.setText("");
                GPref_prod.setText(newSelection.getRef_prod());
                GPdesignation.setText("");
                GPdesignation.setText(newSelection.getDesignation());
                GPcombocategorie.setValue("");
                GPcombocategorie.setValue(newSelection.getNomCategorie());
                GPprix.setText("");
                GPprix.setText(String.valueOf(newSelection.getPrix()));
                GPquantite.setText(String.valueOf(""));
                GPquantite.setText(String.valueOf(newSelection.getQte_stock()));
                GPdescription.setText("");
                GPdescription.setText(newSelection.getDescription());
                GPregion.setValue("");
                GPregion.setValue(newSelection.getRegion());
            }
        });
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
//            BufferedImage bImage = SwingFXUtils(produit.)
//            ByteArrayOutputStream s = new ByteArrayOutputStream();
//            ImageIO.write(image, "png", s);
//            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
