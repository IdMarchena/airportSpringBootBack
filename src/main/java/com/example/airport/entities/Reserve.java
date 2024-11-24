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
@Table(name = "reserves")
public class Reserve {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column
    private LocalDateTime reservationDate;

    @Column
    private int numberOfSeats;

    @OneToMany(mappedBy = "reserve", fetch = FetchType.LAZY)
    private List<Passenger> passengers;

    @ManyToMany(mappedBy = "reserves", fetch = FetchType.LAZY)
    private List<Flight> flights;
}
