package com.example.airport.serviceimplement;
import com.example.airport.dtos.ClientDto;
import com.example.airport.dtos.ReserveDto;
import com.example.airport.entities.Reserve;
import com.example.airport.mapper.ClientMapper;
import com.example.airport.mapper.FlightMapper;
import com.example.airport.mapper.PassengerMapper;
import com.example.airport.mapper.ReserveMapper;
import com.example.airport.repositories.ClientRepository;
import com.example.airport.repositories.ReserveRepository;
import com.example.airport.services.ReserveService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class ReserveServiceImpl implements ReserveService {
    private final ClientRepository clientRepository;
    private ReserveMapper reserveMapper;
    private ClientMapper clientMapper;
    private FlightMapper flightMapper;
    private PassengerMapper passengerMapper;
    ReserveRepository reserveRepository;

    @Override
    public ReserveDto save(ReserveDto reserve) {
        return reserveMapper.toIdDto(reserveRepository.save(reserveMapper.toEntity(reserve)));
    }

    @Override
    public Optional<ReserveDto> findById(int id) {
        return reserveRepository.findById((long) id).map(reserveMapper::toDto);
    }

    @Override
    public Optional<ReserveDto> update(int id, ReserveDto reserve) {
        return reserveRepository.findById((long) id).map(oldReserve -> {
            oldReserve.setClient(clientRepository.findById(reserve.client()).get());
            oldReserve.setReservationDate(reserve.reservationDate());
            oldReserve.setFlights(flightMapper.toListEntity(reserve.flights()));
            oldReserve.setPassengers(passengerMapper.toListEntity(reserve.passengers()));
            oldReserve.setNumberOfSeats(reserve.numberOfSeats());
            return reserveMapper.toIdDto(reserveRepository.save(oldReserve));
        });
    }

    @Override
    public List<ReserveDto> findAll() {
        return reserveMapper.toListIdDto(reserveRepository.findAll());
    }

    @Override
    public List<ReserveDto> findByClient(ClientDto client) {
        Reserve r = new Reserve();
        r.setClient(clientMapper.toEntity(client));
        return reserveMapper.toListIdDto(reserveRepository.findAll(Example.of(r)));
    }

    @Override
    public List<ReserveDto> findByDate(LocalDateTime date) {
        Reserve r = new Reserve();
        r.setReservationDate(date);
        return reserveMapper.toListIdDto(reserveRepository.findAll(Example.of(r)));
    }

    @Override
    public void deleteById(int id) {
        reserveRepository.deleteById((long) id);
    }
}
