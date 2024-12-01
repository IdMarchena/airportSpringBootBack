package com.example.airport.api;

import com.example.airport.dtos.ClientDto;
import com.example.airport.exceptions.ClientNotFoundException;
import com.example.airport.services.ClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.lang.Long;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) { this.clientService = clientService; }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam(required = false, value = "name") String name) {
        return ResponseEntity.ok(clientService.findByName(name));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ClientDto> getClientById(@PathVariable int id) {
        return clientService.findById(id)
                .map( c -> ResponseEntity.ok().body(c))
                .orElseThrow(() -> new ClientNotFoundException("No se encontró el cliente con código"+id));
    }

    @PostMapping()
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        return createNewClient(clientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable int id, @RequestBody ClientDto clientDto) {
        Optional<ClientDto> clientUpdated = clientService.update(id, clientDto);
        return clientUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewClient(clientDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private ResponseEntity<ClientDto> createNewClient(ClientDto c) {
        ClientDto clientSaved = clientService.save(c);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientSaved.id())
                .toUri();
        return ResponseEntity.created(location).body(clientSaved);
    }
}