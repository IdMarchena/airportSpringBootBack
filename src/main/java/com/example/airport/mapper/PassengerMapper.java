package com.example.airport.mapper;

import com.example.airport.dtos.PassengerDto;
import com.example.airport.entities.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReserveMapper.class)
public interface PassengerMapper {
    @Named("complete")
    @Mapping(source = "passenger.reserve.id", target = "reserve")
    PassengerDto toIdDto(Passenger passenger);

    @Named("listComplete")
    @Mapping(source = "passenger.reserve.id", target = "reserve", qualifiedByName = "mapToReserveIdPassenger")
    List<PassengerDto> toListIdDto(List<Passenger> passengers);

    @Mapping(source = "passengerDto.reserve", target = "reserve")
    Passenger toEntity(PassengerDto passengerDto);


    @Mapping(source = "passengerDto.reserve", target = "reserve")
    List<Passenger> toListEntity(List<PassengerDto> passengerDto);

    @Named("withoutId")
    @Mapping(target="id",ignore = true)
    @Mapping(source = "passenger.reserve.id", target = "reserve")
    PassengerDto toDto(Passenger passenger);

    @Named("listWithoutId")
    @Mapping(target="id",ignore = true)
    @Mapping(source = "passenger.reserve.id", target = "reserve")
    List<PassengerDto> toListDto(List<Passenger> passengers);
}
