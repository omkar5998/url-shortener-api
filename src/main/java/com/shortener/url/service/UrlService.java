package com.shortener.url.service;

import com.google.common.hash.Hashing;
import com.shortener.url.domain.Url;
import com.shortener.url.repository.UrlRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public Url createUrl(String longUrl) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 5);

        String shortUrl = validateUrl(longUrl);
        Url url = urlRepository.checkExistingUrl(shortUrl);

        if (ObjectUtils.isEmpty(url))
            url = new Url();
        url = saveUrl(longUrl, calendar, shortUrl, url);
        return url;
    }

    private Url saveUrl(String longUrl, Calendar calendar, String shortUrl, Url url) {
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        url.setCreatedTime(new Date());
        url.setExpirationTime(calendar.getTime());
        url = urlRepository.saveAndFlush(url);
        url.setShortUrl("http://localhost:8080/" + shortUrl);
        return url;
    }

    private String validateUrl(String longUrl) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(longUrl)) {
            return Hashing.murmur3_32_fixed().hashString(longUrl, StandardCharsets.UTF_8).toString();
        } else {
            throw new RuntimeException("Invalid Url " + longUrl);
        }
    }

    public String redirectUrl(String shortUrl) throws MalformedURLException {
        Url url = urlRepository.checkExistingUrl(shortUrl);
        return url.getLongUrl();
    }
}
