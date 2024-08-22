package com.example.contactManager.service;

import com.example.contactManager.model.Engineer;
import com.example.contactManager.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {
    @Autowired
    private EngineerRepository engineerRepository;

    public List<Engineer> getAllEngineers() {
        return engineerRepository.findAll();
    }

    public Engineer createEngineer(Engineer engineer) {
        return engineerRepository.save(engineer);
    }

    public Optional<Engineer> updateEngineer(Long id, Engineer engineerDetails) {
        if (engineerRepository.existsById(id)) {
            engineerDetails.setId(id);
            return Optional.of(engineerRepository.save(engineerDetails));
        }
        return Optional.empty();
    }

    public boolean deleteEngineer(Long id) {
        if (engineerRepository.existsById(id)) {
            engineerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Engineer> getEngineerById(Long id) {
        return engineerRepository.findById(id);
    }
}
