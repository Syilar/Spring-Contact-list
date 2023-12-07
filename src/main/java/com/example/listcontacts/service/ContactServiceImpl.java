package com.example.listcontacts.service;

import com.example.listcontacts.Contact;
import com.example.listcontacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private  final ContactRepository contactRepository;
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.addContact(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactRepository.updateContact(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteContact(id);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        contactRepository.batchInsert(contacts);
    }
}
