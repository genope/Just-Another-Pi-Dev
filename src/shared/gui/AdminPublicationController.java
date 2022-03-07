/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import shared.entities.Publication;
import static shared.gui.AffichagePublicationController.selectedItem;
import shared.services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AdminPublicationController implements Initializable {
    
static Publication selectedItem5;
    @FXML
    private TableView<Publication> list;
    @FXML
    private Button supprimerbtn;
    

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
    }    

    @FXML
    private void index(MouseEvent event) {
            selectedItem5 = list.getSelectionModel().getSelectedItem();
             
             
    }

    @FXML
    private void SupprimerPublication(ActionEvent event) {
        Publication pub = new Publication();
        PublicationService pubService = new PublicationService();
        selectedItem5=list.getSelectionModel().getSelectedItem();
        pubService.supprimerPublication(selectedItem5.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Publication supprimée!");
                alert.showAndWait();
                
                
        
        
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
                
                
    }

    @FXML
    private void index3(KeyEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AdminDetailPub.fxml"));
            Scene scene =new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDetailPubl.fxml"));
//            Parent root = loader.load();
//            DetailPublicationController ac =loader.getController(); 
//            supprimerbtn.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
