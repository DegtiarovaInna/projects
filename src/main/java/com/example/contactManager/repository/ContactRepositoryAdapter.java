package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
//Реализует методы ContactRepository с использованием JpaContactsRepository.
//Методы просто вызывают соответствующие методы jpaContactRepository
@Primary
@Repository
public class ContactRepositoryAdapter  implements ContactRepository{
    @Autowired
    private final JpaContactsRepository jpaContactRepository;

    public ContactRepositoryAdapter(JpaContactsRepository jpaContactRepository) {
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        return jpaContactRepository.save(contact);
    }

    @Override
    public Contact read(Integer id) {
        return jpaContactRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contact> readAll() {
        return jpaContactRepository.findAll();
    }

    @Override
    public Contact update(Contact contact) {
        return jpaContactRepository.save(contact);
    }

    @Override
    public boolean delete(Integer id) {
        if (jpaContactRepository.existsById(id)) {
            jpaContactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
