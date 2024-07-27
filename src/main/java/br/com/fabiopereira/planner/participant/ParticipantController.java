package br.com.fabiopereira.planner.participant;

import br.com.fabiopereira.planner.trip.Trip;
import br.com.fabiopereira.planner.trip.TripRepository;
import br.com.fabiopereira.planner.trip.TripRequest;
import br.com.fabiopereira.planner.trip.TripResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequest participantRequest) {
        return participantRepository.findById(id).map(p -> {
            p.setIsConfirmed(true);
            p.setName(participantRequest.name());
            participantRepository.save(p);

            return ResponseEntity.ok(p);
        }).orElseGet(() ->ResponseEntity.notFound().build());
    }
}
