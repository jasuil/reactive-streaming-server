package com.example.reactive;

import com.example.reactive.dto.News;
import com.example.reactive.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReactiveApplicationTests {

//	@Autowired
//	NewsRepository newsRepository;

	@Autowired
	private ReactiveRedisOperations<String, News> newsOps;
	/*
	void test1() {
		News news1 = new News("결국 그런 거였어", LocalDateTime.of(2022,4,28,23,50));
		News news2 = new News("대단해요", LocalDateTime.of(2022,4,29,0,5));
		newsRepository.save(news1).subscribe();
		newsRepository.save(news2).subscribe();

		List<String> ids = new ArrayList<>();
		List<News> newsList = newsRepository.findAll().toStream().collect(Collectors.toList());
		long count = 0l;
		// block으로 위 subscribe도 여기서 구독가능... non blocking이라~
		count = newsRepository.count().blockOptional().get();
		Assert.isTrue(count == 2, "그런건가 안되는 건가");
		Assert.isTrue(newsList.size() == 2, "그런건가 안되는 건가22");
	}
*/
	@Test
	public void redisTest(){
		News news1 = new News("결국 그런 거였어", Timestamp.valueOf(LocalDateTime.of(2022,4,28,23,50)));
		News news2 = new News("대단해요", Timestamp.valueOf(LocalDateTime.of(2022,4,29,0,5)));
		Flux.just(new Object[]{"1", news1},
				new Object[] {"2", news2})
				.flatMap(value -> newsOps.opsForValue().set((String) value[0], (News) value[1])).subscribe();

		News news3 = newsOps.opsForValue().get("1").block();
		Assert.isTrue(news3.getContent().equals(news1.getContent()), "안돼에에~~");
	}
}
