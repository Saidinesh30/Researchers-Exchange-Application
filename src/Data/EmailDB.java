package Data;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


public class EmailDB {


	public static void emailRecommendTrigger(String name, String senderMail,  String friendMail, String messageTxt, HttpServletRequest request,String token){
		final String username = "unccnbadbhavyaassignment3@gmail.com";
		final String password = "bhavya@123";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress(senderMail);
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("New Recommendation");
			message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nYou have been recommended for "+name+".\n\n"
					+"Follow this link to create your account  "+request.getRequestURL()+"?"+"recommended="+senderMail+".\n\n"
					+ "\n\nRegards,\n" + name + "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static void emailContactTrigger(String name, String friendMail, String messageTxt){
		final String username = "unccnbadbhavyaassignment3@gmail.com";
		final String password = "bhavya@123";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			InternetAddress me = new InternetAddress("unccnbadbhavyaassignment3@gmail.com");
		         try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("New Contact");
			message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nYou have been added as a contact for "+name+".\n\n"
					+"Message:  "+messageTxt
					+ "\n\nRegards,\n" + name + "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        public static void emailActivationTrigger(HttpServletRequest request,String friendMail,String token){
		final String username = "unccnbadbhavyaassignment3@gmail.com";
		final String password = "bhavya@123";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			InternetAddress me = new InternetAddress("unccnbadbhavyaassignment3@gmail.com");
		        
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Activate account");
			message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nFollow the link to activate your account "+request.getRequestURL()+"?"+"token="+token+".\n\n"
					
					+ "\n\nRegards,\n" +"Admin"+ "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        public static void passwordResetTrigger(HttpServletRequest request,String friendMail,String token){
		final String username = "unccnbadbhavyaassignment3@gmail.com";
		final String password = "bhavya@123";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			InternetAddress me = new InternetAddress("unccnbadbhavyaassignment3@gmail.com");
		        
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Password Reset");
			message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nFollow the link to reset your password: "+request.getRequestURL()+"?"+"reset="+token+".\n\n"
					
					+ "\n\nRegards,\n" +"Admin"+ "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
}
