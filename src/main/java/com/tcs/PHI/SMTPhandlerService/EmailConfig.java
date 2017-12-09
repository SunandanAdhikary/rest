package com.tcs.PHI.SMTPhandlerService;

import java.io.IOException;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;


public class EmailConfig {
	
	private   final Logger log=LoggerFactory.getLogger(EmailConfig.class);
	private final String AWS_ACCESSKEYID="";
	private final String AWS_ACCESSKEY="";
	  
	  	 
	  // IMPORTANT: Ensure that the region selected below is the one in which your identities are verified.  
	  private   Regions AWS_REGION = Regions.DEFAULT_REGION;           // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
	                                                                   // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
	                                                                   // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
	                                                                   // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
	                                                                   // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
	  
	  
	  private   String EMAIL_SUBJECT   ;
	  private   String EMAIL_BODY_TEXT ;
	  private String EMAIL_BODY_MIMETYPE = "text/HTML";
	  private String EMAIL_FILE_MIMETYPE ;
	  private AWSCredentials credentials;
	  private   String EMAIL_FROM    ;
	  private   String EMAIL_REPLY_TO  ;  // Replace with the address replies should go to. This address must be verified with Amazon SES. 
	  private   String EMAIL_RECIPIENT ; // Replace with a recipient address. If your account is still in the sandbox,
	                                                                   // this address must be verified with Amazon SES.  
	  private   String EMAIL_ATTACHMENTS ;        
	  private int EMAIL_PORT;
	  private String EMAIL_HOST;

	  
	public EmailConfig(String filename) {
		try {
			//AmazonSimpleEmailService client = new AmazonSimpleEmailServiceClient(credentials);
			this.credentials=new BasicAWSCredentials(this.AWS_ACCESSKEYID, this.AWS_ACCESSKEY) ;
			Properties props=PropertiesLoaderUtils.loadProperties(new ClassPathResource(filename));
			this.EMAIL_BODY_MIMETYPE = "text/HTML";
			  /*this.EMAIL_SUBJECT  ;
			  this.EMAIL_BODY_TEXT ;
			  
			  this.EMAIL_FILE_MIMETYPE ;
			  this.String EMAIL_FROM    ;
			  private   String EMAIL_REPLY_TO  ;
			  private   String EMAIL_RECIPIENT ;
			  private   String EMAIL_ATTACHMENTS ;        
			  */
			this.EMAIL_HOST = props.getProperty("HOST").toString();
			this.EMAIL_PORT = Integer.parseInt(props.getProperty("PORT"));
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
	
	  String getEMAIL_FROM() {
		return EMAIL_FROM;
	}
	  void setEMAIL_FROM(String eMAIL_FROM) {
		EMAIL_FROM = eMAIL_FROM;
	}
	  String getEMAIL_REPLY_TO() {
		return EMAIL_REPLY_TO;
	}
	  void setEMAIL_REPLY_TO(String eMAIL_REPLY_TO) {
		EMAIL_REPLY_TO = eMAIL_REPLY_TO;
	}
	  String getEMAIL_RECIPIENT() {
		return EMAIL_RECIPIENT;
	}
	  void setEMAIL_RECIPIENT(String eMAIL_RECIPIENT) {
		EMAIL_RECIPIENT = eMAIL_RECIPIENT;
	}
	  String getEMAIL_ATTACHMENTS() {
		return EMAIL_ATTACHMENTS;
	}
	  void setEMAIL_ATTACHMENTS(String eMAIL_ATTACHMENTS) {
		EMAIL_ATTACHMENTS = eMAIL_ATTACHMENTS;
	}
	  String getEMAIL_SUBJECT() {
		return EMAIL_SUBJECT;
	}
	  void setEMAIL_SUBJECT(String eMAIL_SUBJECT) {
		EMAIL_SUBJECT = eMAIL_SUBJECT;
	}
	  String getEMAIL_BODY_TEXT() {
		return EMAIL_BODY_TEXT;
	}
	  void setEMAIL_BODY_TEXT(String eMAIL_BODY_TEXT) {
		EMAIL_BODY_TEXT = eMAIL_BODY_TEXT;
	}
	String getEMAIL_BODY_MIMETYPE() {
		return EMAIL_BODY_MIMETYPE;
	}
	void setEMAIL_BODY_MIMETYPE(String eMAIL_BODY_MIMETYPE) {
		EMAIL_BODY_MIMETYPE = eMAIL_BODY_MIMETYPE;
	}
	String getEMAIL_FILE_MIMETYPE() {
		return EMAIL_FILE_MIMETYPE;
	}
	void setEMAIL_FILE_MIMETYPE(String eMAIL_FILE_MIMETYPE) {
		EMAIL_FILE_MIMETYPE = eMAIL_FILE_MIMETYPE;
	}
	int getEMAIL_PORT() {
		return EMAIL_PORT;
	}
	void setEMAIL_PORT(int eMAIL_PORT) {
		EMAIL_PORT = eMAIL_PORT;
	}
	String getEMAIL_HOST() {
		return EMAIL_HOST;
	}
	void setEMAIL_HOST(String eMAIL_HOST) {
		EMAIL_HOST = eMAIL_HOST;
	}
	Regions getAWS_REGION() {
		return AWS_REGION;
	}
	void setAWS_REGION(Regions aWS_REGION) {
		AWS_REGION = aWS_REGION;
	}

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(AWSCredentials credentials) {
		this.credentials = credentials;
	}
}
