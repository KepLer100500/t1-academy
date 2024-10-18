package com.kepler.exception;

/**
 * Custom exception thrown when there is an issue with the PLC connection,
 * indicating that the device is busy.
 */
public class PLCConnectionException extends Exception {
    public PLCConnectionException() {
        super("Device is busy");
    }
}
