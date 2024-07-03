package com.test.demo.controllerTest;

import com.test.demo.controller.BookController;
import com.test.demo.model.Book;
import com.test.demo.repo.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookControllerTests {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book());
        mockBooks.add(new Book());

        when(bookRepo.findAll()).thenReturn(mockBooks);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    public void testGetBookById() {
        Book mockBook = new Book();
        when(bookRepo.findById(1L)).thenReturn(Optional.of(mockBook));

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    public void testAddBook() throws Exception {
        Book newBook = new Book();
        Book savedBook = new Book();

        when(bookRepo.save(any(Book.class))).thenReturn(savedBook);

        ResponseEntity<Book> response = bookController.addBook(newBook);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedBook, response.getBody());
    }

    @Test
    public void testUpdateBookById() {
        Long id = 1L;
        Book existingBook = new Book();
        Book updatedBookData = new Book();  
    
        when(bookRepo.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepo.save(any(Book.class))).thenReturn(updatedBookData);
    
        ResponseEntity<Book> response = bookController.updateBookById(id, updatedBookData);
    
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookData, response.getBody());
    }
    @Test
    public void testDeleteBookById() {
        Long id = 2L;

        ResponseEntity<HttpStatus> response = bookController.deleteBookById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookRepo, times(1)).deleteById(id);
    }
}
