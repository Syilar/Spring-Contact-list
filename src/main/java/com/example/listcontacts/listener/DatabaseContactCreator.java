package com.example.listcontacts.listener;

import com.example.listcontacts.Contact;
import com.example.listcontacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseContactCreator {

    private final ContactService contactService;

    @EventListener(ApplicationStartedEvent.class)
    public void createContactData() {
        List<Contact> contactList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int value = i + 1;
            Contact contact = new Contact();
            contact.setId((long) value);
            contact.setFirstName("Name " + value);
            contact.setLastName("Last name " + value);
            contact.setEmail(value + "@log.com");
            contact.setPhone(value + "7000");

            contactList.add(contact);
        }
        contactService.batchInsert(contactList);
    }
}
