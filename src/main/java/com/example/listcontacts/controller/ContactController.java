package com.example.listcontacts.controller;

import com.example.listcontacts.Contact;
import com.example.listcontacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.findAll());

        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("action", "create");

        return "createEdit";
    }

    @PostMapping("/contact/create")
    public String addContact(@ModelAttribute Contact contact) {
        contactService.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);

        if (contact != null) {
            model.addAttribute("contact", contact);
            model.addAttribute("action", "edit");
            return "createEdit";
        }

        return "redirect:/";
    }

    @PostMapping("/contact/edit")
    public String editContact(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);

        return "redirect:/";
    }
    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);

        return "redirect:/";
    }
}
