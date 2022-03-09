/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package shared.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import shared.entities.Produit;



/**
 * FXML Controller class
 *
 * @author l3ej
 */
public class CardProduitController implements Initializable {
    
    private Produit produit;
    @FXML
    private ImageView IvProd;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelregion;
    @FXML
    private Label LabelPrix;
    @FXML
    private Label labelNom;
    MyListener mylistener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public  List<File> findAllFilesInFolder(File folder) {
          List<File> list=new ArrayList<>();
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				list.add(file);
                                          
                            
			} else {
				findAllFilesInFolder(file);
			}
		}
                return list;
	}
    
    public void AddProduit(Produit produit,MyListener mylistener) throws SQLException, IOException{
        //produit.getImage().getBinaryStream();
        this.produit=produit;
        this.mylistener = mylistener;
        labelNom.setText(produit.getDesignation());
        labelDescription.setText(produit.getDescription());
        LabelPrix.setText(String.valueOf(produit.getPrix()));
        labelregion.setText(produit.getNomCategorie());
        //IvProd.setImage(SwingFXUtils.toFXImage(ImageIO.read(produit.getImage().getBinaryStream()), null));
        File folder = new File("\\opt\\lampp\\htdocs\\uploads\\images");
        
        File folder2 = new File("/");
        System.out.println(folder2);
        System.out.println("12");
//             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
//             {
//                 if(findAllFilesInFolder(folder).get(i).getName().equals(produit.getImage()))
//                 { Image imge = new Image(new FileInputStream("\\opt\\lampp\\htdocs\\uploads\\images\\"+produit.getImage()));
//                      IvProd.setImage(imge);
//                 }
//             }

        
    }


    @FXML
    private void click(MouseEvent event) {
        mylistener.onClickListener(produit);
    }
    
}
