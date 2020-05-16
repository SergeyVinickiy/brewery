package com.springboot.brewery.web.mappers;

import com.springboot.brewery.domain.Beer;
import com.springboot.brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

}
