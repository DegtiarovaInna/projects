package com.example.contactManager.controller;

import com.example.contactManager.model.Engineer;
import com.example.contactManager.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/engineers")
public class EngineerController {
    @Autowired
    private EngineerService engineerService;

    @GetMapping
    public ResponseEntity<List<Engineer>> getAllEngineers() {
        List<Engineer> engineers = engineerService.getAllEngineers();
        return ResponseEntity.status(HttpStatus.OK).body(engineers);
    }

    @PostMapping
    public ResponseEntity<Engineer> createEngineer(@RequestBody Engineer engineer) {
        Engineer createdEngineer = engineerService.createEngineer(engineer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEngineer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Engineer> updateEngineer(@PathVariable Long id, @RequestBody Engineer engineerDetails) {
        Optional<Engineer> updatedEngineer = engineerService.updateEngineer(id, engineerDetails);
        return updatedEngineer.map(engineer -> ResponseEntity.status(HttpStatus.OK).body(engineer))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id) {
        boolean deleted = engineerService.deleteEngineer(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Engineer> getEngineerById(@PathVariable Long id) {
        Optional<Engineer> engineer = engineerService.getEngineerById(id);
        return engineer.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
