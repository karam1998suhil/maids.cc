package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.demo.handler.ResourceNotFoundException;
import com.test.demo.model.Patron;
import com.test.demo.repo.PatronRepo;

@RestController
@RequestMapping("/api/patrons") 
public class PatronController {

    @Autowired
    private PatronRepo patronRepo;

    @GetMapping("/")
    @Cacheable(value = "patronsCache") 
    public ResponseEntity<List<Patron>> getAllPatrons() {
        try {
            List<Patron> patronList = new ArrayList<>();
            patronRepo.findAll().forEach(patronList::add);
            if (patronList.isEmpty()) {
                return new ResponseEntity<>(patronList, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patronList, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch patrons: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patronData = patronRepo.findById(id);
        return patronData.map(patron -> new ResponseEntity<>(patron, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    @PostMapping("/")
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        Patron patronObj = patronRepo.save(patron);
        return new ResponseEntity<>(patronObj, HttpStatus.CREATED); // Use CREATED for successful creation
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatronById(@PathVariable Long id, @RequestBody Patron newPatronData) {
        Optional<Patron> oldPatronData = patronRepo.findById(id);
        if (oldPatronData.isPresent()) {
            Patron updatedPatron = oldPatronData.get();
            updatedPatron.setName(newPatronData.getName());
            updatedPatron.setContact(newPatronData.getContact());
            Patron patronObj = patronRepo.save(updatedPatron);
            return new ResponseEntity<>(patronObj, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Patron not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatronById(@PathVariable Long id) {
        try {
            patronRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete patron: " + e.getMessage());
        }
    }
}
