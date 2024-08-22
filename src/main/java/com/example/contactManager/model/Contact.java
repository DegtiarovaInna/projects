package com.example.contactManager.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // Эта аннотация говорит Hibernate, что данный класс должен отображаться на таблицу в базе данных.
@Table(name = "contacts")
public class Contact {
    @Id
    //Аннотация @Id указывает, что поле id является первичным ключом сущности.
    // Первичный ключ служит уникальным идентификатором для каждого объекта в базе данных.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Аннотация @GeneratedValue указывает, что значение этого поля будет
    // автоматически генерироваться. Параметр strategy = GenerationType.IDENTITY
    // задает стратегию генерации значений.
    // В данном случае используется стратегия IDENTITY, при которой
    // значение первичного ключа будет автоматически увеличиваться в базе данных
    // (обычно используется для автонумерации в SQL базах данных, таких как MySQL или
    // PostgreSQL).
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "owner_id")
    private Integer ownerId; // новое поле для хранения ID владельца контакта



}
//Без @Entity Spring (или другой фреймворк) не поймёт, что этот класс должен быть представлен в базе данных в виде таблицы.
// Это значит, что данные, которые ты собираешь в объекте этого класса, не будут автоматически сохраняться в БД.
//Убрав @Entity, ты просто сделаешь этот класс обычным Java-классом, который больше не будет связан с БД.