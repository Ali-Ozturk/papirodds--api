package dk.alihan.papirodds.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.alihan.papirodds.entity.Contest;
import dk.alihan.papirodds.entity.Match;
import dk.alihan.papirodds.entity.Score;
import dk.alihan.papirodds.entity.UserOdds;
import dk.alihan.papirodds.repository.ContestRepository;
import dk.alihan.papirodds.repository.MatchRepository;
import dk.alihan.papirodds.repository.UserOddsRepository;
import dk.alihan.papirodds.service.MatchService;
import dk.alihan.papirodds.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    MatchService matchService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    UserOddsRepository userOddsRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    ContestRepository contestRepository;

    // @Scheduled(cron = "0 0/45 * * * *")
    @Scheduled(fixedDelay = 1000000) // todo: remove x2 0's for tests
    public void testSchedule() {
        log.info("Testing scheduler");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Get list of started matches that have not been validated yet
            List<Match> todaysUnvalidatedMatches = matchService.getStartedMatches()
                    .stream()
                    .filter(match -> !match.getValidated())
                    .toList();
            Contest contest = contestRepository.findFirstByOrderById();

            for (Match match : todaysUnvalidatedMatches) {
                // Fetch match stats from HLTV api
                Mono<String> result = matchService.getHLTVMatchById(match.getId());
                JsonNode rootNode = objectMapper.readTree(result.block());

                if (rootNode.has("state")) {
                    if (Objects.equals(rootNode.get("state").asText(), "Match over")) {
                        // Match has concluded
                        log.info("Match has concluded");

                        Optional<List<UserOdds>> allOdds = userOddsRepository.findAllByOddsMatchId(match.getId());

                        if (allOdds.isPresent()) {
                            System.out.println(allOdds.get());

                            // Iterate through all odds and update scores when correct vs. not correct
                            for (UserOdds odds : allOdds.get()) {
                                double threshold = odds.getOdds().getThreshold();
                                long oddsPlayerId = odds.getOdds().getPlayer().getId();
                                boolean correctPrediction = false; // Default value

                                // Iterate JsonNode from match details to find the player that have been odds' on
                                JsonNode teams = rootNode.path("teams");
                                for (JsonNode teamNode : teams) {
                                    JsonNode playersNode = teamNode.get("players");
                                    for (JsonNode playerNode : playersNode) {
                                        long playerId = playerNode.get("id").asLong();
                                        if (playerId == oddsPlayerId) {
                                            // We found the player we are looking for
                                            correctPrediction = odds.getOverThreshold() ? playerNode.get("kills").asInt() > threshold : playerNode.get("kills").asInt() < threshold;
                                            break;
                                        }
                                    }
                                }

                                if (correctPrediction) {
                                    // User predicted correct, we have to update his score now
                                    Optional<Score> userScore = scoreService.getScoreByUserIdAndContestId(odds.getUser().getId(), contest.getId());

                                    if (userScore.isPresent()) { // Check if user already have a score for this contest
                                        int newScore = userScore.get().getScore() + 1;
                                        userScore.get().setScore(newScore);
                                        scoreService.save(userScore.get());
                                    } else {
                                        // User does not have an entry in db, we create one
                                        Score score = new Score(null, odds.getUser(), contest, 1);
                                        scoreService.save(score);
                                    }
                                }

                            }
                        }

                        // Update validation state for the match to determine that it has been checked
                        match.setValidated(true);
                        matchRepository.save(match);
                    } else {
                        log.info("Match has not yet concluded, continuing..");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * We cache many of the HLTV requests so that we don't send too many requests,
     * we want to be able to evict those at a fixed interval
     */
    @Scheduled(fixedRate = 600000)
    public void evictAllCachesAtInterval() {
        log.info("Clearing Caches");
        cacheManager.getCacheNames()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
