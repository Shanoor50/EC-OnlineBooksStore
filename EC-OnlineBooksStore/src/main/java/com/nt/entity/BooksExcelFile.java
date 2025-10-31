package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BooksExcelFile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Title")
	private String Title; 
	
	@Column(name = "Author")
	private String Author;
	
	@Column(name = "Genre")
	private String Genre;
	
	@Column(name = "Year")
	private Integer  Year;
	
	@Column(name = "Price")
	private Double  Price;
	
	
	
}
