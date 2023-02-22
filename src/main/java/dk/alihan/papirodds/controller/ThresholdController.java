package dk.alihan.papirodds.controller;

import dk.alihan.papirodds.models.PlayerMatchThresholdsDTO;
import dk.alihan.papirodds.request.OddsRequest;
import dk.alihan.papirodds.service.ThresholdService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
@AllArgsConstructor
public class ThresholdController {
    private final ThresholdService thresholdService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PlayerMatchThresholdsDTO>> allThresholds() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(thresholdService.getThresholds());
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> createOdds(@RequestBody OddsRequest request) {
        try {
            thresholdService.createOdds(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successfully added odds");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/{matchId}")
    @ResponseBody
    public ResponseEntity<List<PlayerMatchThresholdsDTO>> thresholdsByMatchId(@PathVariable Long matchId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(thresholdService.getThresholdsByMatchId(matchId));
    }
}
