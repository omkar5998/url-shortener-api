package com.shortener.url.controller;

import com.shortener.url.UrlShortenerApiApplicationTests;
import com.shortener.url.constants.TestConstants;
import com.shortener.url.service.UrlService;
import com.shortener.url.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UrlControllerTests extends UrlShortenerApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService urlService;

    @BeforeEach
    void Setup() {
    }

    @Test
    @DisplayName("Create Short Url")
    void createShortUrl() throws Exception {

        when(urlService.createShortUrl(TestConstants.getUrlShortenRequest())).thenReturn(TestConstants.getUrlResponse());

        mvc.perform(post(TestConstants.URL_PATH).content(TestUtil.convertObjectToJsonBytes(TestConstants.getUrlResponse())).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
