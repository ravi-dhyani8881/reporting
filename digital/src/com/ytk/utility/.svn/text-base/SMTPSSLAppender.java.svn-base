package com.ytk.utility;

/*
 * ravi Dhyani
 */

import java.security.Security;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.apache.log4j.net.SMTPAppender;

public class SMTPSSLAppender extends SMTPAppender {

	public SMTPSSLAppender() {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	}

	@Override
	protected Session createSession() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", "192.168.0.7");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.socketFactory.port", "25");
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.quitwait", "false");
    
		
		
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication(getSMTPUsername(),getSMTPPassword());	}
		});		

		return session;
	}
}
