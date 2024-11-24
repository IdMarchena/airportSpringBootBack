package com.example.airport.mapper;

import com.example.airport.dtos.ReserveDto;
import com.example.airport.entities.*;
import com.example.airport.repositories.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReserveMapper {
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AirlaneRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    @Named("complete")
    @Mapping(source = "reserve.client.id", target = "client")
    @Mapping(source = "reserve.passengers", target = "passengers")
    @Mapping(source = "reserve.flights", target = "flights")
    public abstract ReserveDto toIdDto(Reserve reserve);

    @Named("listComplete")
    @Mapping(source = "reserve.client.id", target = "client")
    @Mapping(source = "reserve.passengers", target = "passengers")
    @Mapping(source = "reserve.flights", target = "flights")
    public abstract List<ReserveDto> toListIdDto(List<Reserve> reserves);

    @Mapping(source = "reserveDto.client", target = "client")
    @Mapping(source = "reserveDto.passengers", target = "passengers")
    @Mapping(source = "reserveDto.flights", target = "flights")
    public abstract Reserve toEntity(ReserveDto reserveDto);

    @Mapping(source = "reserveDto.client", target = "client")
    @Mapping(source = "reserveDto.passengers", target = "passengers")
    @Mapping(source = "reserveDto.flights", target = "flights")
    public abstract List<Reserve> toListEntity(List<ReserveDto> reserves);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "reserve.client.id", target = "client")
    @Mapping(source = "reserve.passengers", target = "passengers")
    @Mapping(source = "reserve.flights", target = "flights")
    public abstract ReserveDto toDto(Reserve reserve);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "reserve.client.id", target = "client")
    @Mapping(source = "reserve.passengers", target = "passengers")
    @Mapping(source = "reserve.flights", target = "flights")
    public abstract List<ReserveDto> toListDto(List<Reserve> reserves);

    public Long mapToClientId(Client client){
        return client.getId();
    }

    public Client mapToClient(Long id){
        return clientRepository.findById(id).get();
    }

    public Long mapToReserveId(Reserve reserve){
        return reserve.getId();
    }
    public Reserve mapToReserve(Long id){
        return reserveRepository.findById(id).get();
    }

    public Long mapToPassengerId(Passenger passenger){
        return passenger.getId();
    }

    public Passenger mapToPassenger(Long id){
        return passengerRepository.findById(id).get();
    }

    public Long mapToAirlaneId(Airlane airline){
        return airline.getId();
    }

    public Airlane mapToAirlane(Long id){
        return airlineRepository.findById(id).get();
    }

    public Long mapToAirportId(Airport airport){
        return airport.getId();
    }

    public Airport mapToAirport(Long id){
        return airportRepository.findById(id).get();
    }
}
