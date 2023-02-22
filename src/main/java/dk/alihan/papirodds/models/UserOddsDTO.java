package dk.alihan.papirodds.models;

import dk.alihan.papirodds.entity.UserOdds;
import lombok.Data;

@Data
public class UserOddsDTO {
    private final Long id;
    private final Long userId;
    private final Boolean overThreshold;
    private final PlayerMatchThresholdsDTO odds;

    public UserOddsDTO(UserOdds userOdds) {
        this.id = userOdds.getId();
        this.userId = userOdds.getUser().getId();
        this.overThreshold = userOdds.getOverThreshold();
        this.odds = new PlayerMatchThresholdsDTO(userOdds.getOdds());
    }
}
