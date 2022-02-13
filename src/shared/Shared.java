package shared;

import shared.connexion.MaConnexion;
import shared.services.UserService;


public class Shared {


    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        UserService userService = new UserService();
        System.out.println(userService.GetUserByMail("7ammoud@7ammoud.7ammoud", "7ammoud"));
    }
    
}
