package shared;



import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import shared.API.YoutubeAPI;
import shared.connexion.MaConnexion;
import shared.entities.CategorieProduit;
import shared.entities.Evenement;
import shared.entities.Panier;
import shared.entities.PanierDetails;
import shared.entities.User;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;
import shared.services.UserService;
import shared.entities.Produit;
import shared.services.EvenementService;
import shared.services.ProduitService;
import shared.services.CategorieServices;
import shared.services.PanierDetailsService;
import shared.services.PanierService;
//import shared.services.PanierService;


public class Shared {


    public static void main(String[] args) throws UnsupportedEncodingException, UnirestException {

        
        MaConnexion m = MaConnexion.getInstance();
//       
        
//        

//        CategorieProduit catprod = new CategorieProduit(20, "Chasse");
//        CategorieServices catserv = new CategorieServices();
        
//        catserv.AjouterCategorieProd(catprod);
//        catserv.afficherCategorieProduit();
        
//        
//        ProduitService prodserv = new ProduitService();
////        Produit prod1 = new Produit("tente", "petiteTente", "tente pour 2 personnes", 144, "image", 5, "Camping", "Kef");
////        prodserv.afficherProduit();
////
//        PanierService panserv = new PanierService();
//        Panier pan = new Panier(11, 3, 2.00d);
//        Panier pan2 = new Panier(11, 11,5.50d);
//        Panier pan3= new Panier(10, 11, 12.3);
//        panserv.ajouterPanier(pan3);
//        System.out.println(panserv.ListPanier());
////        panserv.ListPanier();
//        panserv.supprimerPanier(11);
//        System.out.println(panserv.ListPanier());
        
//        PanierDetailsService pandes = new PanierDetailsService();
//        PanierDetails pande = new PanierDetails("canne15",3,30,2.00);
        
//        pandes.ajouterPanierDetails(pande);
        
       // System.out.println(pandes.ListPanierDetails());
//        System.out.println(pandes.ListPanierDetailsUser(11));
          

//        System.out.println(pandes.getPanDetByrefPr("canne15"));
//        ProduitService prods = new ProduitService();
//        
//        System.out.println(prods.getProdJoin());
        
    
    }
    
       
    
}
    