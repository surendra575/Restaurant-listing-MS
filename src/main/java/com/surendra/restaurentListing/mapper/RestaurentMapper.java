package com.surendra.restaurentListing.mapper;

import com.surendra.restaurentListing.dto.RestaurentDTO;
import com.surendra.restaurentListing.entity.RestaurentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurentMapper {
    RestaurentMapper INSTANCE=Mappers.getMapper(RestaurentMapper.class);

    RestaurentEntity mapRestaurentDTOToRestaurentEntity(RestaurentDTO restaurentDTO);
    RestaurentDTO mapRestaurentEntityToRestaurentDTO(RestaurentEntity restaurentEntity);
}
