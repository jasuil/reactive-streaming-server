package com.example.reactive.config;

import com.example.reactive.dto.News;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class AppConfiguration {

    @Bean
    public Sinks.Many<News> newsSink(){
        return Sinks.many().replay().latest();
    }

    @Bean
    public Flux<News> newsFlux(Sinks.Many<News> sink){
        return sink.asFlux().cache();
    }

}