package br.com.fabiopereira.planner.activities;

import br.com.fabiopereira.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponse save(ActivityRequest activityRequest, Trip trip) {
        var activity = new Activity(activityRequest.title(), activityRequest.occursAt(), trip);
        activityRepository.save(activity);

        return new ActivityResponse(activity.getId());
    }
}
