package com.lgdisplay.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MultipartMail {
	private static Transport transport;

	public static void main(String[] args){
		Properties props = new Properties();
		props.setProperty("mailtransport", "smtp");
		props.setProperty("mail.host", "gwapkumi01.lgdisplay.com");
		
		Session mailsession = Session.getDefaultInstance(props, null);
		mailsession.setDebug(true);
		try {
			transport = mailsession.getTransport();
			
			MimeMessage message = new MimeMessage(mailsession);
			message.setSubject("HTML Mail with images ÇÑ±Û","UTF-8");
			message.setFrom(new InternetAddress("admin@lgdisplay.com"));
			message.setContent("<h1>This is a test</h1>"
					+ "<img src=\"http://s2o.lgdisplay.com:8080/img/btn/Title01.gif\">", "text/html; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("ickjae@lgdpartner.com"));
			
			 //
	        // This HTML mail have to 2 part, the BODY and the embedded image
	        //
	        MimeMultipart multipart = new MimeMultipart("related");

	        // first part  (the html)
	        BodyPart messageBodyPart = new MimeBodyPart();
	        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
	        messageBodyPart.setContent(htmlText, "text/html");

	        // add it
	        multipart.addBodyPart(messageBodyPart);
	        
	        // second part (the image)
	        messageBodyPart = new MimeBodyPart();
	        DataSource fds = new FileDataSource
	          ("C:\\img\\3d_windows_7-wide.jpg");
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID","<image>");

	        // add it
	        multipart.addBodyPart(messageBodyPart);

	        // put everything together
	        message.setContent(multipart);


			
			
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
