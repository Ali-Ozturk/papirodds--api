package dk.alihan.papirodds.service;

import dk.alihan.papirodds.entity.Team;
import dk.alihan.papirodds.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getTeams() {
        log.info("Getting teams");
        return teamRepository.findAll();
    }

    public Team createTeam(Team team) throws Exception {
        log.info("Creating a new team");
        return teamRepository.save(team);
    }
}
