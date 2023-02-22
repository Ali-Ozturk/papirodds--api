package dk.alihan.papirodds.controller;

import dk.alihan.papirodds.entity.Score;
import dk.alihan.papirodds.service.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@AllArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Score>> latestScores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scoreService.getScores());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<Score>> latestScoresByContestId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scoreService.getScoresByContestId(id));
    }
}
