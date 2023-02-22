package dk.alihan.papirodds.controller;

import dk.alihan.papirodds.entity.Match;
import dk.alihan.papirodds.entity.MapMatch;
import dk.alihan.papirodds.entity.Player;
import dk.alihan.papirodds.enumtype.MapType;
import dk.alihan.papirodds.models.MapsAndThresholds;
import dk.alihan.papirodds.request.NewMatchRequest;
import dk.alihan.papirodds.service.MatchService;
import dk.alihan.papirodds.service.PlayerService;
import dk.alihan.papirodds.service.TeamService;
import dk.alihan.papirodds.service.ThresholdService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
@AllArgsConstructor
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final ThresholdService thresholdService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Match>> allActiveMatches() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getActiveMatches());
    }

    @GetMapping("/started")
    @ResponseBody
    public ResponseEntity<List<Match>> allStartedMatches() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getStartedMatches());
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Match>> allMatches() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getAllMatches());
    }

    @GetMapping("/hltv")
    @ResponseBody
    @Cacheable(cacheNames = {"HLTVAllMatchesCache"})
    public ResponseEntity<String> getHLTVMatches() {
        Mono<String> result = matchService.getHLTVMatches();
        return result.map(r -> new ResponseEntity<>(r, HttpStatus.OK)).block();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Cacheable(cacheNames = {"HLTVMatchCache"}, key = "#id")
    public ResponseEntity<String> getHLTVMatchById(@PathVariable Long id) {
        Mono<String> result = matchService.getHLTVMatchById(id);
        return result.map(r -> new ResponseEntity<>(r, HttpStatus.OK)).block();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Match> createMatch(@RequestBody NewMatchRequest request) {
        try {
            Match match = request.getMatch();

            // Generate list of all players and add them to db
            ArrayList<Player> players = new ArrayList<>() {
                {
                    addAll(match.getTeamHome().getPlayers());
                    addAll(match.getTeamAway().getPlayers());
                }
            };
            for (Player player : players) {
                playerService.createPlayer(player);
            }

            // Create Teams
            teamService.createTeam(match.getTeamHome());
            teamService.createTeam(match.getTeamAway());

            //TODO: Temp fix for date issues
            match.setStartDate(match.getStartDate().plusHours(1));

            // Create Match
            matchService.createMatch(match);

            int mapsCount = match.getMaps().size();

            // Add Maps and Thresholds
            for (MapsAndThresholds mapsAndThresholds : request.getMapsAndThresholds()) {
                MapMatch mapMatch = new MapMatch(null, match.getMaps().get(), mapsAndThresholds.getMapNumber(), match);
                thresholdService.createThreshold(match.getMaps(), player, player.getThreshold());
            }



            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(match);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }
}
