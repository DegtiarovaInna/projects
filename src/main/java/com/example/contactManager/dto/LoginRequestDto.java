package com.example.contactManager.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
//класс используется для передачи данных при попытке входа в систему.
// Он содержит поля для электронной почты и пароля пользователя.
// Эти данные отправляются на сервер для проверки и получения токена авторизации.