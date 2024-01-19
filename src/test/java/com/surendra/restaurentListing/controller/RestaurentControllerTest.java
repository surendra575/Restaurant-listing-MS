package com.surendra.restaurentListing.controller;

import com.surendra.restaurentListing.dto.RestaurentDTO;
import com.surendra.restaurentListing.service.RestaurentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class RestaurentControllerTest {

    @InjectMocks
    RestaurentController restaurentController;

    @Mock
    RestaurentService restaurentService;

    @BeforeEach
    public void setUp(){
        //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestFetchAllRestaurents(){
        //1A -> Arrange
        List<RestaurentDTO> actualRestaurants= Arrays.asList(new RestaurentDTO(1,"Restaurant-1","Gachbowli","Hyderabad","This is restauarant-1"),
                new RestaurentDTO(2,"Restaurant-2","Kukatpally","Hyderabad-West","This is restaurant-2"));
        Mockito.when(restaurentService.findAllRestaurents()).thenReturn(actualRestaurants);

        //2A -> Act
        ResponseEntity<List<RestaurentDTO>> listResponseEntity = restaurentController.fetchAllRestaurents();

        //3A -> Assess
        Assertions.assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        Assertions.assertEquals(actualRestaurants,listResponseEntity.getBody());
    }

    @Test
    public void TestAddRestaurant(){
        // 1A -> Arrange
        RestaurentDTO rt=new RestaurentDTO(1,"Restaurant-1","Gachbowli","Hyderabad","This is restauarant-1");
        Mockito.when(restaurentService.addRestaurentInDB(rt)).thenReturn(rt);
        // 2A -> Act
        ResponseEntity<RestaurentDTO> listResponseEntity = restaurentController.addRestaurant(rt);
        //3A -> Assess
        Assertions.assertEquals(HttpStatus.CREATED,listResponseEntity.getStatusCode());
        Assertions.assertEquals(rt,listResponseEntity.getBody());
        Mockito.verify(restaurentService,Mockito.times(1)).addRestaurentInDB(rt);

    }

    @Test
    public void TestfindRestaurentById(){
        // 1A -> Arrange
        RestaurentDTO rt=new RestaurentDTO(1,"Restaurant-1","Gachbowli","Hyderabad","This is restauarant-1");
        ResponseEntity<RestaurentDTO> re=new ResponseEntity<>(rt,HttpStatus.OK);
        Mockito.when(restaurentService.fetchRestaurantById(1)).thenReturn(re);

        // 2A -> Act
        ResponseEntity<RestaurentDTO> restaurentById = restaurentController.findRestaurentById(1);

        // 3A -> Assess
        Assertions.assertEquals(HttpStatus.OK, restaurentById.getStatusCode());
        Assertions.assertEquals(rt,restaurentById.getBody());
    }
}
