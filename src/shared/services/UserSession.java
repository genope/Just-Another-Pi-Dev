
package shared.services;

import java.sql.Date;
import shared.entities.User;
public class UserSession {
  

    public static int userCin;
    public static String userString;
    public static Date userDate;
    private final UserService userService = new UserService();

    public void setUserId(int cin,String mail)
    {
        if(cin == 0) return ;
        UserSession.userCin = cin ;
        UserSession.userString=mail;
    }
    
    public int getUserCin() {
        return userCin;
    }

    public  void setUserCin(int userInt) {
        UserSession.userCin = userInt;
    }

    public static Date getUserDate() {
        return userDate;
    }

    public static void setUserDate(Date userDate) {
        UserSession.userDate = userDate;
    }

    public static String getUserString() {
        return userString;
    }

    public static void setUserString(String userString) {
        UserSession.userString = userString;
    }
    public static int getUserId()
    {
        return UserSession.userCin;
    }
    
    public User getUser()
    {
        return userService.GetUserByCin(userCin);
    }


}
