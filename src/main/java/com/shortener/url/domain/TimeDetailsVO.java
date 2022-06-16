package com.shortener.url.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class TimeDetailsVO {

    @Column(name = "created_stp")
    private Date createdStp;

    @Column(name = "expiration_stp")
    private Date expirationStp;
}
