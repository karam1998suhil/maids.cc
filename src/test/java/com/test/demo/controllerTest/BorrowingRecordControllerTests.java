package com.test.demo.controllerTest;

import com.test.demo.controller.BorrowingRecordController;
import com.test.demo.model.Book;
import com.test.demo.model.BorrowingRecord;
import com.test.demo.model.Patron;
import com.test.demo.repo.BorrowingRecordRepo;
import com.test.demo.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BorrowingRecordControllerTests {

    @Mock
    private BorrowingRecordRepo borrowingRecordRepo;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testBorrowBook() {
        Long bookId = 1L;
        Long patronId = 1L;
        BorrowingRecord borrowingRecord = new BorrowingRecord();

        when(borrowingRecordService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    public void testReturnBook() {
        Long bookId = 1L;
        Long patronId = 1L;
        BorrowingRecord existingRecord = new BorrowingRecord();
        existingRecord.setId(1L);
        existingRecord.setBook(new Book());
        existingRecord.setPatron(new Patron());

        when(borrowingRecordService.findBorrowingRecordByBookIdAndPatronId(bookId, patronId)).thenReturn(existingRecord);
        when(borrowingRecordRepo.save(any(BorrowingRecord.class))).thenReturn(existingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(existingRecord, response.getBody());
        assertEquals(new java.sql.Date(new java.util.Date().getTime()), existingRecord.getReturnDate()); // Verify return date is set
    }
}
