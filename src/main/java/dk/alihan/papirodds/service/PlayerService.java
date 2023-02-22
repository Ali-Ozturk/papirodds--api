package dk.alihan.papirodds.service;

import dk.alihan.papirodds.entity.Player;
import dk.alihan.papirodds.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional
    public Player createPlayer(Player player) {
        log.info("Creating a new player");
        return playerRepository.save(player);
    }

    @Transactional
    public List<Player> createAll(List<Player> players) {
        log.info("Saving all players");
        return playerRepository.saveAll(players);
    }
}
