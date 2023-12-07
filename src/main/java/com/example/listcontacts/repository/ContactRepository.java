package com.example.listcontacts.repository;

import com.example.listcontacts.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContact(Long id);

    void batchInsert(List<Contact> contacts);
}
