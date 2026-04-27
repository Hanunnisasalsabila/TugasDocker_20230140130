package com.tugas.deploy.service;

import com.tugas.deploy.model.User;
import com.tugas.deploy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User request){
        request.setId(UUID.randomUUID().toString());
        return userRepository.save(request);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, User request){
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null){
            existingUser.setNama(request.getNama());
            existingUser.setNim(request.getNim());
            existingUser.setJenis_kelamin(request.getJenis_kelamin());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}