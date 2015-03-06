package com.lgdisplay.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LinkImageMail {
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
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
