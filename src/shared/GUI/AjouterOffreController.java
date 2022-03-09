/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import shared.entities.Offres;
import shared.entities.enums.CategorieOffres;
import shared.services.OffreServices;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import shared.services.UserService;
import shared.services.UserSession;



/**
 * FXML Controller class
 *
 * @author user
 */

      
public class AjouterOffreController implements Initializable {

    @FXML
    private DatePicker textdatefin;
    @FXML
    private TextField txtnom;
    @FXML
    private DatePicker txtdatedebut;
    @FXML
    private TextArea txtdescrip;

    @FXML
    private TextField txtprix;
    @FXML
    private Button btnajouter;
    @FXML
    private ComboBox<String> Categ;
    @FXML
    private ComboBox<String> ville;
    private static Stage pStage;
    File file;
    File file1;

    public static Stage getpStage() {
        return pStage;
    }

    public static void setpStage(Stage pStage) {
        AjouterOffreController.pStage = pStage;
    }
    @FXML
    private ImageView img;
    private RenderedImage src;
    private BufferedImage dst;
    UserSession connectedUser=new UserSession();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        Categ.getItems().add("Maison");
        Categ.getItems().add("Appartement");
        Categ.getItems().add("Chambre");
        Categ.getItems().add("Voiture");
        Categ.getItems().add("Moto");
        Categ.getItems().add("Velo");

        ville.getItems().add("Ariana");
        ville.getItems().add("Beja");
        ville.getItems().add("Ben Arous");
        ville.getItems().add("Bizerte");
        ville.getItems().add("Gabes");
        ville.getItems().add("Gafsa");
        ville.getItems().add("Jendouba");
        ville.getItems().add("Kairouan");
        ville.getItems().add("Kasserine");
        ville.getItems().add("Kebili");
        ville.getItems().add("Manouba");
        ville.getItems().add("Kef");
        ville.getItems().add("Mahdia");
        ville.getItems().add("Medenine");
        ville.getItems().add("Monastir");
        ville.getItems().add("Nabeul");
        ville.getItems().add("Sfax");
        ville.getItems().add("Sidi Bouzid");
        ville.getItems().add("Siliana");
        ville.getItems().add("Sousse");
        ville.getItems().add("Tatouine");
        ville.getItems().add("Tozeur");
        ville.getItems().add("Tunis");
        ville.getItems().add("Zaghouan");
        ville.selectionModelProperty().get().selectFirst();
    }

    @FXML
    private CategorieOffres CategorieValue() {

        switch (Categ.selectionModelProperty().get().getSelectedItem()) {
            case "Maison":

                return CategorieOffres.valueOf("Maison");
            case "Appartement":
                return CategorieOffres.valueOf("Appartement");

            case "Chambre":
                return CategorieOffres.valueOf("Chambre");

            case "Voiture":
                return CategorieOffres.valueOf("Voiture");

            case "Moto":

                return CategorieOffres.valueOf("Moto");

        }
        return CategorieOffres.valueOf("Velo");

    }

    @FXML
    private String VilleValue() {
        switch (ville.selectionModelProperty().get().getSelectedItem()) {
            case "Ariana":
                return "Ariana";

            case "Beja":
                return "Beja";

            case "Ben Arous":
                return "Ben Arous";

            case "Gabes":
                return "Gabes";

            case "Gafsa":
                return "Gafsa";

            case "Jendouba":
                return "Jendouba";

            case "Kairouan":
                return "Kairouan";

            case "Kasserine":
                return "Kasserine";

            case "Manouba":
                return "Manouba";

            case "Kef":
                return "Kef";

            case "Mahdia":
                return "Mahdia";

            case "Medenine":
                return "Medenine";

            case "Monastir":
                return "Monastir";

            case "Nabeul":
                return "Nabeul";

            case "Sfax":
                return "Sfax";

            case "Sidi Bouzid":
                return "Sidi Bouzid";

            case "Siliana":
                return "Siliana";

            case "Sousse":
                return "Sousse";

            case "Tatouine":
                return "Tatouine";

            case "Tozeur":
                return "Tozeur";

            case "Tunis":
                return "Tunis";
        }
        return "Zaghouan";
    }

    @FXML
    private void addOffre(ActionEvent event) throws FileNotFoundException, IOException {

        if (txtnom.getText().isEmpty()
                || txtdescrip.getText().isEmpty() || txtprix.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            txtnom.setEffect(in);
            txtprix.setEffect(in);
            txtdescrip.setEffect(in);
            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (TestPrix() & TestText() & TestDescription() & TestDate()) {

            String nom = txtnom.getText();

            String description = txtdescrip.getText();

            Float prix = Float.parseFloat(txtprix.getText());
            java.sql.Date datedebut = java.sql.Date.valueOf(txtdatedebut.getValue());

            java.sql.Date dateFin = java.sql.Date.valueOf(textdatefin.getValue());

            CategorieOffres categ;

            if (Categ.getSelectionModel().isEmpty()) {
                categ = null;
            } else {
                categ = CategorieValue();
            }

            String ville = VilleValue();

            FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String path = fileName;
            fl.read(data);
            fl.close();
            // OutputStream out = new FileOutputStream(new File(path));

//        OutputStream out = new FileOutputStream(new File(path));
//        out.write(data);
//        out.close();
            Offres of = new Offres(connectedUser.getUser().getCin(), nom, description, datedebut, dateFin, prix, false, ville, categ, path);

            OffreServices offresService = new OffreServices();
            //     System.out.println("hh");
            offresService.ajoutOffre(of);

            final Stage dialog = new Stage();
            dialog.initModality(Modality.WINDOW_MODAL);

            //  dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(10);
            dialogVbox.getChildren().add(new Text("Offre Ajputé"));
            Scene dialogScene = new Scene(dialogVbox, 200, 200);
            dialog.setScene(dialogScene);
            dialog.show();

//                try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonne.fxml"));
//                        Parent root = loader.load();
//                        
//                        GetAllOffersController c =loader.getController ();
//                        
//                        //c.setLname(offresService.getAllHoreca().toString());
//                        System.out.println(offresService.getAllHoreca().toString());
//                        System.out.println("fff");
//                       // txtnom.getScene().setRoot(root);
//                } 
//                catch (IOException ex) {
//                    System.out.println("gg");
//                System.out.println (ex.getMessage ());
//
//            }
        }
    }

//    public void sauverImage(BufferedImage image, String nomImage) throws IOException {
//        File nomfichier = new File("C:\\Users\\user\\Desktop\\iheb" + nomImage + ".bmp");// ou jpg
//
//        ImageIO.write(image, "BMP", nomfichier);//ou JPG
//    }
    @FXML
    private File addimage(ActionEvent event) {

        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\uploads\\images";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        return file;
    }

    private boolean TestText() {
        Pattern p = Pattern.compile("[a-zA-Z0-9]*[a-zA-Z0-9]*");
        Pattern p1 = Pattern.compile("[s][+][0-9]*");
        Matcher m = p.matcher(txtnom.getText());
        Matcher m1 = p.matcher(txtnom.getText());
        if (m.find() && m.group().equals(txtnom.getText()) || m1.find() && m1.group().equals(txtnom.getText())) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un nom valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean TestDate() {
        java.sql.Date datedebut = java.sql.Date.valueOf(txtdatedebut.getValue());

        java.sql.Date dateFin = java.sql.Date.valueOf(textdatefin.getValue());
        if (dateFin.compareTo(datedebut) > 0) {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            txtdatedebut.setEffect(in);
            textdatefin.setEffect(in);
            return true;

        } else {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            txtdatedebut.setEffect(in);
            textdatefin.setEffect(in);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une date valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean TestDescription() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*");
        Matcher m = p.matcher(txtdescrip.getText());
        if (m.find() && m.group().equals(txtdescrip.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une description  valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean TestPrix() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtprix.getText());
        if (m.find() && m.group().equals(txtprix.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Syntaxe Prix");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un prix valide");
            alert.showAndWait();

            return false;
        }
    }

//    ImageTypeSpecifier createFromRenderedImage(RenderedImage image) throws IOException {
//        String fileNmaeExt = file.getName();
//
//        File initialImage = new File("C:\\Users\\user\\Desktop\\iheb");
//        if (image == null) {
//            throw new IllegalArgumentException("image == null!");
//        }
//
//        if (image instanceof BufferedImage) {
//            write(image, fileNmaeExt, initialImage);
//
//        }
//        return new ImageTypeSpecifier(image);
//    }
    @FXML
    private void testprix(KeyEvent event) {
        if (txtprix.getText().isEmpty() == false) {
            if (TestPrix()) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                txtprix.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                txtprix.setEffect(in);
            }

        }
    }

    @FXML
    private void testDescri(KeyEvent event) {
        if (txtdescrip.getText().isEmpty() == false) {
            if (TestDescription()) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                txtdescrip.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                txtdescrip.setEffect(in);
            }
        }
    }

    @FXML
    private void testNomm(KeyEvent event) {
        if (TestText() & txtnom.getText().isEmpty() == false) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            txtnom.setEffect(in);
        } else {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            txtnom.setEffect(in);
        }
    }

}
