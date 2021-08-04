package com.rj.library.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rj.library.shopping.models.Book;

@Repository
public interface IBookRepository extends MongoRepository<Book, String>{

}
