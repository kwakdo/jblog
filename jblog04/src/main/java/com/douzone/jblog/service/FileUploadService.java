package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String RESTORE_PATH = "/jblog-upload";
	private static String URL_BASE = "/assets/images";

	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		try {
		
			if(multipartFile.isEmpty()) {
				return null;
			}
			
			File restireDirectory = new File(RESTORE_PATH);
			if(!restireDirectory.exists()) {
				restireDirectory.mkdirs();
			}
			
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring( originFileName.lastIndexOf('.')+1);
			String restoreFileName = generateSaveFilename(extName);
			Long fileSize = multipartFile.getSize();
		
			System.out.println("#########" + originFileName);
			System.out.println("#########" + restoreFileName);
			System.out.println("#########" + fileSize);
		
		
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(RESTORE_PATH + "/" + restoreFileName);
			os.write(data);
			os.close();
	
			url = URL_BASE + "/" + restoreFileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}



}
