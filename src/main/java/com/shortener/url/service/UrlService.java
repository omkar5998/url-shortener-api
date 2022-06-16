package com.shortener.url.service;

import com.google.common.hash.Hashing;
import com.shortener.url.constants.UrlConstants;
import com.shortener.url.domain.Url;
import com.shortener.url.mapper.UrlResponseVO;
import com.shortener.url.mapper.UrlShortenRequest;
import com.shortener.url.repository.UrlRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponseVO createShortUrl(UrlShortenRequest urlShortenRequest) {

        String urlHash = validateUrl(urlShortenRequest.getLongUrl());
        Url url = urlRepository.findByUrlHash(urlHash);

        if (ObjectUtils.isEmpty(url)) {
            return saveUrl(urlShortenRequest, urlHash);
        }
        return getFinalUrl(urlHash);
    }

    private UrlResponseVO getFinalUrl(String urlHash) {
        String stringBuilder = UrlConstants.RequestMappingConstants.GET_API_PATH + urlHash;
        return new UrlResponseVO(stringBuilder);
    }

    private Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, UrlConstants.OtherConstants.FREE_TIER_DURATION); // Free Tier Service
        return calendar;
    }

    private UrlResponseVO saveUrl(UrlShortenRequest urlShortenRequest, String urlHash) {

        Url url = new Url();
        url.setLongUrl(urlShortenRequest.getLongUrl());
        url.setUrlHash(urlHash);
        url.setCreatedStp(new Date());
        url.setExpirationStp(getCalendar().getTime());
        url.setUserNum(checkUser(urlShortenRequest.getUserId()));
        url = urlRepository.saveAndFlush(url);
        return getFinalUrl(url.getUrlHash());
    }

    public Long checkUser(String userId){
        if(ObjectUtils.isEmpty(userId))
            return -1L;
        return urlRepository.getUserNum(userId);

    }

    private String validateUrl(String longUrl) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(longUrl)) {
            return Hashing.murmur3_32_fixed().hashString(longUrl, StandardCharsets.UTF_8).toString();
        } else {
            throw new RuntimeException("Invalid Url " + longUrl);
        }
    }

    public String redirectUrl(String urlHash) throws MalformedURLException {
        Url url = urlRepository.findByUrlHash(urlHash);
        return url.getLongUrl();
    }
}
