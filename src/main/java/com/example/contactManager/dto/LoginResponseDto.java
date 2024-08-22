package com.example.contactManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String email;
    private String token;
}
//класс используется для представления ответа сервера после успешного входа в систему.
// Он содержит поля для электронной почты пользователя и токена авторизации.
// Токен используется для аутентификации последующих запросов пользователя к защищенным ресурсам.