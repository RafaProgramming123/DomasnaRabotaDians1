package com.example.kulturnostoriskonasledstvo.Service;


import com.example.kulturnostoriskonasledstvo.Domain.Users;
import com.example.kulturnostoriskonasledstvo.UsersRepository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    public final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(Users user) {
        usersRepository.save(user);

    }
    public boolean authenticateUser(String username, String password) {
        Users user = usersRepository.findById(username).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }


}
