package com.surendra.restaurentListing.service;

import com.surendra.restaurentListing.dto.RestaurentDTO;
import com.surendra.restaurentListing.entity.RestaurentEntity;
import com.surendra.restaurentListing.mapper.RestaurentMapper;
import com.surendra.restaurentListing.repo.RestaurentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RestaurentService {
    @Autowired
    RestaurentRepo repo;

    public List<RestaurentDTO> findAllRestaurents(){
        List<RestaurentEntity> restaurents = repo.findAll();
        List<RestaurentDTO> restaurentDTOS = restaurents.stream().map(restaurent -> RestaurentMapper.INSTANCE.mapRestaurentEntityToRestaurentDTO(restaurent))
                .collect(Collectors.toList());
        return restaurentDTOS;
    }

    public RestaurentDTO addRestaurentInDB(RestaurentDTO restaurentDTO){
        RestaurentEntity savedRestaurent = repo.save(RestaurentMapper.INSTANCE.mapRestaurentDTOToRestaurentEntity(restaurentDTO));
       return  RestaurentMapper.INSTANCE.mapRestaurentEntityToRestaurentDTO(savedRestaurent);
    }

    public ResponseEntity<RestaurentDTO> fetchRestaurantById(Integer id){
        Optional<RestaurentEntity> restaurent = repo.findById(id);
        if(restaurent.isPresent()){
            return  new ResponseEntity<>(RestaurentMapper.INSTANCE.mapRestaurentEntityToRestaurentDTO(restaurent.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
