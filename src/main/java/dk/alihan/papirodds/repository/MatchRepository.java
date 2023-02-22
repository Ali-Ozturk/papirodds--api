package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByStartDateAfterOrderByStartDate(LocalDateTime beforeDate);
    List<Match> findAllByStartDateBeforeOrderByStartDate(LocalDateTime beforeDate);

    @Query("SELECT e FROM Match e WHERE e.startDate BETWEEN ?1 AND ?2")
    List<Match> findAllWhereStartBetweenEnd(LocalDateTime start, LocalDateTime end);
}
