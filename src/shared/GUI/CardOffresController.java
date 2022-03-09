/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import shared.entities.Offres;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CardOffresController implements Initializable {

    @FXML
    private Label LabelNom;
    @FXML
    private Label LabelDateDebut;
    @FXML
    private Label LabelDateFin;
    @FXML
    private Label LabelVille;
    @FXML
    private Label LabelPrix;
    private Offres offre;
    private MyListener myListener;
    @FXML
    private VBox vbox1;
    @FXML
    private ImageView image;

    
   
    
    
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
        public void setData(Offres offre,MyListener mylistener) throws IOException {
      
            this.offre=offre;
            this.myListener=mylistener;
            LabelNom.setText(offre.getNom());
            LabelDateDebut.setText(offre.getDatedebut().toString());
            LabelDateFin.setText(offre.getDatefin().toString());
            LabelVille.setText(offre.getVille());
            LabelPrix.setText(String.valueOf(offre.getPrix()));
           
            
            
         
        File folder = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources");
		
        
          
             
            
             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
             {
               
                      
                 if(findAllFilesInFolder(folder).get(i).getName().equals(offre.getFile()))
                 {
                   

               
                     Image imge = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
                      image.setImage(imge);
                 }

             }

        
        }



    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(offre);
    }

    
}
