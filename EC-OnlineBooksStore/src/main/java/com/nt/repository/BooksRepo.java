package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.BooksExcelFile;

@Repository
public interface BooksRepo extends JpaRepository<BooksExcelFile, Long> {

}
