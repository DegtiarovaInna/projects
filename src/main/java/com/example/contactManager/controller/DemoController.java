package com.example.contactManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable("id") String value) {
        String response = String.format("Передано значение: %s", value);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/postItem")
    public ResponseEntity<String> postItemWithValue(@RequestBody String data) {
        String response = String.format("postItemWithValue получил данные: %s", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> postItem(@RequestBody String data) {
        String response = String.format("postItem получил данные: %s", data);
        return ResponseEntity.ok(response);
    }
}

