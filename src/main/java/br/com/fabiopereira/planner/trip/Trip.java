package br.com.fabiopereira.planner.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "trips")
@Data
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String destination;
    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;
    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;
    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    public static Trip fromRecord(TripRequest tripRequest) {
        return Trip.builder()
                .destination(tripRequest.destionation())
                .isConfirmed(false)
                .ownerEmail(tripRequest.ownerEmail())
                .ownerName(tripRequest.ownerName())
                .startsAt(LocalDateTime.parse(tripRequest.startsAt(), DateTimeFormatter.ISO_DATE_TIME))
                .endsAt(LocalDateTime.parse(tripRequest.endsAt(), DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
