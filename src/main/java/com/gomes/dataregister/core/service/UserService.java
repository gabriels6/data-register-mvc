package com.gomes.dataregister.core.service;

import com.gomes.dataregister.core.model.User;
import com.gomes.dataregister.core.repository.UserRepository;
import com.gomes.dataregister.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    SessionService sessionService;

    public String test() {
        return "User service is working";
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }


    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
