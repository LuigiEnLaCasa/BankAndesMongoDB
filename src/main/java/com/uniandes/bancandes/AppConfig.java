package com.uniandes.bancandes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.uniandes.bancandes", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.uniandes.bancandes.*_old"))

public class AppConfig {
    
}
