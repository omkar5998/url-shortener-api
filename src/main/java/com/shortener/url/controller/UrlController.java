package com.shortener.url.controller;

import com.shortener.url.domain.Url;
import com.shortener.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;

/**
 * @author OMKAR
 */
@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping(value = "/url", produces = )
    public ResponseEntity<Url> createUrl(@RequestBody String longUrl){
        return new ResponseEntity(urlService.createUrl(longUrl), HttpStatus.CREATED);
    }

    @GetMapping("/{url}")
    public RedirectView redirectUrl(@PathVariable("url") String shortUrl) throws MalformedURLException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlService.redirectUrl(shortUrl));
        return redirectView;
    }
}
