/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import shared.services.OffreServices;
import shared.services.UserSession;



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
    @FXML
    private Label nameUser;

    /**
     * Initializes the controller class.
     */
    
     UserSession connectedUser=new UserSession();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameUser.setText(connectedUser.getUser().getNom()+" "+connectedUser.getUser().getPrenom());
        
        OffreServices offreService = new OffreServices();
        TotalOffres.setText(String.valueOf(offreService.getNbrMyOffres(connectedUser.getUser().getCin())));

        TotalLogs.setText(String.valueOf(offreService.getNbrLogemts(connectedUser.getUser().getCin())));
        MoysTra.setText(String.valueOf(offreService.getNbrMoyTransports(connectedUser.getUser().getCin())));
        Hore.setText(String.valueOf(offreService.getNbrMyHoreca(connectedUser.getUser().getCin())));

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Logement",offreService.getAllMyOffres(connectedUser.getUser().getCin()).size()),
                new PieChart.Data("Moyen De Transport", offreService.getNbrMoyTransports(connectedUser.getUser().getCin())),
                new PieChart.Data("Horeca", offreService.getNbrMyHoreca(connectedUser.getUser().getCin()))
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
