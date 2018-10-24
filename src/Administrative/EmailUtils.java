package Administrative;

import pojo.User;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {

    private static final String FROM = "pgcgroup2@sina.com";
    private static final String PASSWORD = "aucklanduni";
    private static final String host = "smtp.sina.com";


    public static void sendResetPasswordEmail(User user) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("Reset your password of PGC blog");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setContent("Please click this link to reset your password:" + GenerateLinkUtils.generateResetPwdLink(user) + " cheers! ", "text/html;charset=utf-8");
            // send
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //todo connection
    public static Session getSession() {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.debug", "true");


//        Authenticator auth = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("pgcgroup2@sina.com", "aucklanduni");
//            }
//        };
//         Session session = Session.getInstance(props, auth);


        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(FROM, PASSWORD);
            }

        });
        return session;
    }
}