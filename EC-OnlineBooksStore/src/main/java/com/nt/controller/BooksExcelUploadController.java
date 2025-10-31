package com.nt.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.Iservice.IBooksExcelFile;
import com.nt.model.ResponseMessage;
import com.nt.utility.Constants;
import com.nt.utility.Helper;

@RestController
public class BooksExcelUploadController {

	@Autowired IBooksExcelFile booksExcelSer;
	
	@PostMapping("/uploadExcelFile")
	public ResponseEntity<ResponseMessage> insertSheet(@RequestParam("file") MultipartFile file) throws IOException{
		
		if(Helper.checkExcelFile(file)) {
			booksExcelSer.uploadExcelintoDB(file);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Excelfile Save Successfully"));
		}else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Excelfile Fail To Save"));
		}
		
	}
}
