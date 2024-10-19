package com.kepler.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingPointcut {
    /**
     * Pointcut for methods that read data from the PLC.
     */
    @Pointcut("execution(* *readPLC(..))")
    public void readPLCPointcut() {}
}
