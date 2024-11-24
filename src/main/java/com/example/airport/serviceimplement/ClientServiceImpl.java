package com.example.airport.serviceimplement;
import com.example.airport.dtos.ClientDto;
import com.example.airport.entities.Client;
import com.example.airport.mapper.ClientMapper;
import com.example.airport.mapper.ReserveMapper;
import com.example.airport.repositories.ClientRepository;
import com.example.airport.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class ClientServiceImpl implements ClientService {
    private ClientMapper clientMapper;
    private ReserveMapper reserveMapper;
    private ClientRepository clientRepository;

    @Override
    public ClientDto save(ClientDto client) {
        return clientMapper.toIdDto(clientRepository.save(clientMapper.toEntity(client)));
    }

    @Override
    public Optional<ClientDto> findById(int id) {
        return clientRepository.findById((long) id).map(clientMapper::toIdDto);
    }

    @Override
    public Optional<ClientDto> update(int id, ClientDto client) {
        return clientRepository.findById((long) id).map(oldClient -> {
            oldClient.setAddress(client.address());
            oldClient.setFirstName(client.firstName());
            oldClient.setLastName(client.lastName());
            oldClient.setCell(client.cell());
            oldClient.setEmail(client.email());
            oldClient.setUsername(client.username());
            oldClient.setReserves(reserveMapper.toListEntity(client.reserves()));
            return clientMapper.toIdDto(clientRepository.save(oldClient));
        });
    }

    @Override
    public List<ClientDto> findAll() {
        return clientMapper.toListIdDto(clientRepository.findAll());
    }

    @Override
    public List<ClientDto> findByName(String name) {
        Client c = new Client();
        c.setFirstName(name);
        return clientMapper.toListIdDto(clientRepository.findAll(Example.of(c)));
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById((long) id);
    }
}
