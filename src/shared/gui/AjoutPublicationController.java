/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import shared.entities.Publication;

import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AjoutPublicationController implements Initializable {
    String path;
static Publication selectedItemm;

    @FXML
    private Button Modifierbtn;
    @FXML
    private Button Ajouterpubbtn;
    @FXML
    private TextField id;
    @FXML
    private TextField description;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<Object> region;
    @FXML
    private Button selectimg;
    @FXML
    private ImageView imageview;
    File selectedFile;
    @FXML
    private TableView<Publication> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PublicationService pubService = new PublicationService();
        TableColumn<Publication, Integer> idColumn = new TableColumn<>("ID Publication");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Publication, Integer> idgColumn = new TableColumn<>("ID Guest");
        idgColumn.setCellValueFactory(new PropertyValueFactory<>("id_guest"));
        idgColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Publication, String> idnColumn = new TableColumn<>("Nom");
        idnColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idnColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> iddColumn = new TableColumn<>("DESCRIPTION");
        iddColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        iddColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> idiColumn = new TableColumn<>("IMAGE");
        idiColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        idiColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> idaColumn = new TableColumn<>("ADRESSE");
        idaColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idaColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, Integer> idrColumn = new TableColumn<>("REGION ID");
        idrColumn.setCellValueFactory(new PropertyValueFactory<>("region_id"));
        idrColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, Date> iddcColumn = new TableColumn<>("DATE CREATION");
        iddcColumn.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
        iddcColumn.setStyle("-fx-alignment: CENTER;");
        ObservableList observableList = FXCollections.observableArrayList(pubService.afficherPublication());
        list.setItems(observableList);
        list.getColumns().addAll(idColumn, idgColumn,idnColumn,iddColumn,idiColumn, idaColumn, idrColumn, iddcColumn);
        Callback<TableColumn<Publication, String>, TableCell<Publication, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Publication, String>, TableCell<Publication, String>>() {
            String path;

            @Override
            public TableCell call(final TableColumn<Publication, String> param) {
                final TableCell<Publication, String> cell = new TableCell<Publication, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            System.out.println(item);
                            path = item;
                            System.out.println(path);
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
                            System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };
        idiColumn.setCellFactory(cellFactoryImage);
        String regions[] =
                   { "ARIANA", "SOUSSE", "TUNIS", "NABEUL","MONASTIR","BIZERTE","BEN AROUS","BEJA","GABES","GAFSA","JENDOUBA","KAIROUAN","KASSERINE","KEBILI","KEF","MAHDIA","MANOUBA","MEDNINE","SFAX","SIDI BOUZID","SILIANA","TATAOUINE","TOZEUR","ZAGHOUAN" };
        region.setItems(FXCollections.observableArrayList(regions));
    }    

    @FXML
    private void ModifierPublication(ActionEvent event) {
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        pub.setNom(nom.getText());       
        pub.setDescription(description.getText());
        pub.setAdresse(adresse.getText());
        pub.setImage(selectimg.getText()); 
        pubService.modifierPublication(pub,Integer.parseInt(id.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Publication modifiée!");
                alert.showAndWait();
                
         
    }

    @FXML
    private void AjouterPublication(ActionEvent event) {
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        pub.setNom(nom.getText());       
        pub.setDescription(description.getText());
        pub.setAdresse(adresse.getText());
        pub.setDatecreation(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
    
        pub.setImage(selectimg.getText());
        pub.setId_guest(1234567);
        pub.setRegion_id(pubService.GetItemId("region", "nom", region.getValue().toString()));
 
        pubService.ajouterPublication(pub);
        

        TableColumn<Publication, Integer> idColumn = new TableColumn<>("ID Publication");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Publication, Integer> idgColumn = new TableColumn<>("ID Guest");
        idgColumn.setCellValueFactory(new PropertyValueFactory<>("id_guest"));
        idgColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Publication, String> idnColumn = new TableColumn<>("Nom");
        idnColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idnColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> iddColumn = new TableColumn<>("DESCRIPTION");
        iddColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        iddColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> idiColumn = new TableColumn<>("IMAGE");
        idiColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        idiColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, String> idaColumn = new TableColumn<>("ADRESSE");
        idaColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idaColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, Integer> idrColumn = new TableColumn<>("REGION ID");
        idrColumn.setCellValueFactory(new PropertyValueFactory<>("region_id"));
        idrColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Publication, Date> iddcColumn = new TableColumn<>("DATE CREATION");
        iddcColumn.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
        iddcColumn.setStyle("-fx-alignment: CENTER;");
        ObservableList observableList = FXCollections.observableArrayList(pubService.afficherPublication());
        list.setItems(observableList);
        list.getColumns().addAll(idColumn, idgColumn,idnColumn,iddColumn,idiColumn, idaColumn, idrColumn, iddcColumn);
        Callback<TableColumn<Publication, String>, TableCell<Publication, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Publication, String>, TableCell<Publication, String>>() {
            String path;

            @Override
            public TableCell call(final TableColumn<Publication, String> param) {
                final TableCell<Publication, String> cell = new TableCell<Publication, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            System.out.println(item);
                            path = item;
                            System.out.println(path);
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
                            System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };
        idiColumn.setCellFactory(cellFactoryImage);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Publication ajoutée!");
                alert.showAndWait();
                
    }

    @FXML
    private void addimage(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = "file:"+selectedFile.getAbsolutePath();
//                path = selectedFile.toURI().toURL().toExternalForm();
            imageview.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            selectimg.setText(path);

        }
    }

    @FXML
    private void indexx(MouseEvent event) {
        selectedItemm = list.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(selectedItemm.getId()));
        nom.setText(selectedItemm.getNom());
        description.setText(selectedItemm.getDescription());
        adresse.setText(selectedItemm.getAdresse());
        
    }

    @FXML
    private void index3(KeyEvent event) {
    
         try {
//            Parent parent = FXMLLoader.load(getClass().getResource("DetailPublication.fxml"));
//            Scene scene =new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPublication.fxml"));
            Parent root = loader.load();
            DetailPublicationController ac =loader.getController(); 
            nom.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
