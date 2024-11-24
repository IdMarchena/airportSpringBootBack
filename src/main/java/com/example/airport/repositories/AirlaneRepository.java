package com.example.airport.repositories;

import com.example.airport.entities.Airlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlaneRepository extends JpaRepository<Airlane,Long> {
}
