package br.com.fabiopereira.planner.trip;

import java.util.List;

public record TripRequest(
        String destionation,
        String startsAt,
        String endsAt,
        List<String> emailsToInvite,
        String ownerName,
        String ownerEmail
) {
}
