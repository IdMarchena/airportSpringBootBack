package com.example.airport.api;

import com.example.airport.dtos.FlightDto;
import com.example.airport.exceptions.FlightNotFoundException;
import com.example.airport.services.FlightService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@RequestParam int id) {
        return flightService.findById(id)
                .map( a -> ResponseEntity.ok().body(a))
                .orElseThrow(()-> new FlightNotFoundException("doesnt found fligh with"+id));
    }

    @PostMapping()
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flight) {
        return createNewFlight(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable int id, @RequestBody FlightDto flight) {
        Optional<FlightDto> flightUpdated = flightService.update(id, flight);
        return flightUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewFlight(flight);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable int id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<FlightDto> createNewFlight(FlightDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
