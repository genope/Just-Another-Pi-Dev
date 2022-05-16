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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import shared.entities.Offres;
import shared.entities.User;
import shared.services.UserService;

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

    @FXML
    private Label NameUser;
    
   private UserService user=new UserService();
    private ImageView Upic;
    @FXML
    private Circle HostPic;
    
    
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
           
            NameUser.setText(user.GetUserByCin(offre.getIdhost()).getNom()+" "+user.GetUserByCin(offre.getIdhost()).getPrenom());
                 Circle cir2 = new Circle(50, 50, 50);
        File folder = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources");
		       
                             
        
//          Image imge = new Image(new FileInputStream("C:\\xampp\\htdocs\\uploads\\images\\"
//                  + user.GetUserByCin(offre.getIdhost()).getImage_cin()));
//                    HostPic.setFill(new ImagePattern(imge));
//                    cir2.setFill(new ImagePattern(imge));
//             
            
             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
             {
               
                      
                 if(findAllFilesInFolder(folder).get(i).getName().equals(offre.getFile()))
                 {
                   

               
                     Image imgee = new Image(new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\"+offre.getFile()));
                     image.setImage(imgee);
                 }

             }

        
        }



    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(offre);
    }

    @FXML
    private void image_admin(MouseEvent event) {
    }

    
}
