package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addUser(User user);
    public List<User> listUsers();
    public void updateUser(User user, int id);
    public void removeUser(int id);
    public User getUserById(int id);
}
