package com.example.boot.Service.Implementation;

import com.example.boot.Entity.User;
import com.example.boot.Repository.UserRepository;
import com.example.boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getData(Long id) {
        Optional<User> getUser =userRepository.findById(id);
        return getUser.get();
    }

    @Override
    public List<User> getAllData() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMail(user.getMail());
        User updateUser = userRepository.save(user);
        return updateUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
