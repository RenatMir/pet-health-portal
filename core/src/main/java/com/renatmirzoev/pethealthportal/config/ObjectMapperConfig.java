package com.renatmirzoev.pethealthportal.config;

import static com.renatmirzoev.pethealthportal.utils.JsonUtils.JSON_MAPPER;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

  @Bean
  ObjectMapper objectMapper() {
    return JSON_MAPPER;
  }
}
