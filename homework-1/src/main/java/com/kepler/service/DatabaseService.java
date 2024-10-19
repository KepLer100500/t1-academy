package com.kepler.service;

import com.kepler.model.Well;

/**
 * Service interface for database operations on {@link Well} entities.
 */
public interface DatabaseService {
    void saveRecord(Well well);
}
