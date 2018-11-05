package com.santanu.santanucontactbook.controller;

import com.santanu.santanucontactbook.model.Contact;
import com.santanu.santanucontactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {
    @Autowired
    private ContactService contactService;
    @GetMapping("/search/by_name")
    public ResponseEntity<List<Contact>> getContactByName(@RequestParam String name,
                                                          @RequestParam(name = "start", value = "0") int start,
                                                          @RequestParam(name = "size", value = "10") int size) {
        try {
            return ResponseEntity.ok(contactService.findContactByName(name, start, size));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/search/by_email")
    public ResponseEntity<Contact> getContactByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(contactService.findContactByEmail(email));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Boolean> createContact(@RequestBody Contact contact) {
        try {
            return ResponseEntity.ok(contactService.saveContact(contact));
        } catch(RuntimeException re) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PutMapping("/update/by_email")
    public ResponseEntity<Boolean> updateContact(@RequestParam String email,
                                                 @RequestParam(name = "phone", value = "") String phone,
                                                 @RequestParam(name = "name", value = "") String name) {
        try {
            return ResponseEntity.ok(contactService.updateContact(email, phone, name));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/delete/by_email")
    public ResponseEntity<Boolean> deleteContact(@RequestParam String email) {
        try {
            return ResponseEntity.ok(contactService.deleteContactByEmail(email));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
