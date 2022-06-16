package com.shortener.url.repository;

import com.shortener.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Url findByUrlHash(@Param("url_hash") String urlHash);

    @Query(nativeQuery = true, value = "select user_num from user where user_id = :user_id")
    Long getUserNum(@Param("user_id") String userId);
}
