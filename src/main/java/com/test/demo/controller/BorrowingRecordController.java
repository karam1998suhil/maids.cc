
package com.test.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.demo.model.BorrowingRecord;
import com.test.demo.repo.BorrowingRecordRepo;
import com.test.demo.service.BorrowingRecordService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private BorrowingRecordRepo borrowingRecordRepo;

 

    @PostMapping("/borrow/{bookid}/patron/{patronid}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookid, @PathVariable Long patronid) {
        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookid, patronid);
            return new ResponseEntity<>(borrowingRecord, HttpStatus.CREATED); // Use CREATED for successful creation
        } catch (Exception e) {
            throw new RuntimeException("Failed to borrow book: " + e.getMessage());
        }
    }

    @PutMapping("/return/{bookid}/patron/{patronid}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookid, @PathVariable Long patronid) {
        try {
            BorrowingRecord record = borrowingRecordService.findBorrowingRecordByBookIdAndPatronId(bookid, patronid);
            if (record != null) {
                record.setReturnDate(new java.sql.Date(new Date().getTime()));

                return new ResponseEntity<>(borrowingRecordRepo.save(record), HttpStatus.CREATED); // Use CREATED for successful creation

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to borrow book: " + e.getMessage());
        }
    }
    
}
