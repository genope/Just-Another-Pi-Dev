/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierOffresController implements Initializable {

    @FXML
    private JFXDatePicker txtdatedebut;
    @FXML
    private JFXTextArea txtdescrip;
    @FXML
    private ImageView img;
//    @FXML
//    private JFXDatePicker textdatefin1;
    @FXML
    private JFXTextField txtnom1;
    @FXML
    private JFXTextField txtprix1;

   

    
    @FXML
    private JFXDatePicker textdatefin1;
    private JFXTextField txtId;
    @FXML
    private JFXButton btnmodifier;

    public void setTxtdatedebut(JFXDatePicker txtdatedebut) {
        this.txtdatedebut= textdatefin1;
    }

    public void setTxtdescrip(String txtdescrip) {
        this.txtdescrip.setText(txtdescrip);
    }

    public void setTxtnom1(String txtnom1) {
        this.txtnom1.setText(txtnom1);
    }

    public void setTxtprix1(String txtprix1) {
        this.txtprix1.setText(txtprix1);
    }

    public void setTextdatefin1(JFXDatePicker textdatefin1) {
        this.textdatefin1 = textdatefin1;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


   

    @FXML
    private void ModifOffre(ActionEvent event) {
    }

    @FXML
    private void Modifimage(ActionEvent event) {
    }

    
}
