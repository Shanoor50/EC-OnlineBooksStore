package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books") // it is optional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@CreationTimestamp
	@Column(name="createdDate",updatable=false)
	public LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name="updatedDate")
	public LocalDateTime updatedDate;

}
