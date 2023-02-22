package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByContestId(Long contestId);
    Optional<Score> findByUserIdAndContestId(Long userId, Long contestId);
}
