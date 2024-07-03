package com.test.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.demo.model.BorrowingRecord;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord, Long> {
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId")
    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);

}
