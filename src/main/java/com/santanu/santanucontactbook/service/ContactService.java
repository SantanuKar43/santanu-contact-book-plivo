package com.santanu.santanucontactbook.service;

import com.santanu.santanucontactbook.model.Contact;
import com.santanu.santanucontactbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public Contact findContactByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Boolean saveContact(Contact contact) { repository.save(contact); return true; }

    public List<Contact> findContactByName(String name, int start, int size) {
        Pageable pageable = PageRequest.of(start, size);
        return repository.findByNameContaining(name, pageable);
    }

    @Transactional
    public Boolean deleteContactByEmail(String email) {
        repository.deleteByEmail(email);
        return true;
    }

    @Transactional
    public Boolean deleteContactById(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Transactional
    public Boolean updateContactByEmail(String email, Long phone, String name) {
        Contact contact = repository.findByEmail(email);
        contact.updateFrom(name, phone);
        repository.save(contact);
        return true;
    }

    public Boolean updateContactById(Long id, String email, Long phone, String name) {
        Contact contact = repository.findById(id).orElse(null);
        if(Objects.nonNull(contact)) {
            contact.updateFrom(name, phone);
            if(!email.equals("")) contact.setEmail(email);
            repository.save(contact);
            return true;
        }
        return false;
    }
}
