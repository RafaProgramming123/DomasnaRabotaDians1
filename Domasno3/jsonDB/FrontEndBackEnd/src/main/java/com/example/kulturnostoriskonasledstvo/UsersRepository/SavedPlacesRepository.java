package com.example.kulturnostoriskonasledstvo.UsersRepository;

import com.example.kulturnostoriskonasledstvo.Domain.SavedPlaces;
import com.example.kulturnostoriskonasledstvo.Domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedPlacesRepository extends CrudRepository<SavedPlaces,Long> {

    List<SavedPlaces> findByUser(Users user);
}
