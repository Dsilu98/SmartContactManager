package com.smart.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	private static final String UPLOAD_DIR ="contact-photos/";
	
	public static String saveFile(MultipartFile file) throws IOException{
		if(file.isEmpty()) {
			return null;
		}
		
		//create directory if not exist
		File uploadDir = new File(UPLOAD_DIR);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//save file
		 String fileName = System.currentTimeMillis()+ "_"+file.getOriginalFilename();
		 Path filePath = Paths.get(UPLOAD_DIR, fileName);
		 Files.write(filePath, file.getBytes());
		 
		 return fileName;
	}
}
