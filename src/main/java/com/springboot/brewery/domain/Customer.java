package com.springboot.brewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
}

