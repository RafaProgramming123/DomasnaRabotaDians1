package com.example.kulturnostoriskonasledstvo.Service;

import com.example.kulturnostoriskonasledstvo.Domain.SavedPlaces;
import com.example.kulturnostoriskonasledstvo.Domain.Users;
import com.example.kulturnostoriskonasledstvo.UsersRepository.SavedPlacesRepository;
import com.example.kulturnostoriskonasledstvo.UsersRepository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavedPlacesService {

    private final SavedPlacesRepository savedPlacesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public SavedPlacesService(SavedPlacesRepository savedPlacesRepository, UsersRepository usersRepository) {
        this.savedPlacesRepository = savedPlacesRepository;
        this.usersRepository = usersRepository;
    }

    public SavedPlaces savePlace(String userName, String placeId, String name, String description, String type) {

        Optional<Users> user = usersRepository.findById(userName);

        if (user.isPresent()) {

            SavedPlaces newSavedPlace = new SavedPlaces();
            newSavedPlace.setUser(user.get());
            newSavedPlace.setPlaceId(placeId);
            newSavedPlace.setName(name);
            newSavedPlace.setDescription(description);
            newSavedPlace.setType(type);


            return savedPlacesRepository.save(newSavedPlace);
        } else {

            throw new RuntimeException("User not found with username: " + userName);
        }
    }
    public List<SavedPlaces> findPlacesByUser(String userName) {

        Optional<Users> user = usersRepository.findById(userName);

        if (user.isPresent()) {

            List<SavedPlaces> savedPlacesList = savedPlacesRepository.findByUser(user.get());

            return savedPlacesList;
        } else {

            throw new RuntimeException("User not found with username: " + userName);
        }
    }
}
