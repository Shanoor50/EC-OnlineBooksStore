package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.BookModule;

@Repository
public interface BooksModuleRepo extends JpaRepository<BookModule, Long> {

}
