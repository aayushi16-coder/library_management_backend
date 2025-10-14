package com.spartans.controller;

import com.spartans.model.Book;
import com.spartans.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;



    @GetMapping("/list")
    public  ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping("/details")
    public ResponseEntity<?> getBookDetails(@RequestParam String title) {
        Map<String, Object> bookDetails = bookService.getBookDetails(title);


        if (bookDetails.containsKey("Message")) {
            return new ResponseEntity<>(bookDetails, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }
}
