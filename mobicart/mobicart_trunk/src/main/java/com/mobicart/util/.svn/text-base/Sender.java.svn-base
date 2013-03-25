package com.mobicart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * helper class to send emails via templates
 * @author jasdeep.singh
 */
public class Sender {

	private static final Logger logger = LoggerFactory.getLogger( Sender.class);
	
	private JavaMailSender javaMailSender;
	
	private MimeMessage message;
	
	private HashMap<String, String> templateCache = new HashMap<String, String>();

	/**
	 * @return the sender
	 */
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		message = javaMailSender.createMimeMessage();

	}

	/**
	 * send mail
	 * 
	 * @param email
	 */

	public void sendMail1(EmailGenerator email) {

		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setFrom(email.getFromEmail());
			msg.setTo(email.getToEmail());
			msg.setSubject(email.getSubject());
			msg.setText(getEmailBody(email.getTemplateName(), email.getParam()));

		} catch (Exception e) {
			logger.debug("error ocuurred in email sending",e);
		}

		javaMailSender.send(msg);

	}

	/**
	 * Send mail
	 * 
	 * @param email
	 */
	public void sendMail(EmailGenerator email) {
		try {

			//System.out.println("mail being sent to : " + email.getToEmail());
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(email.getFromEmail());
			
			if (ResourceProperties.getString("appUrl").contains("localhost")) {				
				helper.setTo("siddhartha.bhatia@netsolutionsindia.com");				
				
			} else {				
				helper.setTo(email.getToEmail());
			}
			if(email.getBccEmail()!=null){
				helper.setBcc(email.getBccEmail());
			}
			if(email.getCcEmail()!=null){
			helper.setCc(email.getCcEmail());
			}
			
			helper.setSentDate(DateTimeUtils.getDateToday());
			helper.setSubject(email.getSubject());
			helper.setText(getEmailBody(email.getTemplateName(), email
					.getParam()), true);

			javaMailSender.send(message);
			
		} catch (Exception e) {
			logger.debug("error ocuurred in email sending",e);
		}
	}

	/**
	 * Send mail with an attachement
	 * 
	 * @param email
	 * @param filePathAndName
	 * @param filename
	 */

	public void sendAttachementMail(EmailGenerator email,
			String filePathAndName, String filename) {
		try {

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(email.getFromEmail());
			helper.setTo(email.getToEmail());
			// helper.setTo("harpreet.singh@netsol.local");
			helper.setSubject(email.getSubject());
			helper.setText(getEmailBody(email.getTemplateName(), email
					.getParam()), true);

			FileSystemResource res = new FileSystemResource(new File(
					filePathAndName));
			helper.addAttachment(filename, res);

			javaMailSender.send(message);

		} catch (Exception e) {
			logger.debug("error ocuurred in email sending",e);
		}
	}

	private String getEmailBody(String templateName, Map<?, ?> model)
			throws IOException {
		templateName = templateName.replaceAll("%20", " ");
		String emailBody = null;
		File file = null;
		emailBody = templateCache.get(templateName);
		if (emailBody == null) {
			try {
				file = new File(templateName + ".template");
				if (!file.exists()) {
					ClassPathResource resource = new ClassPathResource(
							templateName + ".template");
					file = resource.getFile();
				}
				FileInputStream fin = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				fin.read(bytes);
				emailBody = new String(bytes);
				// System.out.println("TEST:" + emailBody);
				templateCache.put(templateName, emailBody);
			} catch (Exception e) {
				logger.debug("error ocuurred in email sending",e);
			}

		}

		if (emailBody != null) {
			Iterator<?> itr = model.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String) itr.next();
				String value = (String) model.get(key);
				if(value == null){
					value = "NA";
				}
				emailBody = emailBody.replaceAll(key, value);
			}
		}

		
		return emailBody;
	}

}
