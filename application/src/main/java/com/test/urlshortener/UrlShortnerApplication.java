package com.test.urlshortener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication(scanBasePackages = {"com.test.urlshortener" })
public class UrlShortnerApplication {

	@Value("${com.url.shortner.hostname}")
	String hostname;
	@Value("${com.url.shortner.port}")
	int port;
	

	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(hostname);
		jedisConFactory.setPort(port);
		jedisConFactory.getPoolConfig().setMaxIdle(60);
		jedisConFactory.getPoolConfig().setMinIdle(30);
		jedisConFactory.setTimeout(1000);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

}
