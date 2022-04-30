package com.example.reactive.config;

import com.example.reactive.dto.News;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class NewsRepositoryConfiguration {
    @Bean
    ReactiveRedisOperations<String, News> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<News> serializer = new Jackson2JsonRedisSerializer<>(News.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, News> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, News> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}