package com.shortener.url.repository;

import com.shortener.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    @Query(nativeQuery = true, value = "select * from url where short_url = :url ")
    Url checkExistingUrl(@Param("url") String shorturl);
}
