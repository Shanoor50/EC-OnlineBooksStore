package com.nt.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Iservice.IBookService;
import com.nt.entity.BookModule;
import com.nt.exception.BookIdNotFoundException;
import com.nt.repository.BooksModuleRepo;

import org.springframework.cache.annotation.Cacheable;


@Service
public class BooksModuleImpl implements IBookService {
	
	@Autowired BooksModuleRepo booksModuleRepo;

	@Override
	public BookModule customerSaveBooks(BookModule bookModule) {
		BookModule booksave = booksModuleRepo.save(bookModule);
		return booksave;
	}

	@Override
	public List<BookModule> customerGetAllBooks() {
		List<BookModule> allbooks = booksModuleRepo.findAll();
		return allbooks;
	}

	@Override
	@Cacheable(cacheNames="BookCache",key="#id")
	public BookModule getCustomerBookById(long id) {
		Optional<BookModule> bookId = booksModuleRepo.findById(id);
		if(!bookId.isPresent()) {
			throw new BookIdNotFoundException("Book Id Is Not Available");
		}
		int a=0;
		System.out.println("request hit to DB::"+ a++);
		return bookId.get();
	}

}
