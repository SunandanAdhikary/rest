package com.tcs.PHI.SMTPhandlerService;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.tcs.PHI.APIhandlerService.ServiceBean;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;

public class EmailHandler {
	private static final Logger log= LoggerFactory.getLogger(EmailHandler.class);
	
	private EmailConfig config;
	private Transport AWSTransport;
	private Session AWSsession;
	
	public EmailHandler(String filename) {
		config= new EmailConfig(filename);
		Properties props= new Properties();
		 props.setProperty("mail.transport.protocol", "aws");
	     props.setProperty("mail.aws.user", config.getCredentials().getAWSAccessKeyId());
	     props.setProperty("mail.aws.password", config.getCredentials().getAWSSecretKey());
	     props.put("mail.smtp.host", config.getEMAIL_HOST());
	     props.put("mail.smtp.port", config.getEMAIL_PORT());
		 props.put("mail.smtp.ssl.enable", "true");
		 props.put("mail.smtp.auth", "true");	     
	     
		 
		 AWSsession = Session.getInstance(props);
	        AWSTransport = new AWSJavaMailTransport(AWSsession, null);
	        try {
				AWSTransport.connect();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				log.error(e.getLocalizedMessage());;
			}
	     
	}

	
	public void sendEmail() throws AddressException, MessagingException{
		
		MimeMessage message= new MimeMessage(this.AWSsession);
		message.setSubject(config.getEMAIL_SUBJECT(), "UTF-8");

        message.setFrom(new InternetAddress(config.getEMAIL_FROM()));
        message.setReplyTo(new Address[]{new InternetAddress(config.getEMAIL_REPLY_TO())});
        message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(config.getEMAIL_RECIPIENT()));
        
        Multipart parts=new MimeMultipart();
        //add body
        BodyPart body= new MimeBodyPart();
        body.setContent(config.getEMAIL_BODY_TEXT(),config.getEMAIL_BODY_MIMETYPE());
        parts.addBodyPart(body);
        
        String[] attachmentsFiles = new String[]{config.getEMAIL_ATTACHMENTS() };

            for (String attachmentFileName : attachmentsFiles) {

                MimeBodyPart attachment = new MimeBodyPart();

                DataSource fds = new FileDataSource(attachmentFileName);
                attachment.setDataHandler(new DataHandler(fds));
                attachment.setFileName(fds.getName());
                //adding attachment
                parts.addBodyPart(attachment);
            }
        
           this.AWSTransport.sendMessage(message, null);
            this.AWSTransport.close();
		
	}
}
