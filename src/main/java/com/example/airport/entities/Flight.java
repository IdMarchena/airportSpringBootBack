package com.example.airport.entities;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_airlane", referencedColumnName = "id")
    private Airlane airlane;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_airport", referencedColumnName = "id")
    private Airport airport;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private int capacity;

    @ManyToMany
    @JoinTable(
            name = "reserve_flights",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "id")
    )
    private List<Reserve> reserves;
}
