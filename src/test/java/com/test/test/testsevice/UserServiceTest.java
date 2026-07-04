package com.test.test.testsevice;

import org.junit.jupiter.api.Test;

import com.test.test.DTOs.UserRequest;
import com.test.test.Model.User;
import com.test.test.Repository.UserRepository;
import com.test.test.Service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void getUserByIdTest() {
        UserRepository repository = mock(UserRepository.class);
        UserService service = new UserService(repository);

        User user = new User(1L, "John", "johnny", "john@gmail.com", null, null);

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = service.getUserById(1L);

        assertEquals("John", result.getName());
    }

    @Test
    void createUserTest() {
        UserRepository repository = mock(UserRepository.class);
        UserService service = new UserService(repository);

        UserRequest request = new UserRequest();
        request.setName("John");
        request.setUserName("johnny");
        request.setEmail("john@gmail.com");

        when(repository.generateId()).thenReturn(1L);

        User savedUser = new User(1L, "John", "johnny", "john@gmail.com", null, null);

        when(repository.save(any(User.class)))
                .thenReturn(savedUser);

        User result = service.createUser(request);

        assertEquals(1L, result.getId());
    }
}
