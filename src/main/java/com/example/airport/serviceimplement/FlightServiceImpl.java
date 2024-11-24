package com.example.airport.serviceimplement;
import com.example.airport.dtos.FlightDto;
import com.example.airport.entities.Flight;
import com.example.airport.mapper.FlightMapper;
import com.example.airport.repositories.AirlaneRepository;
import com.example.airport.repositories.FlightRepository;
import com.example.airport.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class FlightServiceImpl implements FlightService {
    private final AirlaneRepository airlaneRepository;
    private FlightMapper flightMapper;
    FlightRepository flightRepository;

    @Override
    public FlightDto save(FlightDto flight) {
        return flightMapper.toIdDto(flightRepository.save(flightMapper.toEntity(flight)));
    }

    @Override
    public Optional<FlightDto> findById(int id) {
        return flightRepository.findById((long) id).map(flightMapper::toIdDto);
    }

    @Override
    public Optional<FlightDto> update(int id, FlightDto flight) {
        return flightRepository.findById((long) id).map(oldFlight ->{
            oldFlight.setOrigin(flight.origin());
            oldFlight.setDestination(flight.destination());
            oldFlight.setAirlane(airlaneRepository.findById(flight.airlane()).get());
            oldFlight.setAirport(flightMapper.mapToAirport(flight.airport()));
            oldFlight.setDepartureTime(flight.departureTime());
            oldFlight.setArrivalTime(flight.arrivalTime());
            oldFlight.setCapacity(flight.capacity());
            oldFlight.setReserves(flightMapper.mapToReserveList(flight.reserves()));

            return flightMapper.toIdDto(flightRepository.save(oldFlight));
        });
    }

    @Override
    public List<FlightDto> findAll() {
        return flightMapper.toListIdDto(flightRepository.findAll());
    }

    @Override
    public List<FlightDto> findByDate(LocalDateTime date) {
        Flight f = new Flight();
        f.setDepartureTime(date);
        Example<Flight> example = Example.of(f);
        return flightMapper.toListIdDto(flightRepository.findAll(example));
    }

    @Override
    public void deleteFlight(int id) {
        flightRepository.deleteById((long) id);
    }
}
