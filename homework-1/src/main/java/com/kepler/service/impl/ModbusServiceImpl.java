package com.kepler.service.impl;

import com.kepler.service.ModbusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementation of the {@code ModbusService} for reading flow, temperature, and pressure data.
 */
@Slf4j
@Service
public class ModbusServiceImpl implements ModbusService {

    /**
     * Simulates reading flow data from the Modbus register.
     *
     * @param registerFlow the register identifier for the flow.
     * @return a simulated flow value.
     */
    @Override
    public Double readFlow(Integer registerFlow) {
        return Math.round(ThreadLocalRandom.current().nextDouble(10000, 20000) * 100.0) / 100.0;
    }

    /**
     * Simulates reading temperature data from the Modbus register.
     *
     * @param registerTemperature the register identifier for the temperature.
     * @return a simulated temperature value.
     */
    @Override
    public Double readTemperature(Integer registerTemperature) {
        return Math.round(ThreadLocalRandom.current().nextDouble(0, 100) * 100.0) / 100.0;
    }

    /**
     * Simulates reading pressure data from the Modbus register.
     *
     * @param registerPressure the register identifier for the pressure.
     * @return a simulated pressure value.
     */
    @Override
    public Double readPressure(Integer registerPressure) {
        return Math.round(ThreadLocalRandom.current().nextDouble(100, 300) * 100.0) / 100.0;
    }
}
