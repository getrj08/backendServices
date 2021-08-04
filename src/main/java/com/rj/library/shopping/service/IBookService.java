package com.rj.library.shopping.service;

import java.util.List;
import java.util.Map;

import com.rj.library.shopping.models.Book;

public interface IBookService {
	
	public List<Book> getBooks();
	public Map<String,Object> getBookForIds(List<String> ids);
	public String saveBooks(List<Book> books);
	public String saveBook(Book book);
	public String savePrice();
	
	public List<Book> searchBooks(String keyword);
	

}
