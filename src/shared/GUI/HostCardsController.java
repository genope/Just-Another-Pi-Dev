/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static shared.GUI.GererOffresController.setPrimarySatge;
import shared.entities.Offres;
import shared.services.OffreServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HostCardsController implements Initializable {

    @FXML
    private Label myname;
    @FXML
    private Label mydatedebut;
    @FXML
    private Label mydatefin;
    @FXML
    private Label myville;
    @FXML
    private Label myprix;
    @FXML
    private Button supproffre;
    
    private Offres offre;
    
        private SupprimerCard supprimero;
        
        private Modifier modif;
        
          Stage window;
        Scene fxmlFile;
        private static Stage primarySatge;
@FXML
    private ImageView hostimg;
    public static void setPrimarySatge(Stage primarySatge) {
        GererOffresController.primarySatge = primarySatge;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
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

    public Button getSupproffre() {
        return supproffre;
    }
    
        public void setData(Offres offre,SupprimerCard supp,Modifier modif) throws FileNotFoundException {
          this.offre=offre;
            this.supprimero=supp;
            this.modif=modif;
        myname.setText(offre.getNom());
         mydatedebut.setText(offre.getDatedebut().toString());
          mydatefin.setText(offre.getDatefin().toString());
           myville.setText(offre.getVille());
             myprix.setText(String.valueOf(offre.getPrix()));
             
            File folder = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources");
		
           
          
             
            
             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
             {
//                 System.out.println(findAllFilesInFolder(folder).get(i).getName());
//                 System.out.println("hhhhhhhhhhh\n");
//                      System.out.println(offre.getFile());
                 if(findAllFilesInFolder(folder).get(i).getName().equals(offre.getFile()))
                 {
                     
                     
//                     
//                     BufferedImage bf;
//                     bf = ImageIO.read(findAllFilesInFolder(folder).get(i));
//                     Image img=SwingFXUtils.toFXImage(bf,null);
               
                     Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
                      hostimg.setImage(imge);
                 }

             }

        
        }  

        
        
    @FXML
    private void supprimer(ActionEvent event) {
      supprimero.supprimer(offre);
    }

    @FXML
    private void modifieroffre(ActionEvent event){
    
       modif.modifier(offre);

    }   

  
}
