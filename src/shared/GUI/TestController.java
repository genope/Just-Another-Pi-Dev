/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.entities.Offres;
import shared.entities.enums.CategorieOffres;
import static shared.entities.enums.CategorieOffres.Chambre;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class TestController implements Initializable {

    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox<String> choisirCateg;

    /**
     * Initializes the controller class.
     */
    Scene fxmlFile;

    Stage window;

    private Offres offre;
    @FXML
    private VBox chosenOffreCard;
    
    private SupprimerCard sup;
    private MyListener myListener;
    @FXML
    private Label LabelN;
    @FXML
    private Label LabelDateD;
    @FXML
    private Label LabelDateF;
    @FXML
    private Label LabelV;
    @FXML
    private Label LabelP;

    @FXML
    private CategorieOffres CategorieOffres() {
        switch (choisirCateg.selectionModelProperty().get().getSelectedItem()) {
            case "Maison":

                return CategorieOffres.valueOf("Maison");
            case "Appartement":
                return CategorieOffres.valueOf("Appartement");

            case "Chambre":
                return CategorieOffres.valueOf("Chambre");

            case "Voiture":
                return CategorieOffres.valueOf("Voiture");

            case "Moto":

                return CategorieOffres.valueOf("Moto");

        }
        return CategorieOffres.valueOf("Velo");
    }

    private void setChosenOffre(Offres offre) {
        try {
            LabelN.setText(offre.getNom());
            LabelV.setText(offre.getVille());
            LabelDateD.setText(String.valueOf(offre.getDatedebut()));
            LabelDateF.setText(String.valueOf(offre.getDatefin()));
            LabelP.setText(String.valueOf(offre.getPrix()));
            
            
            Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
            fruitImg.setImage(imge);
            
            
//       chosenOffreCard.setStyle("-fx-background-color: #" + offre.getColor() + ";\n"
//                + "    -fx-background-radius: 30;");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        private void supprimerr(Offres offre) {
       OffreServices offree=new OffreServices();
       offree.suuprimerLogement(offre.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choisirCateg.getItems().add("Maison");
        choisirCateg.getItems().add("Appartement");
        choisirCateg.getItems().add("Chambre");
        choisirCateg.getItems().add("Voiture");
        choisirCateg.getItems().add("Moto");
        choisirCateg.getItems().add("Velo");

        choisirCateg.selectionModelProperty().get().selectFirst();

        

        ConfirmerCateg2();

    }

    @FXML
    private void ConfirmerCateg2() {

         grid.getChildren().clear();
        OffreServices offres = new OffreServices();
        
        
        
        if (offres.getAllOffres(CategorieOffres()).size() > 0) {
            setChosenOffre(offres.getAllOffres(CategorieOffres()).get(0));
            myListener=new MyListener() {
                @Override
                public void onClickListener(Offres offre) {
                setChosenOffre(offre);
                }
            };
            
            sup=new SupprimerCard() {
                @Override
                public void supprimer(Offres offre) {
                  supprimerr(offre);
                }
            };
        }
        int column = 1;
        int row = 0;

        try {

            for (int i = 0; i < offres.getAllOffres(CategorieOffres()).size(); i++) {

                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("CardOffres.fxml"));

                AnchorPane anchorPane = cards.load();

                CardOffresController offreservice = cards.getController();

                offreservice.setData(offres.getAllOffres(CategorieOffres()).get(i),myListener);
               

                // System.out.println("hhh");
                // System.out.println(offreservice.getAllHoreca().get(i).getClass().getSimpleName());
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

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));
            }

////                  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Horeca(ActionEvent event) {
         grid.getChildren().clear();
        OffreServices offres = new OffreServices();
        
        
        
        if (offres.getAllHorecaById().size() > 0) {
            setChosenOffre(offres.getAllOffres(CategorieOffres()).get(0));
            myListener=new MyListener() {
                @Override
                public void onClickListener(Offres offre) {
                setChosenOffre(offre);
                }
            };
            
            
        int column = 1;
        int row = 0;

        try {

            for (int i = 0; i < offres.getAllHorecaById().size(); i++) {

                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("CardOffres.fxml"));

                AnchorPane anchorPane = cards.load();

                CardOffresController offreservice = cards.getController();

                offreservice.setData(offres.getAllHorecaById().get(i),myListener);
               

                // System.out.println("hhh");
                // System.out.println(offreservice.getAllHoreca().get(i).getClass().getSimpleName());
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

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));
            }

////                  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
        
    }
    
    

  
}
