package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContactsRepository extends JpaRepository<Contact, Integer> {
    // Дополнительные методы JPA, если необходимо
    // Например
    // Contact findByEmail(String email);
}
