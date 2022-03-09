/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.entities.enums.TypeOffres;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DahshbordHostController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label TotalOffres;
    @FXML
    private Label TotalLogs;
    @FXML
    private Label MoysTra;
    @FXML
    private Label Hore;

    int a = 0;
    int b = 0;
    int c = 0;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OffreServices offreService = new OffreServices();
        TotalOffres.setText(String.valueOf(offreService.getNbrMyOffres(110405018)));

        TotalLogs.setText(String.valueOf(offreService.getNbrLogemts(110405018)));
        MoysTra.setText(String.valueOf(offreService.getNbrMoyTransports(110405018)));
        Hore.setText(String.valueOf(offreService.getNbrMyHoreca(110405018)));

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Logement",offreService.getAllMyOffres(110405018).size()),
                new PieChart.Data("Moyen De Transport", offreService.getNbrMoyTransports(110405018)),
                new PieChart.Data("Horeca", offreService.getNbrMyHoreca(110405018))
        );

//        for (int i = 0; i < offreService.getAllOffresById2(110405018).size(); i++) {
//          
//            if(offreService.getAllOffresById2(110405018).get(i).getTypeOff().toString().equals("Logement"))
//                list.add(i, element);
////        list.addAll(offreService.getAllOffresById2(110405018));
//        }
        piechart.setData(list);
    }


}
