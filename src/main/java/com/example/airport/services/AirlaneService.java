package com.example.airport.services;

import com.example.airport.dtos.AirlaneDto;

import java.util.List;
import java.util.Optional;

public interface AirlaneService {
    AirlaneDto save(AirlaneDto airline);
    Optional<AirlaneDto> findById(int id);
    Optional<AirlaneDto> update(int id, AirlaneDto airline);
    List<AirlaneDto> findAll();
    List<AirlaneDto> findByName(String name);
    void deleteById(int id);
}
