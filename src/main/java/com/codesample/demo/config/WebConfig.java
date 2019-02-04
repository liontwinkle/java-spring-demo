package com.codesample.demo.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class WebConfig {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        b.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return b;
    }
}
