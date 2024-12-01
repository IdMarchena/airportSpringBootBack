package com.example.airport.api;

import com.example.airport.dtos.AirlaneDto;
import com.example.airport.exceptions.AirlaneNotFoundException;
import com.example.airport.services.AirlaneService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlaneController {
    private final AirlaneService airlaneService;
    public AirlaneController(AirlaneService airlaneService) {
        this.airlaneService = airlaneService;
    }

    @GetMapping()
    public ResponseEntity<List<AirlaneDto>> getAllAirlanes() {
        return ResponseEntity.ok(airlaneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlaneDto> getAirlaneById(@RequestParam int id) {
        return airlaneService.findById(id)
                .map( a -> ResponseEntity.ok().body(a))
                .orElseThrow(()-> new AirlaneNotFoundException("doesnt found airlane with code"+id)) ;
    }

    @PostMapping()
    public ResponseEntity<AirlaneDto> createAirlane(@RequestBody AirlaneDto Airlane) {
        return createNewAirlane(Airlane);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlaneDto> updateAirlane(@PathVariable int id, @RequestBody AirlaneDto Airlane) {
        Optional<AirlaneDto> airlaneUpdated = airlaneService.update(id, Airlane);
        return airlaneUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewAirlane(Airlane);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirlane(@PathVariable int id) {
        airlaneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<AirlaneDto> createNewAirlane(AirlaneDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
