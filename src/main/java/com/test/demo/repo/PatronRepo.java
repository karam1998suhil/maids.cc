package com.test.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.demo.model.Patron;

public interface PatronRepo extends JpaRepository<Patron, Long> {
    // Custom query methods can be added here if needed
}

