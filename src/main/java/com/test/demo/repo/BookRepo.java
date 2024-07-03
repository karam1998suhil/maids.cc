package com.test.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.model.Book;
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
