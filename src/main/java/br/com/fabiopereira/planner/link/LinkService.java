package br.com.fabiopereira.planner.link;

import br.com.fabiopereira.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public LinkResponse save(LinkRequest linkRequest, Trip trip) {
        var link = new Link(linkRequest.title(), linkRequest.url(), trip);
        linkRepository.save(link);

        return new LinkResponse(link.getId());
    }

    public List<LinkData> getAllLinkFromId(UUID id) {
        return linkRepository.findByTripId(id)
                .stream()
                .map(link ->
                        new LinkData(
                                link.getId(),
                                link.getTitle(),
                                link.getUrl())
                ).toList();
    }
}
