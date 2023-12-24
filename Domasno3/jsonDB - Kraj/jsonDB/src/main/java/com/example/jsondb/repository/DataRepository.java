package com.example.jsondb.repository;

import com.example.jsondb.domain.DataJson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<DataJson,Long> {

}
