package com.springboot.brewery.web.controller;


import com.springboot.brewery.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/api/v1/beer")
public class BeerController {

    @GetMapping({"/{beerId}"})
    public ResponseEntity getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(BeerDto.builder().build(),HttpStatus.OK );

    }

}
