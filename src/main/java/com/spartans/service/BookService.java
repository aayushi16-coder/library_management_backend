package com.spartans.service;

import com.spartans.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Map<String, Object> getBookDetails(String bookTitle);
}