package com.surendra.restaurentListing.controller;

import com.surendra.restaurentListing.dto.RestaurentDTO;
import com.surendra.restaurentListing.service.RestaurentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurent")
@CrossOrigin
public class RestaurentController {
    @Autowired
    RestaurentService restaurentService;

    @GetMapping("/fetchAllRestaurents")
    public ResponseEntity<List<RestaurentDTO>> fetchAllRestaurents(){
        List<RestaurentDTO> allRestaurents= restaurentService.findAllRestaurents();
        return  new ResponseEntity<>(allRestaurents, HttpStatus.OK);
    }
    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurentDTO> addRestaurant(@RequestBody RestaurentDTO restaurentDTO){
        RestaurentDTO restaurantAdded = restaurentService.addRestaurentInDB(restaurentDTO);
        return new ResponseEntity<>(restaurantAdded,HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurentDTO> findRestaurentById(@PathVariable Integer id){
        return restaurentService.fetchRestaurantById(id);
    }
}
