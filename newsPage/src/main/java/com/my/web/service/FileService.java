package com.my.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String MemberfileUpload(MultipartFile file) throws Exception;
}
