package br.com.fabiopereira.planner.trip;

import br.com.fabiopereira.planner.participant.ParticipantCreateResponse;
import br.com.fabiopereira.planner.participant.ParticipantData;
import br.com.fabiopereira.planner.participant.ParticipantRequest;
import br.com.fabiopereira.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        participantService.registerParticipantsToEvent(tripRequest.emailsToInvite(), trip);
        return ResponseEntity.ok(new TripResponse(trip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        return tripRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequest tripRequest) {
        return tripRepository.findById(id)
                .map(trip -> {
                    trip.setStartsAt(LocalDateTime.parse(tripRequest.startsAt(), DateTimeFormatter.ISO_DATE_TIME));
                    trip.setEndsAt(LocalDateTime.parse(tripRequest.endsAt(), DateTimeFormatter.ISO_DATE_TIME));
                    trip.setDestination(tripRequest.destionation());
                    tripRepository.save(trip);
                    participantService.triggerConfirmedEmailToParticipants(id);

                    return ResponseEntity.ok(trip);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        return tripRepository.findById(id)
                .map(trip -> {
                    trip.setConfirmed(true);
                    tripRepository.save(trip);
                    return ResponseEntity.ok(trip);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequest participantRequest) {
        return tripRepository.findById(id)
                .map(trip -> {
                    var response = participantService.registerParticipantToEvent(participantRequest.email(), trip);
                    if(trip.isConfirmed()) participantService.triggerConfirmedEmailToParticipant(participantRequest.email());

                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> getAllParticipants(@PathVariable UUID id){
        List<ParticipantData> participantList = participantService.getAllParticipantsFromEvent(id);

        return ResponseEntity.ok(participantList);
    }
}
