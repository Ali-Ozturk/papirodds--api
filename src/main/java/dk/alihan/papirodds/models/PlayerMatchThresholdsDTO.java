package dk.alihan.papirodds.models;

import dk.alihan.papirodds.entity.Player;
import dk.alihan.papirodds.entity.PlayerMatchThreshold;
import lombok.Data;

@Data
public class PlayerMatchThresholdsDTO {
    private final Long id;
    private final Double threshold;
    private final Player player;
    private final Long mapMatchId;
    private final Long matchId;

    public PlayerMatchThresholdsDTO(PlayerMatchThreshold threshold) {
        this.id = threshold.getId();
        this.threshold = threshold.getThreshold();
        this.player = threshold.getPlayer();
        this.mapMatchId = threshold.getMapMatch().getId();
        this.matchId = threshold.getMapMatch().getMatch().getId();
    }
}
