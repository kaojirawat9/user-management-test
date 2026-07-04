package com.test.test.Repository;

import org.springframework.stereotype.Repository;

import com.test.test.Model.User;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    public UserRepository() {
        users.put(1L, new User(
                1L,
                "๋Jirawat Komngam",
                "jirawatku",
                "jirawatku@gmail.com",
                "08X-XXXX-XXXX",
                "jirawat.com"));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public void delete(Long id) {
        users.remove(id);
    }

    public Long generateId() {
        return users.keySet().stream()
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}
