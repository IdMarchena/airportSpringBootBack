package com.example.airport.services;
import java.util.List;
import java.util.Optional;
public class AirportService {
    AirportDto save(AirportDto airport);
    Optional<AirportDto> findById(int id);
    Optional<AirportDto> update(int id, AirportDto airport);
    List<AirportDto> findAll();
    List<AirportDto> findByName(String name);
    void deleteById(int id);
}