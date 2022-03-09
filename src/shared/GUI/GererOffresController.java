/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static shared.GUI.HostCardsController.setPrimarySatge;
import shared.entities.Offres;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GererOffresController implements Initializable {

    @FXML
    private TableView<Offres> OffresTables;
    @FXML
    private TableColumn<Offres, Date> dateDcol;
    @FXML
    private TableColumn<Offres, Date> dateFcol;
    @FXML
    private TableColumn<Offres, String> typecol;
    @FXML
    private TableColumn<Offres, String> categcol;
    @FXML
    private TableColumn<Offres, String> villecol;
    @FXML
    private TableColumn<Offres, Float> prixcol;

    /**
     * Initializes the controller class.
     */
    ObservableList<Offres> list = FXCollections.observableArrayList();
    OffreServices offresS = new OffreServices();
    private TextField txtId;
    private TextField newdateF;
    private TextField newprix;

    private Button btnmodif;
    private Button btnsupprimer;
    private TextField newdateDe;
    private TextArea newDescrip;
    ObservableList<Offres> productSearchModelLObservableList = FXCollections.observableArrayList();
    @FXML
    private TextField keywordTextField;
    @FXML
    private GridPane grid;

    private Offres offre;
    private SupprimerCard sup;
    private Modifier modif;
    Stage window;
    Scene fxmlFile;
    static Stage primarySatge;

    public static void setPrimarySatge(Stage primarySatge) {
        GererOffresController.primarySatge = primarySatge;
    }
    @FXML
    private VBox chosenOffreCard;
    private JFXTextField Nprix;
    @FXML
    private ImageView fruitImg;
    private Label LabelDateD;
    private Label LabelDateF;
    @FXML
    private Label LabelV;

    private MyListener myListener;
    @FXML
    private JFXTextField Nnom;
    @FXML
    private JFXDatePicker NvDateD;
    @FXML
    private JFXDatePicker NvDateF;
    private HBox Nvprix;
    @FXML
    private JFXTextField Nprix3;
    private int i=0;

    private void openModalwindow(String resource, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.setTitle(title);
        setPrimarySatge(window);
        window.showAndWait();

    }
//private void setChosenOffre(Offres offre) {
//        try {
//            Nnom.setText(offre.getNom());
//            LabelV.setText(offre.getVille());
//            NvDateD.setText(String.valueOf(offre.getDatedebut()));
//            NvDateF.setText(String.valueOf(offre.getDatefin()));
//            Nprix3.setText(String.valueOf(offre.getPrix()));
//            
//            
//            Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
//            fruitImg.setImage(imge);
//            
//            
////       chosenOffreCard.setStyle("-fx-background-color: #" + offre.getColor() + ";\n"
////                + "    -fx-background-radius: 30;");
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    private void modifierr(Offres offre) {
//
//        Nnom.setText(offre.getNom());
//        



  LocalDate localDate1 = offre.getDatedebut().toLocalDate();
  LocalDate localDate2 = offre.getDatefin().toLocalDate();
 
       NvDateD.setValue(localDate1);
       NvDateF.setValue(localDate2);
        Nprix3.setText(String.valueOf(offre.getPrix()));
        LabelV.setText(offre.getVille());
        i=offre.getId();
        System.out.println(i);
          Image imge;
        try {
            imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
             fruitImg.setImage(imge);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
//        
        
        
//        try {
//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierOffres.fxml"));
//            Parent root = loader.load();
//          ModifierOffresController modifi = new ModifierOffresController();
//   
//    modifi.setTxtId(offre.getId());
//            modif.setTxtnom1(offre.getNom());
//            modif.setTxtId(String.valueOf(offre.getId()));
//            modif.setTxtprix1(String.valueOf(offre.getPrix()));
//            modif.setTxtdescrip(offre.getDescription());
//         
//            keywordTextField.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//                  
//        }
    }

    private void supprimerr(Offres offre) {
        OffreServices offree = new OffreServices();
        offree.suuprimerLogement(offre.getId());

    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        OffreServices offre = new OffreServices();

        for (int i = 0; i < offre.getAllMyOffres(110405018).size(); i++) {

            productSearchModelLObservableList.add(offre.getAllMyOffres(110405018).get(i));
        }

        FilteredList<Offres> filteredData = new FilteredList<>(productSearchModelLObservableList, b -> true);

        keywordTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            filteredData.setPredicate(e -> {

                if (newValue.isEmpty() || newValue == null) {

                    return true;

                }

                String searchKeyword = newValue.toLowerCase();

                if (e.getDescription().toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (String.valueOf(e.getCateg()).toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (e.getTypeOff().toString().toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (String.valueOf(e.getPrix()).toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (e.getVille().toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }

            });

            SortedList<Offres> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(OffresTables.comparatorProperty());

            OffresTables.setItems(sortedData);
        });

        showProdTable();
        addListenerOffre();
        int column = 1;
        int row = 0;

        for (int i = 0; i < offre.getAllOffresById2(110405018).size(); i++) {

           

            try {

                sup = new SupprimerCard() {
                    @Override
                    public void supprimer(Offres offre) {
                        supprimerr(offre);
                    }
                };
                modif = new Modifier() {
                    @Override
                    public void modifier(Offres offre) {
                        modifierr(offre);

                    }
                };
                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("HostCards.fxml"));

                AnchorPane anchorPane = cards.load();

                HostCardsController offreservice = cards.getController();

                offreservice.setData(offre.getAllOffresById2(110405018).get(i), sup, modif);

                //       offreservice.setData(offre, supp);
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

////                  
            } catch (IOException ex) {
                Logger.getLogger(GererOffresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void showProdTable() {

        list = offresS.getAllOffresById2(110405018);
        dateDcol.setCellValueFactory(new PropertyValueFactory<Offres, Date>("datedebut"));
        dateFcol.setCellValueFactory(new PropertyValueFactory<Offres, Date>("datefin"));
        typecol.setCellValueFactory(new PropertyValueFactory<Offres, String>("description"));
        categcol.setCellValueFactory(new PropertyValueFactory<Offres, String>("categ"));
        villecol.setCellValueFactory(new PropertyValueFactory<Offres, String>("ville"));
        prixcol.setCellValueFactory(new PropertyValueFactory<Offres, Float>("prix"));

        OffresTables.setItems(list);
    }

//    
    private void addListenerOffre() {

        OffresTables.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnsupprimer.setDisable(false);
                btnmodif.setDisable(false);
                txtId.setText(String.valueOf(newSelection.getId()));
                newdateF.setText(String.valueOf(newSelection.getDatefin()));
                newprix.setText(String.valueOf(newSelection.getPrix()));
                newdateDe.setText(String.valueOf(newSelection.getDatedebut()));
                newDescrip.setText((newSelection.getDescription()));
            } else {
                btnsupprimer.setDisable(true);
                btnmodif.setDisable(true);

                txtId.setText("");
                txtId.setText(String.valueOf(newSelection.getId()));

                newdateF.setText("");
                newdateF.setText(String.valueOf(newSelection.getDatefin()));
                newprix.setText("");
                newprix.setText(String.valueOf(newSelection.getPrix()));
                newdateDe.setText("");
                newdateDe.setText(String.valueOf(newSelection.getDatedebut()));
                newDescrip.setText("");
                newDescrip.setText(String.valueOf(newSelection.getDescription()));

            }
        });

    }

    @FXML
    private void Modif(ActionEvent event) {
         java.sql.Date   newdateDeb = java.sql.Date.valueOf(NvDateD.getValue());
     
      java.sql.Date   newdateFin = java.sql.Date.valueOf(NvDateF.getValue());
        
        
        
        
        
     Offres off=new Offres("Laico",newdateDeb ,newdateFin, Float.valueOf(Nprix3.getText()));
        System.out.println(i);
      //  Offres off = new Offres("gggg", newdateDeb, newdateFin, 0f);
        //System.out.println(off);
        OffreServices offres = new OffreServices();
        offres.modifierOffreById(i, off);
    }

}
