package com.example.airport.dtos;

import com.example.airport.entities.Reserve;
import com.example.airport.entities.Role;
import java.util.List;
import java.util.Set;
public record SignUpRequest (String firstName,
                             String lastName,
                             String address,
                             String cell,
                             String email,
                             List<Reserve> reserves,
                             String username,
                             String password,
                             Set<Role> roles){

}
