package com.example.jsondb.repository;

import com.example.jsondb.domain.UsersApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UsersApp,String> {


}
