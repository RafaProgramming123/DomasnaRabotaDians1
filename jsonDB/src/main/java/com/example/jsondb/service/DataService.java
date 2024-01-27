package com.example.jsondb.service;

import com.example.jsondb.domain.DataJson;
import com.example.jsondb.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
   private  DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    public DataJson save(DataJson dataJson)
    {
        return dataRepository.save(dataJson);
    }

    public Iterable<DataJson> save(List<DataJson> datajson)
    {
        return dataRepository.saveAll(datajson);
    }

    public Iterable<DataJson> list()
    {
        return dataRepository.findAll();
    }
}
