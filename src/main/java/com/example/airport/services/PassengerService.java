package com.example.airport.services;
import java.util.List;
import java.util.Optional;
public class PassengerService {
    PassengerDto save(PassengerDto passenger);
    Optional<PassengerDto> getById(int id);
    Optional<PassengerDto> update(int id, PassengerDto passenger);
    List<PassengerDto> findAll();
    List<PassengerDto> findByName(String name);
    void deleteById(int id);
}
