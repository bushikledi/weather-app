package com.project.weather.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .messageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
}
