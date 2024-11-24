package com.example.airport.services;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public class FlightService {
    FlightDto save(FlightDto flight);
    Optional<FlightDto> findById(int id);
    Optional<FlightDto> update(int id, FlightDto flight);
    List<FlightDto> findAll();
    List<FlightDto> findByDate(LocalDateTime date);
    void deleteFlight(int id);
}
