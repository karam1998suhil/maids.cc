package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.demo.model.Book;
import com.test.demo.repo.BookRepo;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/books") 
public class BookController {

    @Autowired
    private BookRepo bookRepo;
 
  

    @GetMapping("/")
    @Cacheable(value = "booksCache") 
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> bookList = new ArrayList<>();
            bookRepo.findAll().forEach(bookList::add);
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(bookList, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch books: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookData = bookRepo.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            throw new com.test.demo.handler.ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book bookObj = bookRepo.save(book);
            return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add book: " + e.getMessage());
        }
    }

   @PutMapping("/{id}")
public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData) {
    Optional<Book> oldBookData = bookRepo.findById(id);
    if (oldBookData.isPresent()) {
        Book updateBookData = oldBookData.get();
        updateBookData.setTitle(newBookData.getTitle());
        updateBookData.setIsbn(newBookData.getIsbn());
        updateBookData.setAuthor(newBookData.getAuthor());
        updateBookData.setpublicationYear(newBookData.getpublicationYear());

        Book bookObj = bookRepo.save(updateBookData);
        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        try {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete book: " + e.getMessage());
        }
    }
}
