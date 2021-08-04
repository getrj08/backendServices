package com.rj.library.shopping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.library.shopping.models.Book;
import com.rj.library.shopping.repository.IBookRepository;

@Service
public class BookService implements IBookService{
	
	@Autowired
	private IBookRepository bookRepository;

	@Override
	public List<Book> getBooks() {
		List<Book> allBooks = bookRepository.findAll();
		List<Book> toSend = new ArrayList<>();
		for(Book b : allBooks) {
			if(b.getIsbn() != null  && 
					b.getTitle() != null && 
					b.getThumbnailUrl() != null && 
					b.getShortDescription() != null && 
					b.getShortDescription().length() < 500) 
			{
				toSend.add(b);
				if(toSend.size()==50) break;
			}
		}
		return toSend;
	}

	@Override
	public String saveBooks(List<Book> books) {
		List<Book> booksStored = bookRepository.saveAll(books);
		return "saved total " + booksStored.size();
		
	}

	@Override
	public String saveBook(Book book) {
		Book bookSaved = bookRepository.save(book);
		return "saved book with id "+ bookSaved.getIsbn();
	}

	@Override
	public String savePrice() {
		List<Book> allbooks = bookRepository.findAll();
		List<Integer> priceList = IntStream.rangeClosed(500, 5000).boxed().collect(Collectors.toList());
		Random rand = new Random();
		List<Book> updatedBooksPrice = new ArrayList<>();
		for(Book b : allbooks) {
			int price = priceList.get(rand.nextInt(priceList.size()));
			b.setPrice(price);
			updatedBooksPrice.add(b);
			
		}
		bookRepository.saveAll(updatedBooksPrice);
		return "updated price of " + updatedBooksPrice.size();
	}

	@Override
	public Map<String,Object> getBookForIds(List<String> ids) {
		Map<String, Long> bookCounts = ids.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Iterable<Book> books = bookRepository.findAllById(ids);
		List<Book> booksToReturn = new ArrayList<>();
		int total = 0;
		int totalItem = 0;
		books.forEach(book -> {
			booksToReturn.add(book);
		});
		
		for(Book b : booksToReturn) {
			
			b.setCount(bookCounts.get(b.getIsbn()).intValue());
			totalItem += b.getCount();
			total += b.getPrice()*b.getCount();
		}
		
		Map<String,Object> booksMap = new HashMap<>();
		booksMap.put("books" , booksToReturn);
		booksMap.put("total" , total);
		booksMap.put("totalItem" , totalItem);
		
		return booksMap;
	}

	@Override
	public List<Book> searchBooks(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
