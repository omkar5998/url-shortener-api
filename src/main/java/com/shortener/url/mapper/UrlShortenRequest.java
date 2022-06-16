package com.shortener.url.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortenRequest implements Serializable {

    private String longUrl;

    private String userId;

}
