package com.carbigdata.ms.core.domain.address;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int id;
    private String state;
    private String city;
    private String zipCode;
    private String district;

    private LocalDateTime createdAt;
}
