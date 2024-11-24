package com.example.airport.services;
import java.util.List;
import java.util.Optional;
public class AirlaneService {
    AirlaneDto save(AirlaneDto airline);
    Optional<AirlaneDto> findById(int id);
    Optional<AirlaneDto> update(int id, AirlaneDto airline);
    List<AirlaneDto> findAll();
    List<AirlaneDto> findByName(String name);
    void deleteById(int id);

}
