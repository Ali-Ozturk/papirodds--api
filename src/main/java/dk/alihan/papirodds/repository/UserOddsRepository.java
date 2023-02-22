package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Contest;
import dk.alihan.papirodds.entity.UserOdds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOddsRepository extends JpaRepository<UserOdds, Long> {
    Optional<List<UserOdds>> findAllByUserId(Long id);
    Optional<List<UserOdds>> findAllByOddsMatchId(Long id);
}
