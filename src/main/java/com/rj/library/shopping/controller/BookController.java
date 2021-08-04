package com.rj.library.shopping.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.library.shopping.models.Book;
import com.rj.library.shopping.service.IBookService;

@RestController
@RequestMapping(value="/books")
@CrossOrigin
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		
		return ResponseEntity.ok().body(bookService.getBooks());
		
	}
	
	@PostMapping(value="/getIds")
	public ResponseEntity<Map<String,Object>> getBooksForIds(@RequestBody List<String> ids) {
		return ResponseEntity.ok().body(bookService.getBookForIds(ids));
	}
	
	@PostMapping(value="/all")
	public ResponseEntity<String> saveAllBooks(@RequestBody List<Book> books) {
		
		return ResponseEntity.ok().body(bookService.saveBooks(books));
		
	}
	
	@PutMapping(value="/save")
	public ResponseEntity<String> saveBook(@RequestBody Book book) {
		
		return ResponseEntity.ok().body(bookService.saveBook(book));
		
	}
	
	

}
