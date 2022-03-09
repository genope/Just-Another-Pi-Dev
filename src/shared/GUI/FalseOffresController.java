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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import shared.entities.Offres;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FalseOffresController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label NameOffre;
    @FXML
    private Label VilleOffre;
    @FXML
    private Label descripOffre;
    private Offres offre;
    @FXML
    private Label PrixOffre;
            private ApprouveOffre Aprouve;
    @FXML
    private ImageView img;

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
        
        
        
     public void setData(Offres offre,ApprouveOffre app) throws IOException {
      this.Aprouve=app;
            this.offre=offre;
            NameOffre.setText(offre.getNom());
            descripOffre.setText(offre.getDatedebut().toString());
            VilleOffre.setText(offre.getVille());
            PrixOffre.setText(String.valueOf(offre.getPrix()));
           
            
            
         
        File folder = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources");
		
        
          
             
            
             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
             {
               
                      
                 if(findAllFilesInFolder(folder).get(i).getName().equals(offre.getFile()))
                 {
                   

               
                     Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
                      img.setImage(imge);
                 }

             }

        
        }

 

    @FXML
    private void Approuve(ActionEvent event) {
        Aprouve.Approuve(offre);
    }
}
