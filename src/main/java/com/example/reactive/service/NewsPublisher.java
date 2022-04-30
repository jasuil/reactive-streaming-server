package com.example.reactive.service;

import com.example.reactive.dto.News;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.stream.Stream;

@Service
public class NewsPublisher {

    public static News news;
    private final ReactiveRedisOperations<String, News> newsOps;
    private final Sinks.Many<News> sink;



    public NewsPublisher(ReactiveRedisOperations<String, News> newsOps, Sinks.Many<News> sink) {
        this.newsOps = newsOps;
        this.sink = sink;
        this.news = new News();
    }

    @Scheduled(fixedRate = 3000)
    public void getNews(){
        newsOps.opsForValue().get("news").flux().subscribe(this.sink::tryEmitNext);
    }

    public Mono<Boolean> saveNews(News news) {
        return newsOps.opsForValue().set("news", news, Duration.ofSeconds(30));
    }

}
