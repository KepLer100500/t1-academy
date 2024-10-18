package com.kepler.service;

/**
 * Service interface for interacting with Modbus to read sensor values.
 */
public interface ModbusService {
    Double readFlow(Integer registerFlow);
    Double readTemperature(Integer registerTemperature);
    Double readPressure(Integer registerPressure);
}
