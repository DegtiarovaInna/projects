package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Primary
public class JdbcContactRepository implements ContactRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact create(Contact contact) {
        String sql = "INSERT INTO contacts (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getName(), contact.getEmail());
        return contact;
    }

    @Override
    public Contact read(Integer id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Contact.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build(),
                id);
    }

    @Override
    public List<Contact> readAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build());
    }

    @Override
    public Contact update(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getId());
        return contact;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Override
//    public Contact create(Contact contact) {
//        String sql = "INSERT INTO contact (name, email, owner_id) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getOwnerId());
//        return contact;
//    }
//
//    @Override
//    public Contact getContactById(Integer id) {
//        String sql = "SELECT * FROM contact WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Contact.builder()
//                        .id(rs.getInt("id"))
//                        .name(rs.getString("name"))
//                        .email(rs.getString("email"))
//                        .ownerId(rs.getInt("owner_id"))
//                        .build(),
//                id);
//    }
//
//    @Override
//    public List<Contact> getAllContacts() {
//        String sql = "SELECT * FROM contact";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> Contact.builder()
//                .id(rs.getInt("id"))
//                .name(rs.getString("name"))
//                .email(rs.getString("email"))
//                .ownerId(rs.getInt("owner_id"))
//                .build());
//    }
//
//    @Override
//    public Contact update(Contact contact) {
//        String sql = "UPDATE contact SET name = ?, email = ?, owner_id = ? WHERE id = ?";
//        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getOwnerId(), contact.getId());
//        return contact;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        String sql = "DELETE FROM contact WHERE id = ?";
//        return jdbcTemplate.update(sql, id) > 0;
//    }
//
//    @Override
//    public List<Contact> findAllByOwnerId(Integer ownerId) {
//        String sql = "SELECT * FROM contact WHERE owner_id = ?";
//        return jdbcTemplate.query(sql, new Object[]{ownerId}, (rs, rowNum) -> Contact.builder()
//                .id(rs.getInt("id"))
//                .name(rs.getString("name"))
//                .email(rs.getString("email"))
//                .ownerId(rs.getInt("owner_id"))
//                .build());
//    }
//}
