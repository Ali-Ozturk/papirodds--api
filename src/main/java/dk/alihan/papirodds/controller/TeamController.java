package dk.alihan.papirodds.controller;

import dk.alihan.papirodds.entity.Score;
import dk.alihan.papirodds.entity.Team;
import dk.alihan.papirodds.service.ScoreService;
import dk.alihan.papirodds.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Team>> latestScores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(teamService.getTeams());
    }
}
