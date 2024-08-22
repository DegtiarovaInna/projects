package com.example.contactManager.service;

import com.example.contactManager.model.Contact;
import com.example.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getContact(Integer id) {
        return contactRepository.read(id);
    }

    public Contact createContact(String name, String email) {
        Contact contact = Contact.builder()
                .id(new Random().nextInt(0,Integer.MAX_VALUE))
                .name(name)
                .email(email)
                .build();
        return contactRepository.create(contact);
    }
//    Создает новый объект Contact с указанными именем и email.
//    Генерирует случайный ID для контакта.
//    Сохраняет контакт в репозитории ContactRepository.
public Contact updateContact(Contact contact) {
    // Сначала проверяем, существует ли контакт
    Contact existingContact = contactRepository.read(contact.getId());
    if (existingContact == null) {
        return null;
    }
    // Обновляем контакт и сохраняем его
    existingContact.setName(contact.getName());
    existingContact.setEmail(contact.getEmail());
    return contactRepository.update(existingContact);
}
//    public Contact updateContact(Integer id, String name, String email) {
//        Contact contact = Contact.builder()
//                .id(id)
//                .name(name)
//                .email(email)
//                .build();
//        return contactRepository.update(contact);
//    }
//Создает объект Contact с указанными данными и ID.
//Обновляет контакт в репозитории ContactRepository.
    public boolean deleteContact(Integer id) {
        return contactRepository.delete(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.readAll();
    }

}
//    @Autowired
//    private ContactRepository contactRepository;
//    // private final List<Contact> contacts = new ArrayList<>(); - не увидела в ТЗ,
//    // про гибкость в реализации хранения данных, сделала только для коллекций, чекрез стримы
//    public Contact getContactById(Integer id) {
//
//        return contactRepository.getContactById(id);
//    }
//
//    public List<Contact> getAllContacts() {
//
//        return contactRepository.getAllContacts();
//    }
//    // Метод для получения всех контактов текущего пользователя
//    public List<Contact> getContactsByOwnerId(Integer ownerId) {
//        // Возвращаем только контакты, принадлежащие данному пользователю
//        return contactRepository.findAllByOwnerId(ownerId);
//    }
//
//    public Contact addContact(Contact contact) {
//
//        return contactRepository.create(contact);
//    }
//
//
//    public Contact updateContact(Integer id, String name, String email, Integer ownerId) {
//        Contact contact = contactRepository.getContactById(id);
//        if (contact == null || !contact.getOwnerId().equals(ownerId)) {
//            return null; // Изменение: Возвращаем null, если контакт не найден или не принадлежит владельцу
//        }
//        contact.setName(name);
//        contact.setEmail(email);
//        return contactRepository.update(contact);
//    }
//
//    public boolean deleteContact(Integer id, Integer ownerId) {
//        Contact contact = contactRepository.getContactById(id);
//        if (contact == null || !contact.getOwnerId().equals(ownerId)) {
//            return false; // Изменение: Возвращаем false, если контакт не найден или не принадлежит владельцу
//        }
//        return contactRepository.delete(id);
//    }
//
//


//    public Contact getContactById(UUID id) {
//        return contacts.stream()
//                .filter(contact -> contact.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Contact not found"));
//    }
//    public List<Contact> getAllContacts() {
//        return new ArrayList<>(contacts); // выозвращаем копию списка контактов
//    }
//    public Contact addContact(Contact contact) {
//        contacts.add(contact);
//        return contact;
//    }
//    public Contact updateContact(UUID id, Contact updatedContact) {
//        return contacts.stream()
//                .filter(contact -> contact.getId().equals(id))
//                .findFirst()
//                .map(contact -> {
//                    contact.setName(updatedContact.getName());
//                    contact.setEmail(updatedContact.getEmail());
//                    return contact;
//                })
//                .orElseThrow(() -> new RuntimeException("Contact not found"));
//    }
//    public void deleteContact(UUID id) {
//        contacts.removeIf(contact -> contact.getId().equals(id));
//    }

    //пример
//    public Contact getContact() {
//        return Contact.builder()
//                .id(UUID.randomUUID())
//                .name("John")
//                .email("john@example.com")
//                .build();
//    }



