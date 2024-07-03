package com.test.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patron") // Optional: If table name is different from class name
public class Patron {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
       
        @NotBlank(message = "Name is required")
        @Column(name = "name")
        private String name;

        @NotBlank(message = "Contact is required")
        @Column(name = "contact")
        private String contact;
    
      
        // Getters and setters
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getContact() {
            return contact;
        }
    
        public void setContact(String contact) {
            this.contact = contact;
        }
    
      
    
    }