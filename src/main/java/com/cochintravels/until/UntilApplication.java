package com.cochintravels.until;

import com.cochintravels.until.service.SheetToJsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class UntilApplication {

    public static void main(String[] args) {
        SpringApplication.run(UntilApplication.class, args);
    }

    @Bean
    public RestTemplate sheetRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}
