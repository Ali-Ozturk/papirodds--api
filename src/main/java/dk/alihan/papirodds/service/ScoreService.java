package dk.alihan.papirodds.service;

import dk.alihan.papirodds.entity.Contest;
import dk.alihan.papirodds.entity.Score;
import dk.alihan.papirodds.repository.ScoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public List<Score> getScores() {
        log.info("Getting scores");
        return scoreRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    }

    public List<Score> getScoresByContestId(Long contestId) {
        log.info("Getting scores");

        return scoreRepository.findAllByContestId(contestId);
    }

    public Optional<Score> getScoreByUserIdAndContestId(Long userId, Long contestId) {
        log.info("Getting score for specific user and contest");

        return scoreRepository.findByUserIdAndContestId(userId, contestId);
    }

    @Transactional
    public void save(Score score) {
        log.info("Saving score");
        scoreRepository.save(score);
    }
}
