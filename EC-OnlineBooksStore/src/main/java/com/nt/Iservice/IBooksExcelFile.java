package com.nt.Iservice;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IBooksExcelFile {
	
	void uploadExcelintoDB(MultipartFile file) throws IOException;

}
