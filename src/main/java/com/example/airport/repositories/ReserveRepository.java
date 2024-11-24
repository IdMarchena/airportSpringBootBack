package com.example.airport.repositories;

import com.example.airport.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve,Long> {
}
