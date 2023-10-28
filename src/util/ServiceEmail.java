package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ServiceEmail {

    public static void sendMail(String emailOwner, String emailPerson, String phoneNumberPerson, String id) {

        final String username = "technowblog01@gmail.com";
        final String password = "@@@@@@@"; //"password per app" per bypassare controllo sicurezza gmail

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("technowblog01@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailOwner)
            );
            message.setSubject("Notify changes of person");
            message.setText("Dear Mail Owner,"
                    + "\n\n modify person with id "+id+"!\n\n"
                    +"new email: "+emailPerson+"\n\n"
                    +"new phoneNumber: "+phoneNumberPerson+"\n\n");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}