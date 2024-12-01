package com.example.airport.api;

import com.example.airport.dtos.ReserveDto;
import com.example.airport.exceptions.ReserveNotFoundException;
import com.example.airport.services.ReserveService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reserves")
public class ReserveController {
    private ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping()
    public ResponseEntity<List<ReserveDto>> getAllReserves() {
        return ResponseEntity.ok(reserveService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveDto> getReserveById(@PathVariable int id) {
        return reserveService.findById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElseThrow(() -> new ReserveNotFoundException("Doesnt found Reserve with"+id));
    }

    @PostMapping()
    public ResponseEntity<ReserveDto> createReserve(@RequestBody ReserveDto reserve) {
        return createNewReserve(reserve);

        // Reserve c = reserveService.saveReserve(reserve);
        // return ResponseEntity.created(new URI("/api/v1/reserves/" + c.getId())).body(c);
        // Thowght the exception.
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReserveDto> updateReserve(@PathVariable int id, @RequestBody ReserveDto reserve) {
        Optional<ReserveDto> reserveUpdated = reserveService.update(id, reserve);
        return reserveUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewReserve(reserve);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserve(@PathVariable int id) {
        reserveService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ReserveDto> createNewReserve(ReserveDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
