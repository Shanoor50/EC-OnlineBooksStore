package com.nt.serviceImp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.Iservice.IBooksExcelFile;
import com.nt.entity.BooksExcelFile;
import com.nt.repository.BooksRepo;
import com.nt.utility.Helper;

@Service
public class BooksExcelFileImpl implements IBooksExcelFile {
	
	@Autowired BooksRepo booksRepo;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {
		List<BooksExcelFile> excelFileInsertDB = Helper.excelFileInsertDB(file.getInputStream());
		
		booksRepo.saveAll(excelFileInsertDB);
	}
	
	

}
