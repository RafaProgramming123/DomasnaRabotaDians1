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
    private final UsersRepository usersRepository; // Add UsersRepository

    @Autowired // Autowire the repositories
    public SavedPlacesService(SavedPlacesRepository savedPlacesRepository, UsersRepository usersRepository) {
        this.savedPlacesRepository = savedPlacesRepository;
        this.usersRepository = usersRepository;
    }

    public SavedPlaces savePlace(String userName, String placeId, String name, String description, String type) {
        // Find the user by userName
        Optional<Users> user = usersRepository.findById(userName);

        if (user.isPresent()) {
            // Create a new SavedPlaces instance
            SavedPlaces newSavedPlace = new SavedPlaces();
            newSavedPlace.setUser(user.get()); // Set the user
            newSavedPlace.setPlaceId(placeId); // Set the place ID
            newSavedPlace.setName(name); // Set the place name
            newSavedPlace.setDescription(description); // Set the place description
            newSavedPlace.setType(type); // Set the place type

            // Save the new SavedPlaces instance to the database
            return savedPlacesRepository.save(newSavedPlace);
        } else {
            // Handle the case where the user isn't found
            throw new RuntimeException("User not found with username: " + userName);
        }
    }
    public List<SavedPlaces> findPlacesByUser(String userName) {
        // Find the user by userName
        Optional<Users> user = usersRepository.findById(userName);

        if (user.isPresent()) {
            // Use the user's ID to find the saved places associated with the user
            List<SavedPlaces> savedPlacesList = savedPlacesRepository.findByUser(user.get());

            return savedPlacesList;
        } else {
            // Handle the case where the user isn't found
            throw new RuntimeException("User not found with username: " + userName);
        }
    }
}
