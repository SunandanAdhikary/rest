package com.tcs.PHI.ZIPcreatorService;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipMaker {
	private static final Logger log= LoggerFactory.getLogger(ZipMaker.class);
	ZipOutputStream zout =null;
	
	
	/**
	 * Zipping files into a .zip
	 * @param fileList
	 * @param zipFileName
	 * @return zipFile 
	 */
	public File compressToZip(List<File> fileList, String zipFileName) {
		
		try {
			zout = new ZipOutputStream(new FileOutputStream(zipFileName));
			 fileList.stream().forEach(i->{
	            	
     			try {
					zout.putNextEntry(new ZipEntry(i.getName()));
					byte[] bytes = Files.readAllBytes(Paths.get(i.getAbsolutePath()));
         			zout.write(bytes, 0, bytes.length);
         			zout.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			 
		}catch (FileNotFoundException e1) {
			log.error(e1.getMessage());
		}finally{
			try{
			if(zout!=null)
				zout.close();
			}catch (IOException e) {
				log.error(e.getMessage());
			}
		}
    return new File(zipFileName);
	}
	


}
