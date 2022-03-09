/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.API;

/**
 *
 * @author l3ej
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.*;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class onlineMail {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */

    /**
     *
     * @author user
     */
    public static void onlineMail(String recepient) {
        try {
            System.out.println("pre");
            Properties props = new Properties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            String myaccountEmail = "aziz.lajili@esprit.tn";
            String pass = "213JMT0193";

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myaccountEmail, pass);
                }
            });
            System.out.println("post");
            Message message = prepareMessage(session, myaccountEmail, recepient);
            
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Message prepareMessage(Session session, String myac, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myac));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Commande approuvé");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Votre Commande a ete approuvé");

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            DataSource source = new FileDataSource("C:\\Users\\user\\Documents\\NetBeansProjects\\Shared\\src\\ressources\\1.png");

            messageBodyPart2.setDataHandler(new DataHandler(source));

            Multipart multipart = new MimeMultipart();
            messageBodyPart2.setFileName("1.png");
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

}
