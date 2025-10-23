package com.nt.Iservice;

import java.util.List;

import com.nt.entity.BookModule;

public interface IBookService {
	
	public BookModule customerSaveBooks(BookModule bookModule);
	
	public List<BookModule> customerGetAllBooks();
	
	public BookModule getCustomerBookById(long id);

}
