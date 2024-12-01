package com.example.airport.api;

import com.example.airport.dtos.AirportDto;
import com.example.airport.exceptions.AirportNotFoundException;
import com.example.airport.services.AirportService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {
    private AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping()
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return ResponseEntity.ok(airportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable int id) {
        return airportService.findById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElseThrow(() -> new AirportNotFoundException("doesnt found airport whit code"+id));
    }

    @PostMapping()
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto Airport) {
        return createNewAirport(Airport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable int id, @RequestBody AirportDto Airport) {
        Optional<AirportDto> airportUpdated = airportService.update(id, Airport);
        return airportUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewAirport(Airport);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable int id) {
        airportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<AirportDto> createNewAirport(AirportDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }

}
