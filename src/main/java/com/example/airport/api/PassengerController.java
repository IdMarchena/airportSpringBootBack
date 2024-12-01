package com.example.airport.api;

import com.example.airport.dtos.PassengerDto;
import com.example.airport.exceptions.PassengerNotFoundException;
import com.example.airport.services.PassengerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping()
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable int id) {
        return passengerService.getById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElseThrow(() -> new PassengerNotFoundException("doesnt found Passenger with id " + id ));
    }

    @PostMapping()
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto) {
        return createNewPassenger(passengerDto);

        // Passenger c = passengerService.savePassenger(passenger);
        // return ResponseEntity.created(new URI("/api/v1/passengers/" + c.getId())).body(c);
        // Thowght the exception.
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable int id, @RequestBody PassengerDto passengerDto) {
        Optional<PassengerDto> passengerUpdated = passengerService.update(id, passengerDto);
        return passengerUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewPassenger(passengerDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable int id) {
        passengerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private ResponseEntity<PassengerDto> createNewPassenger(PassengerDto p) {
        PassengerDto passangerSaved = passengerService.save(p);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(passangerSaved.id())
                .toUri();
        return ResponseEntity.created(location).body(passangerSaved);
    }

}
