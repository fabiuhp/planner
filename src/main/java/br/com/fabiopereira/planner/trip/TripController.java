package br.com.fabiopereira.planner.trip;

import br.com.fabiopereira.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody TripRequest tripRequest) {
        var trip = Trip.fromRecord(tripRequest);
        tripRepository.save(trip);
        participantService.registerParticipantsToEvent(tripRequest.emailsToInvite(), trip.getId());
        return ResponseEntity.ok("Sucesso");
    }
}
