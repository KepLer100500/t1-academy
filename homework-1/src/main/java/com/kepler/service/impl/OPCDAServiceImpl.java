package com.kepler.service.impl;

import com.kepler.annotation.LogErrorExecution;
import com.kepler.exception.PLCConnectionException;
import com.kepler.service.OPCDAService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@code OPCDAService} for reading PLC data via OPC DA protocol.
 */
@Service
public class OPCDAServiceImpl implements OPCDAService {

    /**
     * Attempts to read data from the PLC. Throws a {@link PLCConnectionException} if the device is busy.
     *
     * @throws PLCConnectionException when the PLC is busy.
     */
    @Override
    @LogErrorExecution
    public void readPLC() throws PLCConnectionException {
        throw new PLCConnectionException();
    }
}
