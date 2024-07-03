package com.test.demo.service;

import org.springframework.stereotype.Service;

import com.test.demo.model.Book;
import com.test.demo.model.BorrowingRecord;
import com.test.demo.model.Patron;
import com.test.demo.repo.BookRepo;
import com.test.demo.repo.BorrowingRecordRepo;
import com.test.demo.repo.PatronRepo;

import java.util.Date;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepo borrowingRecordRepo;
    private final BookRepo bookRepo;
    private final PatronRepo patronRepo;

    public BorrowingRecordService(BorrowingRecordRepo borrowingRecordRepo, BookRepo bookRepo, PatronRepo patronRepo) {
        this.borrowingRecordRepo = borrowingRecordRepo;
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
    }

    public BorrowingRecord borrowBook(long bookid, long patronid) {
        Book book = bookRepo.findById( bookid).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepo.findById(patronid).orElseThrow(() -> new RuntimeException("Patron not found"));
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(new java.sql.Date(new Date().getTime()));

        return borrowingRecordRepo.save(borrowingRecord);
    }

    public BorrowingRecord returnBookRec(Long bookid, Long patronid) {
        return null;
    }
    public BorrowingRecord findBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId) {
        return borrowingRecordRepo.findByBookIdAndPatronId(bookId, patronId);
    }
}
