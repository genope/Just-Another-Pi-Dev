package shared;



import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.UnsupportedEncodingException;
import shared.connexion.MaConnexion;


public class Shared {


    public static void main(String[] args) throws UnsupportedEncodingException, UnirestException {
        MaConnexion m = MaConnexion.getInstance();
    }
    
       
    
}
    