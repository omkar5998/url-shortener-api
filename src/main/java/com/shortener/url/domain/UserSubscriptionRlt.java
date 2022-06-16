package com.shortener.url.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "user_subscription_rlt")
public class UserSubscriptionRlt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long userSubsNum;

    @Column(name = "user_num")
    private Long userNum;

    @Column(name = "subsNum")
    private Long subsNum;

    @Column(name = "created_stp")
    private Date createdStp;

    @Column(name = "expiration_stp")
    private Date expirationStp;
}
