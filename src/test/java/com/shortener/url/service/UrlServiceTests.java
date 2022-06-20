package com.shortener.url.service;

import com.shortener.url.constants.TestConstants;
import com.shortener.url.domain.Url;
import com.shortener.url.framework.BusinessException;
import com.shortener.url.mapper.UrlResponseVO;
import com.shortener.url.mapper.UrlShortenRequest;
import com.shortener.url.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTests {
    @Mock
    UrlRepository urlRepository;

    private UrlService urlService;

    @BeforeEach
    void setup(){
        urlService = new UrlService(urlRepository);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Create Short Url: Unique Url")
    void createShortUrl() {
        given(urlRepository.findByUrlHash(TestConstants.TEST_URL_HASH)).willReturn(null);
        when(urlRepository.saveAndFlush(Mockito.any(Url.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        UrlResponseVO urlResponse = urlService.createShortUrl(TestConstants.getUrlShortenRequest());

        assertEquals(TestConstants.getUrlResponse(), urlResponse);
    }

    @Test
    @DisplayName("Create Short Url : Duplicate Url")
    void createShortUrlDuplicateUrl() {
        given(urlRepository.findByUrlHash(TestConstants.TEST_URL_HASH)).willReturn(TestConstants.getUrlVO());

        UrlResponseVO urlResponse = urlService.createShortUrl(TestConstants.getUrlShortenRequest());

        assertEquals(TestConstants.getUrlResponse(), urlResponse);
    }

    @Test
    @DisplayName("Create Short Url : Invalid Url")
    void createShortUrlInvalidUrl() {

        assertThrows(RuntimeException.class, () -> {
            urlService.createShortUrl(new UrlShortenRequest("http", ""));
        });
    }
}
