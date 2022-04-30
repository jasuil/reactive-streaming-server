package com.example.reactive.controller;

import com.example.reactive.service.NewsPublisher;
import com.example.reactive.dto.News;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class NewsController {

    private final Flux<News> newsFlux;
    private final NewsPublisher newsPublisher;

    public NewsController(Flux<News> flux, NewsPublisher newsPublisher) {
        this.newsFlux = flux;
        this.newsPublisher = newsPublisher;
    }

    @PostMapping(value="/news")
    public Mono<Boolean> setNews(@RequestBody News news) {
        return newsPublisher.saveNews(news);
    }

    @GetMapping(value = "/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<News> getNews(){
        return newsFlux;
    }

}