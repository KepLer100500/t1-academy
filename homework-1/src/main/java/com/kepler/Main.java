package com.kepler;

import com.kepler.exception.PLCConnectionException;
import com.kepler.service.SCADA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Main entry point of the application.
 * It initializes the Spring context and triggers the SCADA process.
 */
@Slf4j
@ComponentScan
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) throws PLCConnectionException {
        var context = new AnnotationConfigApplicationContext(Main.class);
        SCADA scada = context.getBean(SCADA.class);
        scada.process();
    }
}
