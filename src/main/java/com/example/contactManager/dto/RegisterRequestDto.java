package com.example.contactManager.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private String role; //не используется
}
//класс используется для передачи данных при регистрации нового пользователя.
// Он содержит поля для имени пользователя, электронной почты, пароля и роли.
// Эти данные обычно отправляются на сервер в виде JSON или другого формата для создания новой учетной записи.