package com.test.urlshortener.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.test.urlshortener.model.UrlShortnerModel;
import com.test.urlshortener.repository.UrlShortnerRepo;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService{
	
	@Autowired
	UrlShortnerRepo urlShortnerRepo;

    @Override
    public String getUrlByKey(@NotBlank String key) {
    	UrlShortnerModel url = urlShortnerRepo.findRedirectUrl(key);
        return url.getUrl();
    }

    @Override
    public UrlShortnerModel shortenUrl(@NotBlank String url) {
     
        String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();

        UrlShortnerModel shortUrlEntry = UrlShortnerModel.builder().key(key).createdAt(LocalDateTime.now()).url(url).build();

         String msg = urlShortnerRepo.saveUrl(key,shortUrlEntry);
               return shortUrlEntry;
    }
    
	


	

	




}
