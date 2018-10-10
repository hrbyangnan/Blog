package Security;


import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ResetPassword {

    public static String myEmailAccount = "15261851370@163.com";
    public static String myEmailPassword = "Li221243221200";

    public static String myEmailSMTPHost = "smtp.163.com";


    public static String receiveMailAccount = "531309997@qq.com";

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);


        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);


        Transport transport = session.getTransport();


        transport.connect(myEmailAccount, myEmailPassword);


        transport.sendMessage(message, message.getAllRecipients());


        transport.close();
    }


    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {

        MimeMessage message = new MimeMessage(session);


        message.setFrom(new InternetAddress(sendMail, "Blog Security Team", "UTF-8"));


        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Blog user", "UTF-8"));


        message.setSubject("Reset Passowrd", "UTF-8");


        message.setContent("Reset Passowrd, URL:", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }


}


