package com.example.jsondb.service;

import com.example.jsondb.domain.UsersApp;
import com.example.jsondb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Iterable<UsersApp> save(List<UsersApp> userjson)
    {
        return userRepository.saveAll(userjson);
    }

    public Iterable<UsersApp> listusers()
      {
          return userRepository.findAll();
      }
}
