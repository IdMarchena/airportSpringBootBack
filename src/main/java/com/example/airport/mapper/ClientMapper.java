package com.example.airport.mapper;

import com.example.airport.dtos.ClientDto;
import com.example.airport.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReserveMapper.class)
public interface ClientMapper {
    @Named("complete")
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listComplete")
    ClientDto toIdDto(Client client);

    @Named("listComplete")
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listComplete")
    List<ClientDto> toListIdDto(List<Client> clients);

    @Mapping(source = "clientDto.reserves", target = "reserves")
    Client toEntity(ClientDto clientDto);


    @Mapping(source = "clientDto.reserves", target = "reserves")
    List<Client> toListEntity(List<ClientDto> clientDto);

    @Named("withoutId")
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listWithoutId")
    ClientDto toDto(Client client);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "client.reserves", target = "reserves", qualifiedByName = "listWithoutId")
    List<ClientDto> toListDto(List<Client> clients);
}
