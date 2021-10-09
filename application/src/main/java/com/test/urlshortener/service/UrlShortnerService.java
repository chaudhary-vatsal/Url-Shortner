package com.test.urlshortener.service;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import com.test.urlshortener.model.UrlShortnerModel;

@Service
public interface UrlShortnerService {
	
	public String getUrlByKey(@NotBlank String key);
    public UrlShortnerModel shortenUrl(@NotBlank String url);

}
