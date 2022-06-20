package com.shortener.url.controller;

import com.shortener.url.constants.UrlConstants;
import com.shortener.url.mapper.UrlResponseVO;
import com.shortener.url.mapper.UrlShortenRequest;
import com.shortener.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author OMKAR
 */
@RestController
@RequestMapping(UrlConstants.RequestMappingConstants.URL_CONTROLLER_PATH)
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping(value = UrlConstants.RequestMappingConstants.URL_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlResponseVO> createShortUrl(@RequestBody UrlShortenRequest urlShortenRequest){
        return new ResponseEntity<>(urlService.createShortUrl(urlShortenRequest), HttpStatus.CREATED);
    }
}
