package com.kepler.service;

import com.kepler.exception.PLCConnectionException;

/**
 * Service interface for reading data from a PLC via OPC DA protocol.
 */
public interface OPCDAService {
    void readPLC() throws PLCConnectionException;
}
