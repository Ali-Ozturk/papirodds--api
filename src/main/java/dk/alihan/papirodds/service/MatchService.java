package dk.alihan.papirodds.service;

import dk.alihan.papirodds.entity.Match;
import dk.alihan.papirodds.repository.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MatchService {
    private final MatchRepository matchRepository;

    public List<Match> getActiveMatches() {
        log.info("Getting all active matches");
        return matchRepository.findAllByStartDateAfterOrderByStartDate(LocalDateTime.now());
    }

    public List<Match> getStartedMatches() {
        log.info("Getting matches for today");

        return matchRepository.findAllByStartDateBeforeOrderByStartDate(LocalDateTime.now());
    }

    public List<Match> getAllMatches() {
        log.info("Getting all matches");
        return matchRepository.findAll();
    }

    public Match createMatch(Match match) throws Exception {
        log.info("Creating a new match");
        return matchRepository.save(match);
    }

    public Mono<String> getHLTVMatches() {
        // Makes a call to the local HLTV-API and forwards the result

        WebClient webClient = WebClient.create("http://localhost:5000");
        return webClient.get().uri("/matches/")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getHLTVMatchById(Long id) {
        // Makes a call to the local HLTV-API and forwards the result

        WebClient webClient = WebClient.create("http://localhost:5000");

        return webClient.get().uri("/matches/" + id)
                .retrieve()
                .bodyToMono(String.class);
    }
}
