package shared;

import java.sql.Date;
import shared.connexion.MaConnexion;
import shared.entities.User;
import shared.entities.enums.Etat;
import shared.entities.enums.Role;
import shared.services.UserService;


public class Shared {


    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        UserService userService = new UserService();
       // User user = userService.GetUserByMail("hasen@hasenhhh.hsds", "aze");
        //user.setNom("addddddzee");
        //userService.UpdateUser(user);
        User user = userService.GetUserByCin(1);
        userService.GetUserByCin(1);
         User user1 = userService.GetUserByCin(12);
        userService.GetUserByCin(12);
        
        
        
        
        
        //register host
        /* User user=new User(1,"hassen","mabrouk","hassen@hassen.has","aze",new Date(1999, 1, 1),12,Role.Host,Etat.approved,"okk","hhhh");
        userService.register(user);
         //register guest
        User user1=new User(2,"hassen","mabrouk","hasen@hasen.hasss","aze",new Date(1999, 1, 1),12,Role.Guest,Etat.approved,"okk","hhhh");
        userService.register(user1);*/
     //    User user2=new User(39,"hassen","mabrouk","hasen@hasenhhh.hsds","aze",new Date(1999, 1, 1),12,Role.Admin,Etat.approved,"okk","hhhh");
       // userService.register(user2);
       
       
    }
    
}
    