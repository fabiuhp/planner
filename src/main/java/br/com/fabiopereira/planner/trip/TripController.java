package br.com.fabiopereira.planner.trip;

import br.com.fabiopereira.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest tripRequest) {
        var trip = Trip.fromRecord(tripRequest);
        tripRepository.save(trip);
        participantService.registerParticipantsToEvent(tripRequest.emailsToInvite(), trip.getId());
        return ResponseEntity.ok(new TripResponse(trip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        Optional<Trip> trip = tripRepository.findById(id);

        return trip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
