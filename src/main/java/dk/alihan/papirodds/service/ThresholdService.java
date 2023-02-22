package dk.alihan.papirodds.service;

import dk.alihan.papirodds.models.PlayerMatchThresholdsDTO;
import dk.alihan.papirodds.entity.*;
import dk.alihan.papirodds.repository.PlayerMatchThresholdRepository;
import dk.alihan.papirodds.repository.UserOddsRepository;
import dk.alihan.papirodds.repository.UserRepository;
import dk.alihan.papirodds.request.OddsRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ThresholdService {
    private final PlayerMatchThresholdRepository playerMatchThresholdRepository;
    private final UserOddsRepository userOddsRepository;
    private final UserRepository userRepository;

    public List<PlayerMatchThresholdsDTO> getThresholds() {
        log.info("Getting thresholds");

        List<PlayerMatchThresholdsDTO> dtos = new ArrayList<>();

        List<PlayerMatchThreshold> thresholds = playerMatchThresholdRepository.findAll();
        for (PlayerMatchThreshold threshold : thresholds) {
            dtos.add(new PlayerMatchThresholdsDTO(threshold));
        }

        return dtos;
    }

    public List<PlayerMatchThresholdsDTO> getThresholdsByMatchId(Long id) {
        log.info("Getting thresholds by match id " + id);

        List<PlayerMatchThresholdsDTO> dtos = new ArrayList<>();

        List<PlayerMatchThreshold> thresholds = playerMatchThresholdRepository.findAllByMatchId(id);
        for (PlayerMatchThreshold threshold : thresholds) {
            dtos.add(new PlayerMatchThresholdsDTO(threshold));
        }

        return dtos;
    }

    public void createThreshold(MapMatch mapMatch, Player player, Double threshold) {
        log.info("Creating threshold");

        PlayerMatchThreshold pmt = playerMatchThresholdRepository.save(new PlayerMatchThreshold(null, threshold, player, mapMatch));
        new PlayerMatchThresholdsDTO(pmt);
    }

    public void createOdds(OddsRequest request) throws Exception {
        log.info("Creating Odds");

        Optional<User> user = userRepository.findById(request.getUserId());

        if (user.isPresent()) {
            UserOdds odds = new UserOdds(null, request.getThreshold(), user.get(), request.getIsOverThreshold());
            userOddsRepository.save(odds);
        } else {
            throw new Exception("Could not find user");
        }
    }
}
