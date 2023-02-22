package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.UserPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOddsRepository extends JpaRepository<UserPrediction, Long> {
    Optional<List<UserPrediction>> findAllByUserId(Long id);
    Optional<List<UserPrediction>> findAllByOddsMapMatchMatchId(Long id);
}
