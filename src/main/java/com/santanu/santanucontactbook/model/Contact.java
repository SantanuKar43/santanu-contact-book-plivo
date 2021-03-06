package com.santanu.santanucontactbook.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@DynamicUpdate
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private Long phone;

    public Contact() {
    }

    public Contact(String name, String email, Long phone) {
        this(null, name, email, phone);
    }

    public Contact(Long id, String name, String email, Long phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void updateFrom(String name, Long phone) {
        if(null != name && !name.equals("")) this.name = name;
        if(null != phone) this.phone = phone;
    }

    @Override
    public boolean equals(Object contact1) {
        if(!(contact1 instanceof Contact)) return false;
        Contact contact = (Contact) contact1;
        return this.name.equals(contact.getName())
                && this.email.equals(contact.getEmail())
                && this.getPhone().equals(contact.getPhone());
    }
}
