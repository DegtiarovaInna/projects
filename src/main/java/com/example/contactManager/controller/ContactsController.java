package com.example.contactManager.controller;

import com.example.contactManager.model.Contact;
import com.example.contactManager.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@Tag(name = "Contact'ы", description = "Описание")
public class ContactsController {
    @Autowired
    private ContactService contactService;

    @Operation(summary = "Получить контакт по ID", description = "Возвращает контакт по указанному идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение контакта"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Integer id) {
        Contact contact = contactService.getContact(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Создать новый контакт", description = "Создает новый контакт с указанными данными.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Контакт успешно создан") })
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact.getName(), contact.getEmail());
        return ResponseEntity.status(201).body(createdContact);
    }

    @Operation(summary = "Обновить контакт", description = "Обновляет данные существующего контакта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контакт успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contactDetails) {
        Contact existingContact = contactService.getContact(id);
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }
        existingContact.setName(contactDetails.getName());
        existingContact.setEmail(contactDetails.getEmail());
        Contact updatedContact = contactService.updateContact(existingContact);
        return ResponseEntity.ok(updatedContact);
    }
//    public ResponseEntity<Contact> updateContact(Integer id, @RequestBody Contact contact) {
//        Contact updatedContact = contactService.updateContact(id, contact.getName(), contact.getEmail());
//        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
//    }

    @Operation(summary = "Удалить контакт", description = "Удаляет контакт по указанному идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Контакт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        boolean deleted = contactService.deleteContact(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить все контакты", description = "Возвращает список всех контактов.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка контактов") })
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }


}
//    @Autowired
//    private ContactService contactService;
//    @Autowired
//    private UserService userService;
//
//
//    @GetMapping("/{id}")
////    public ResponseEntity<Contact> getContactById(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
////        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
////        Contact contact = contactService.getContactById(id);
////        if (contact == null || !contact.getOwnerId().equals(user.getId())) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
////        }
////        return ResponseEntity.ok(contact);
////    }
//    public ResponseEntity<Contact> getContactById(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
//        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
//        Contact contact = contactService.getContactById(id);
//        if (contact == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Если контакт не найден
//        }
//        if (!contact.getOwnerId().equals(user.getId())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        return ResponseEntity.ok(contact);
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<Contact>> getAllContacts(@RequestHeader("Authorization") String token) {
//        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
//        List<Contact> contacts = contactService.getContactsByOwnerId(user.getId());
//        return ResponseEntity.ok(contacts);
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Contact> addContact(@RequestBody Contact contact, @RequestHeader("Authorization") String token) {
//        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
//        contact.setOwnerId(user.getId());
//        Contact savedContact = contactService.addContact(contact);
//        return ResponseEntity.ok(savedContact);
//    }
//
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contact, @RequestHeader("Authorization") String token) {
//        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
//        Contact updatedContact = contactService.updateContact(id, contact.getName(), contact.getEmail(), user.getId());
//        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//    }
//
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteContact(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
//        User user = userService.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
//        boolean deleted = contactService.deleteContact(id, user.getId());
//        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//    }
//
////метод возвращал случайный контакт (
////    @GetMapping(value = "/")
////    public ResponseEntity<Contact> getRandomContact() {
////        Contact contact = contactService.getContact();
////        return ResponseEntity.ok(contact);
//    }
////    @GetMapping(value = "/")
////    public ResponseEntity<Contact> getRandomContact() {
////        Contact contact = Contact.builder()
////                .id(UUID.randomUUID())
////                .name("John")
////                .email("john@example.com")
////                .build();
////        return ResponseEntity.ok(contact);
////    }

