package com.example.reactive;

import com.example.reactive.dto.News;
import com.example.reactive.repository.NewsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@EnableScheduling
@SpringBootApplication
@EnableRedisRepositories
public class ReactiveApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner dataBaseInitial(NewsRepository newsRepository){
		// it can make deadlock
		return (p) -> {
			newsRepository.deleteAll().subscribe();
			newsRepository.save(new News( "그래 성일짱이 해냈군", Timestamp.valueOf(LocalDateTime.now()))).subscribe();
		};
	}

	 */
}

