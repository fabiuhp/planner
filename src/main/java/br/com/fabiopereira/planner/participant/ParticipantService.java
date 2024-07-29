package br.com.fabiopereira.planner.participant;

import br.com.fabiopereira.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository repository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        repository.saveAll(participants);

        System.out.println(participants.get(0).getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip){
        var newParticipant = new Participant(email, trip);
        repository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }


    public void triggerConfirmedEmailToParticipants(UUID tripId) {
        //todo: disparar email
    }

    public void triggerConfirmedEmailToParticipant(String email) {
        //todo: disparar email
    }

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return repository.findByTripId(tripId)
                .stream()
                .map(participant ->
                        new ParticipantData(
                                participant.getId(),
                                participant.getName(),
                                participant.getEmail(),
                                participant.getIsConfirmed())
                ).toList();
    }
}
