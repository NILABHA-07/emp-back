package com.nilabha.EmployeeApp.EmployeeApp.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
     public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
