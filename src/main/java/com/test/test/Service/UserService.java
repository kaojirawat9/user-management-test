package com.test.test.Service;

import org.springframework.stereotype.Service;

import com.test.test.DTOs.UserRequest;
import com.test.test.Model.User;
import com.test.test.Repository.UserRepository;
import com.test.test.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(UserRequest request) {
        validate(request);

        User user = new User(
                repository.generateId(),
                request.getName(),
                request.getUserName(),
                request.getEmail(),
                request.getPhone(),
                request.getWebsite());

        return repository.save(user);
    }

    public User updateUser(Long id, UserRequest request) {
        validate(request);

        User existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setName(request.getName());
        existing.setUserName(request.getUserName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setWebsite(request.getWebsite());

        return repository.save(existing);
    }

    public void deleteUser(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        repository.delete(id);
    }

    private void validate(UserRequest request) {
        if (isBlank(request.getName())) {
            throw new IllegalArgumentException("Name is required");
        }

        if (isBlank(request.getUserName())) {
            throw new IllegalArgumentException("Username is required");
        }

        if (isBlank(request.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
