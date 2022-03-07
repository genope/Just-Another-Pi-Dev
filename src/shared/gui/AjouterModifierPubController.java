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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import shared.entities.Publication;
import shared.services.MyListener;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AjouterModifierPubController implements Initializable {
private MyListener myListener;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<Object> region;
    @FXML
    private Button selectimg;
    @FXML
    private Button Ajouterpubbtn;
    @FXML
    private Button Modifierbtn;
    @FXML
    private GridPane grid3;
    File selectedFile;
    @FXML
    private ImageView imageview;
String path;
    @FXML
    private TextField id_guest;
    @FXML
    private Button afficherbtn;
    
    /**
     * Initializes the controller class.
     */
    
    private void setChosenPub(Publication p){
        id.setText(String.valueOf(p.getId()));
        nom.setText(p.getNom());
        description.setText(p.getDescription());
        adresse.setText(p.getAdresse());
//        Image image =new image(p.getImage(),112,100,false,true);
//        imageview.setImage(image);
    
            }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        String regions[] =
                   { "ARIANA", "SOUSSE", "TUNIS", "NABEUL","MONASTIR","BIZERTE","BEN AROUS","BEJA","GABES","GAFSA","JENDOUBA","KAIROUAN","KASSERINE","KEBILI","KEF","MAHDIA","MANOUBA","MEDNINE","SFAX","SIDI BOUZID","SILIANA","TATAOUINE","TOZEUR","ZAGHOUAN" };
        region.setItems(FXCollections.observableArrayList(regions));
        
        //
//         PublicationService rs= new PublicationService();
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < rs.afficherPublicationparuser(Integer.parseInt(id_guest.getText())).size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("pub.fxml"));
//                VBox cardvbox = fxmlLoader.load();
//
//               PubController RecController = fxmlLoader.getController();
//                RecController.setData(rs.afficherPublication().get(i), myListener);
//
//                if (column == 1) {
//                    column = 0;
//                    row++;
//                }
//
//                grid3.add(cardvbox, column++, row); //(child,column,row)
//                //set grid width
//                grid3.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid3.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid3.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid3.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid3.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid3.setMaxHeight(Region.USE_PREF_SIZE);
//
//                grid3.setMargin(cardvbox, new Insets(10));
//                
//            }
//            } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        PublicationService rs= new PublicationService();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rs.afficherPublication().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("pub.fxml"));
                VBox cardvbox = fxmlLoader.load();

               PubController RecController = fxmlLoader.getController();
                RecController.setData(rs.afficherPublication().get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid3.add(cardvbox, column++, row); //(child,column,row)
                //set grid width
                grid3.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid3.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid3.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid3.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid3.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid3.setMaxHeight(Region.USE_PREF_SIZE);

                grid3.setMargin(cardvbox, new Insets(10));
                
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Votre publication est ajoutée!");
                alert.showAndWait();
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
                alert.setContentText("Votre publication est bien modifiée!");
                alert.showAndWait();
                PublicationService rs= new PublicationService();
                int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rs.afficherPublicationparuser(Integer.parseInt(id_guest.getText())).size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("pub.fxml"));
                VBox cardvbox = fxmlLoader.load();

               PubController RecController = fxmlLoader.getController();
                RecController.setData(rs.afficherPublication().get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid3.add(cardvbox, column++, row); //(child,column,row)
                //set grid width
                grid3.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid3.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid3.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid3.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid3.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid3.setMaxHeight(Region.USE_PREF_SIZE);

                grid3.setMargin(cardvbox, new Insets(10));
                
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void click2(MouseEvent event) {
    }

    @FXML
    private void AfficherPubparuser(ActionEvent event) {
         PublicationService rs= new PublicationService();
        myListener = new MyListener() { 
            public void onClickListener(Publication pub) {
                setChosenPub(pub);
            }
        };
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rs.afficherPublicationparuser(Integer.parseInt(id_guest.getText())).size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("pub.fxml"));
                VBox cardvbox = fxmlLoader.load();

               PubController RecController = fxmlLoader.getController();
                RecController.setData(rs.afficherPublication().get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid3.add(cardvbox, column++, row); //(child,column,row)
                //set grid width
                grid3.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid3.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid3.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid3.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid3.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid3.setMaxHeight(Region.USE_PREF_SIZE);

                grid3.setMargin(cardvbox, new Insets(10));
                
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
