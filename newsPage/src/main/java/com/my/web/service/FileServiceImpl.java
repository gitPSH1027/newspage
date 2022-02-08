package com.my.web.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private String saveDir;
	
	@Override
	public String MemberfileUpload(MultipartFile file) throws Exception {
		String originFileName = file.getOriginalFilename(); //파일이름
		
		//파일이름이 있을경우
		if(!originFileName.equals(""))
		{
			//시스템시간 + 파일이름 
			String profile = System.currentTimeMillis() + "_" + originFileName;
			//업로드 경로, 파일명
			File f = new File(saveDir, profile);
			file.transferTo(f);
			return profile;
		}
		//파일이름이 공백일경우
		else
		{
			return "";
		}
	}

}
