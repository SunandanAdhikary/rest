package com.tcs.PHI.SFTPhandlerService;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
public class SFTPConfig {

	private static final Logger log= LoggerFactory.getLogger(SFTPConfig.class);
	
	private String SFTPHOST = null;
    private int SFTPPORT ;
    private String SFTPUSER = null;
    private String SFTPPASS = null;
    private String SFTPWORKINGDIRFORDOWNLOAD = null;
    private String SFTPWORKINGDIRFORUPLOAD = null;
    private Properties sftpProps;
    
    /**
     * @Constructor :set values from config property file to configure
     *  sftp handler to connect to certain sftp server
     * @param configFilePath String
     */
	public SFTPConfig(String configFilePath) {
		
		try {
			sftpProps = PropertiesLoaderUtils.loadProperties(new ClassPathResource(configFilePath));
		
		this.SFTPHOST = sftpProps.getProperty("sftp_host");
		this.SFTPPORT = Integer.parseInt(sftpProps.getProperty("sftp_port"));
		this.SFTPUSER = sftpProps.getProperty("sftp_user");
		this.SFTPPASS = sftpProps.getProperty("sftp_pwd");
		this.SFTPWORKINGDIRFORDOWNLOAD = sftpProps.getProperty("sftp_downloaddir");
		this.SFTPWORKINGDIRFORDOWNLOAD = sftpProps.getProperty("sftp_uploaddir");
		
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		
	}
	String getSFTPHOST() {
		return SFTPHOST;
	}
	void setSFTPHOST(String sFTPHOST) {
		SFTPHOST = sFTPHOST;
	}
	int getSFTPPORT() {
		return SFTPPORT;
	}
	void setSFTPPORT(int sFTPPORT) {
		SFTPPORT = sFTPPORT;
	}
	String getSFTPUSER() {
		return SFTPUSER;
	}
	void setSFTPUSER(String sFTPUSER) {
		SFTPUSER = sFTPUSER;
	}
	String getSFTPPASS() {
		return SFTPPASS;
	}
	void setSFTPPASS(String sFTPPASS) {
		SFTPPASS = sFTPPASS;
	}
	String getSFTPWORKINGDIRFORDOWNLOAD() {
		return SFTPWORKINGDIRFORDOWNLOAD;
	}
	void setSFTPWORKINGDIRFORDOWNLOAD(String sFTPWORKINGDIRFORDOWNLOAD) {
		SFTPWORKINGDIRFORDOWNLOAD = sFTPWORKINGDIRFORDOWNLOAD;
	}
	String getSFTPWORKINGDIRFORUPLOAD() {
		return SFTPWORKINGDIRFORUPLOAD;
	}
	void setSFTPWORKINGDIRFORUPLOAD(String sFTPWORKINGDIRFORUPLOAD) {
		SFTPWORKINGDIRFORUPLOAD = sFTPWORKINGDIRFORUPLOAD;
	}
	
	
}
