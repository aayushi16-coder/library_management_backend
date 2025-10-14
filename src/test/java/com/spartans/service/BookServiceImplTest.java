package com.spartans.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import com.spartans.model.Book;
import com.spartans.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Spring
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Dummy book object
        book = new Book(1L, "Harry Potter", "J.K. Rowling", "Fantasy",
                "123456789", "image.jpg", "Bloomsbury", LocalDate.of(2000, 7, 8),
                500.0, LocalDateTime.now(), 10, "YES");
    }

    @Test
    void testGetBookDetails_BookFound() {
        // Mock repository behavior
        when(bookRepository.findByBookTitleIgnoreCase("Harry Potter")).thenReturn(book);

        Map<String, Object> result = bookService.getBookDetails("Harry Potter");

        assertEquals("Harry Potter", result.get("Title"));
        assertEquals("J.K. Rowling", result.get("Author"));
        assertEquals("Fantasy", result.get("Category"));
        assertEquals("Available", result.get("Availability"));
    }

    @Test
    void testGetBookDetails_BookNotFound() {
        when(bookRepository.findByBookTitleIgnoreCase("Unknown")).thenReturn(null);

        Map<String, Object> result = bookService.getBookDetails("Unknown");

        assertTrue(result.containsKey("Message"));
        assertEquals("No book found with title: Unknown", result.get("Message"));
    }
}


