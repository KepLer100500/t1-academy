package com.kepler.service.impl;

import com.kepler.model.Well;
import com.kepler.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for database operations on {@link Well} entities.
 */
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {

    /**
     * Saves the {@code Well} data into the database.
     * Logs the values of flow, temperature, and pressure.
     *
     * @param well the {@code Well} entity to be saved.
     */
    @Override
    public void saveRecord(Well well) {
        log.info("Сохранение. Расход: {} / Температура: {} / Давление: {}", well.getFlow(), well.getTemperature(), well.getPressure());
    }
}
