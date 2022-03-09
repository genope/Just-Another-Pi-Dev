/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.connexion.MaConnexion;
import shared.entities.CategorieProduit;
import shared.entities.Produit;
import shared.services.CategorieServices;
import shared.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class ModifierCategorieController implements Initializable {

    private TableColumn<CategorieProduit, Integer> idCat;
    @FXML
    private TableColumn<CategorieProduit, String> nameCat;
    private TextField catName;
    @FXML
    private Button ajouterCatbtn;
    @FXML
    private Button SupprCatbtn;
    @FXML
    private TableView<CategorieProduit> tableCat;
    @FXML
    private TextField tfCatName;
    private TextField tfIdCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListenerCategorie();
        Connection mc = MaConnexion.getInstance().getCnx();
        showCategorieTable();
    }    

    @FXML
    private void ajouterCat(MouseEvent event) {
        String nomCategorie = tfCatName.getText();
        int idCategorie = Integer.parseInt(tfIdCat.getText());
        
        CategorieProduit cat = new CategorieProduit(nomCategorie);
        CategorieServices cats = new CategorieServices();
        cats.AjouterCategorieProd(cat);
        showCategorieTable();
        
    }
    
    
    public void showCategorieTable(){
        ObservableList<CategorieProduit> list = getProduitList();
        //idCat.setCellValueFactory(new PropertyValueFactory<CategorieProduit, Integer>("idCategorie"));
        nameCat.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("nomCategorie"));
        tableCat.setItems(list);
    }
    private ObservableList<CategorieProduit> getProduitList() {
        ObservableList<CategorieProduit> CategorieListe = FXCollections.observableArrayList();
        CategorieServices prods = new CategorieServices();
        CategorieListe = prods.getAllCategorieProduit();
        
        
        return CategorieListe;
    }
    
    private void addListenerCategorie(){
        tableCat.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null){
                SupprCatbtn.setDisable(false);
                //tfIdCat.setText(String.valueOf(newSelection.getIdCategorie()));
                tfCatName.setText(newSelection.getNomCategorie());
            }
            else {
                //tfIdCat.setText("");
                //tfIdCat.setText(String.valueOf(newSelection.getIdCategorie()));
                tfCatName.setText("");
                tfCatName.setText(newSelection.getNomCategorie());
                SupprCatbtn.setDisable(true);

            }
        });
    }

    @FXML
    private void SupprCatEntry(MouseEvent event) {
        int idCat = tableCat.getSelectionModel().getSelectedItem().getIdCategorie();
        CategorieServices cats = new CategorieServices();
        cats.supprimerCategorieProduit(idCat);
    
        showCategorieTable();
    }
}
