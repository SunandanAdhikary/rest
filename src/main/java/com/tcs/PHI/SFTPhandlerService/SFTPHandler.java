package com.tcs.PHI.SFTPhandlerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Component
public class SFTPHandler {
	
	private static final Logger log= LoggerFactory.getLogger(SFTPHandler.class);
	
	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;
	@Autowired
	private SFTPConfig config;
	@Autowired
	JSch jsch = new JSch();
	java.util.Properties prop = new java.util.Properties();
	 
	
	/**
	 * Constructor
	 * @param configFilePath
	 */
	public SFTPHandler(String configFilePath) {
		this.config=new SFTPConfig(configFilePath);
		this.prop.put("StrictHostKeyChecking", "no");
		
		//setting host port  userid password
		 try {
			this.session = jsch.getSession(config.getSFTPUSER(), config.getSFTPHOST(), config.getSFTPPORT());
		} catch (JSchException e) {
			log.error(e.getLocalizedMessage());
		}
         this.session.setPassword(config.getSFTPPASS());
		
	}
	
	

/**
 * method to upload files via sftp
 * @param storeId String type
 * @param anyFile File type
 */
public void sendFile(String storeId, File anyFile) {
		
		
        try {
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channel = session.openChannel("sftp");
			channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(config.getSFTPWORKINGDIRFORUPLOAD());
            channelSftp.put(new FileInputStream(anyFile), anyFile.getName());
           log.debug("Zip file has been SFTP'ed successfully.");
           
        } catch (JSchException e) {
			
        	log.error(e.getLocalizedMessage());
        	
		} catch (FileNotFoundException e) {
			
			log.error(e.getLocalizedMessage());
			
		} catch (SftpException e) {
			
			log.error(e.getLocalizedMessage());
		}
        finally{
            channel.disconnect();
            session.disconnect();
            }
    }

/**
 * method to download files via sftp
 * @param filename String type
 * @param localFullFilePathName File type
 */

public void receiveFile(String filename, String localFullFilePathName) 
{
        try
        {
            session.setConfig(prop);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(config.getSFTPWORKINGDIRFORDOWNLOAD());
            channelSftp.get(filename, localFullFilePathName);
 
           log.debug("File : "+ filename + " downloaded successfully.");
        
		} catch (JSchException e) {
	
	log.error(e.getLocalizedMessage());
	
		} catch (SftpException e) {
	
	log.error(e.getLocalizedMessage());
		}	
        finally
        {
        channel.disconnect();
        session.disconnect();
        }
    }


}
