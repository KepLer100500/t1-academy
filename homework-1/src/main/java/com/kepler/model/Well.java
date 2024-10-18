package com.kepler.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a Well with associated measurements like flow, temperature, and pressure.
 */
@Data
@Builder
public class Well {
    private UUID uuid;
    private LocalDateTime dateTime;
    private Double flow;
    private Double temperature;
    private Double pressure;
}
