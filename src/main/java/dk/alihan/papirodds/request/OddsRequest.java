package dk.alihan.papirodds.request;

import dk.alihan.papirodds.entity.MapMatch;
import dk.alihan.papirodds.entity.PlayerMatchThreshold;
import lombok.Data;

@Data
public class OddsRequest {
    private PlayerMatchThreshold threshold;
    private Long userId;
    private MapMatch mapMatch;
    private Boolean isOverThreshold;
}
