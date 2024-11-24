package com.example.airport.services;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public class ReserveService {
    ReserveDto save(ReserveDto reserve);
    Optional<ReserveDto> findById(int id);
    Optional<ReserveDto> update(int id, ReserveDto reserve);
    List<ReserveDto> findAll();
    List<ReserveDto> findByClient(ClientDto client);
    List<ReserveDto> findByDate(LocalDateTime date);
    void deleteById(int id);
}
