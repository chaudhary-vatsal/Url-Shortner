package com.test.urlshortener.repository;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.test.urlshortener.model.UrlShortnerModel;

@Repository
public class UrlShortnerRepo {
	
	

	private HashOperations hashOperations;

	private RedisTemplate redisTemplate;

	@Autowired
	public UrlShortnerRepo(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public String saveUrl(String key,UrlShortnerModel shortUrlEntry ) {
	
		redisTemplate.opsForValue().set(key, shortUrlEntry);
		
		return "URL SAVED";
	}

	public UrlShortnerModel findRedirectUrl(String key) {
	
		UrlShortnerModel abc  = (UrlShortnerModel) redisTemplate.opsForValue().get(key);
		return abc;
	}
	
	
	
	
	
	
}
