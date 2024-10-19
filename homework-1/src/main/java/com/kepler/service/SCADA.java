package com.kepler.service;

import com.kepler.exception.PLCConnectionException;
import com.kepler.model.Well;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * SCADA service for processing well data, saving it to the database,
 * and reading additional data from the PLC via OPC DA.
 */
@Service
public class SCADA {

    @Value("${modbus.register.flow}")
    private Integer registerFlow;

    @Value("${modbus.register.temperature}")
    private Integer registerTemperature;

    @Value("${modbus.register.pressure}")
    private Integer registerPressure;

    private final ModbusService modbusService;
    private final DatabaseService databaseService;
    private final OPCDAService opcdaService;

    public SCADA(ModbusService modbusService, DatabaseService databaseService, OPCDAService opcdaService) {
        this.modbusService = modbusService;
        this.databaseService = databaseService;
        this.opcdaService = opcdaService;
    }

    /**
     * Processes the well data, saves it to the database, and attempts to read PLC data.
     *
     * @throws PLCConnectionException if there is an issue with the PLC connection.
     */
    public void process() throws PLCConnectionException {
        Well well = retrieveWellDataFromModbus();
        saveWell(well);

        opcdaService.readPLC();
    }

    /**
     * Reads flow, temperature, and pressure values from Modbus registers.
     *
     * @return a {@link Well} object with the current sensor values.
     */
    private Well retrieveWellDataFromModbus() {
        Double flow = modbusService.readFlow(registerFlow);
        Double temperature = modbusService.readTemperature(registerTemperature);
        Double pressure = modbusService.readPressure(registerPressure);

        return Well.builder()
                .uuid(UUID.randomUUID())
                .dateTime(LocalDateTime.now())
                .flow(flow)
                .temperature(temperature)
                .pressure(pressure)
                .build();
    }

    /**
     * Saves the {@link Well} entity to the database.
     *
     * @param well the well data to be saved.
     */
    private void saveWell(Well well) {
        databaseService.saveRecord(well);
    }
}
