/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.GUI;

import javax.mail.search.SentDateTerm;
import shared.API.SendMsg;

/**
 *
 * @author user
 */
public class mail {
    public static void main(String[] args) throws Exception {
    
        SendMsg.sendMail("iheb.kraiem@esprit.tn");
    }
    
}
