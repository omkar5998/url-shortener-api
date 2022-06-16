package com.shortener.url.constants;

import com.shortener.url.domain.Url;
import com.shortener.url.mapper.UrlResponseVO;
import com.shortener.url.mapper.UrlShortenRequest;

import java.util.Calendar;
import java.util.Date;

public class TestConstants {

    public static final String TEST_LONG_URL = "https://www.linkedin.com/in/omkrw/";

    public static final String TEST_USER_ID = "omkar.warghade";

    public static final String TEST_SHORTEN_URL = "http://localhost:8080/api/v1/urls/url/9aa3d19b";

    public static final String URL_PATH = "/url";

    public static final String TEST_URL_HASH = "9aa3d19b";

    public static final Long TEST_USER_NUM = 1L;

    public static UrlShortenRequest getUrlShortenRequest() {
        return new UrlShortenRequest(TEST_LONG_URL,TEST_USER_ID);
    }

    public static UrlResponseVO getUrlResponse() {
        return new UrlResponseVO(TEST_SHORTEN_URL);
    }

    public static Url getUrlVO() {
        return new Url(1L, TEST_LONG_URL, TEST_URL_HASH, TEST_USER_NUM, new Date(), getCalendar().getTime());
    }

    private static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, UrlConstants.OtherConstants.FREE_TIER_DURATION); // Free Tier Service
        return calendar;
    }
}
