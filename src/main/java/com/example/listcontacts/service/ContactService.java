package com.example.listcontacts.service;

import com.example.listcontacts.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(Long id);

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContact(Long id);

    void batchInsert(List<Contact> contacts);
}
