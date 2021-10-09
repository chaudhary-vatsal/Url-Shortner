package com.test.urlshortener.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.urlshortener.model.UrlShortnerModel;
import com.test.urlshortener.service.UrlShortnerService;

@RestController
public class UrlShortnerController {

	@Autowired
	UrlShortnerService urlShortnerService;
  
	
	@PostMapping("/urlshortner")
	@ResponseBody
	public ResponseEntity shortenUrl(@RequestBody String url) {
		UrlShortnerModel shortUrlEntry = urlShortnerService.shortenUrl(url);
		return ResponseEntity.ok(shortUrlEntry.getKey());
	}

	
	@GetMapping("/key/{key}")
	@ResponseBody
	public void getUrl(@PathVariable String key, HttpServletResponse response) throws IOException {
		String url = urlShortnerService.getUrlByKey(key);
		response.sendRedirect(url);
	}

}
