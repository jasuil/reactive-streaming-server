package com.example.reactive;

import com.example.reactive.dto.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class ReactiveApplicationTests {
    @Autowired
    private ReactiveRedisOperations<String, News> newsOps;

    @Test
    public void redisTest() {
        News news1 = new News("결국 그런 거였어", Timestamp.valueOf(LocalDateTime.of(2022, 4, 28, 23, 50)));
        News news2 = new News("대단해요", Timestamp.valueOf(LocalDateTime.of(2022, 4, 29, 0, 5)));
        Flux.just(new Object[]{"1", news1},
                        new Object[]{"2", news2})
                .flatMap(value -> newsOps.opsForValue().set((String) value[0], (News) value[1])).subscribe();

        newsOps.opsForValue().get("1").subscribe(n -> Assert.isTrue(news1.getContent().equals(n.getContent()), "안돼에에~~"));
    }
}
