package com.test.demo.controllerTest;

import com.test.demo.model.Patron;
import com.test.demo.repo.PatronRepo;
import com.test.demo.controller.PatronController;
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

 
public class PatronControllerTests {

    

    @Mock
    private PatronRepo patronRepo;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

  
    @Test
    public void testGetAllPatron() {
        List<Patron> patronList = new ArrayList<>();
        patronList.add(new Patron());
        patronList.add(new Patron());

        when(patronRepo.findAll()).thenReturn(patronList);

        ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patronList, response.getBody());
    }

  
    @Test
    public void testGetPatronById() {
        Long patronId = 1L;
        Patron patron = new Patron();      
        when(patronRepo.findById(patronId)).thenReturn(Optional.of(patron));

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }
  

  
    @Test
    public void testAddPatron() throws Exception {
        Patron newpatron = new Patron();

        when(patronRepo.save(any(Patron.class))).thenReturn(newpatron);

        ResponseEntity<Patron> response = patronController.addPatron(newpatron);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newpatron, response.getBody());
    }
 
    @Test
    public void testUpdatePatronById() {
        Long patronId = 1L;
        Patron existingPatron = new Patron();
        Patron updatedPatron = new Patron();  
    
        when(patronRepo.findById(patronId)).thenReturn(Optional.of(existingPatron));
        when(patronRepo.save(any(Patron.class))).thenReturn(updatedPatron);
    
        ResponseEntity<Patron> response = patronController.updatePatronById(patronId, updatedPatron);
    
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }
  
    @Test
    public void testDeletePatronById() {
        Long patronId = 2L;

        ResponseEntity<HttpStatus> response = patronController.deletePatronById(patronId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patronRepo, times(1)).deleteById(patronId);
    }
   
}
