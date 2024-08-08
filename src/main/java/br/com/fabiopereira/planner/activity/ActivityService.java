package br.com.fabiopereira.planner.activity;

import br.com.fabiopereira.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponse save(ActivityRequest activityRequest, Trip trip) {
        var activity = new Activity(activityRequest.title(), activityRequest.occursAt(), trip);
        activityRepository.save(activity);

        return new ActivityResponse(activity.getId());
    }

    public List<ActivityData> getAllActivitiesFromId(UUID id) {
        return activityRepository.findByTripId(id)
                .stream()
                .map(activity ->
                        new ActivityData(
                                activity.getId(),
                                activity.getTitle(),
                                activity.getOccursAt())
                ).toList();
    }
}
