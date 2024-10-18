package com.kepler.config;

import org.springframework.context.annotation.*;


/**
 * Spring configuration class for loading properties and configuring beans.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class Config {

}
