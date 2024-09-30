package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.exception.JourneyDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.exception.StationDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import cat.tecnocampus.fgcstations.domain.Station;
import cat.tecnocampus.fgcstations.persistence.JourneyRepository;
import cat.tecnocampus.fgcstations.persistence.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FgcJouneyService {
    private final JourneyRepository journeyRepository;
    private final StationRepository stationRepository;

    public FgcJouneyService(JourneyRepository journeyRepository, StationRepository stationRepository) {
        this.journeyRepository = journeyRepository;
        this.stationRepository = stationRepository;
    }

    public List<Journey> getAllJourneysDomain() {
        //TODO 6: get all stations (see you return a domain Journey). Actually, you don't need to leave this file
        // in order to complete this exercise
        return journeyRepository.findAll();
    }

    public List<JourneyDTO> getAllJourneysDTO() {
        //TODO 7: get all journeys (see the returned type)
        return journeyRepository.findAllDTO();
    }

    public Journey getJourneyDomain(String origin, String destination) {
        // TODO 8: get a journey by origin and destination (domain). If the journey does not exist, throw a JourneyDoesNotExistsException
        //  try no to use any sql (jpql) query, just come up with an appropriate method name
        // FIXME: Can I create a method to avoid the boilerplate?

        return journeyRepository.findJourneyByOriginNameAndDestinationName(origin, destination)
                .orElseThrow(() -> new JourneyDoesNotExistsException(origin, destination));
    }

    public JourneyId getJourneyId(String origin, String destination) {
        // TODO 9: get a journey ID by origin and destination (domain JourneyId). If the journey does not exist, throw a JourneyDoesNotExistsException
        //  try no to use any sql (jpql) query, just come up with an appropriate method name
        JourneyId journeyId = new JourneyId(origin, destination);
        journeyRepository.findById(journeyId)
                .orElseThrow(() -> new JourneyDoesNotExistsException(origin, destination));
        return journeyId;
    }
}
