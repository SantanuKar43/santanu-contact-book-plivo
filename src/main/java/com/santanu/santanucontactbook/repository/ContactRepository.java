package com.santanu.santanucontactbook.repository;

import com.santanu.santanucontactbook.model.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    public List<Contact> findByName(String name, Pageable pageable);
    public Contact findByEmail(String email);
    public void deleteByEmail(String email);
}
