package com.surendra.restaurentListing.service;

import com.surendra.restaurentListing.dto.RestaurentDTO;
import com.surendra.restaurentListing.entity.RestaurentEntity;
import com.surendra.restaurentListing.mapper.RestaurentMapper;
import com.surendra.restaurentListing.repo.RestaurentRepo;
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
import java.util.Optional;

public class RestaurentServiceTest {

    @InjectMocks
    RestaurentService restaurentService;

    @Mock
    RestaurentRepo restaurentRepo;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestFindAllRestaurents(){
        // 1A -> Arrange
        List<RestaurentEntity> restaurentEntities = Arrays.asList(new RestaurentEntity(1, "Restaurant-1", "Gachbowli", "Hyderabad", "This is restauarant-1"),
                new RestaurentEntity(2, "Restaurant-2", "Kukatpally", "Hyderabad-West", "This is restaurant-2"));
        Mockito.when(restaurentRepo.findAll()).thenReturn(restaurentEntities);

        // 2A -> Act
        List<RestaurentDTO> allRestaurents = restaurentService.findAllRestaurents();

        // 3A -> Assess
        Assertions.assertEquals(restaurentEntities.size(),allRestaurents.size());
        for(int i=0;i<restaurentEntities.size();i++){
           Assertions.assertEquals(RestaurentMapper.INSTANCE.mapRestaurentEntityToRestaurentDTO(restaurentEntities.get(i)),allRestaurents.get(i));
       }
    }

    @Test
    public void TestAddRestaurentInDB(){
        // 1A -> Arrange
        RestaurentDTO rd=new RestaurentDTO(0, "Restaurant-1", "Gachbowli", "Hyderabad", "This is restauarant-1");
        RestaurentEntity re= RestaurentMapper.INSTANCE.mapRestaurentDTOToRestaurentEntity(rd);
        Mockito.when(restaurentRepo.save(re)).thenReturn(re);

        // 2A -> Act
        RestaurentDTO restaurentDTO = restaurentService.addRestaurentInDB(rd);

        //3A -> Assess
        Assertions.assertEquals(rd,restaurentDTO);
        Mockito.verify(restaurentRepo, Mockito.times(1)).save(re);
    }

    @Test
    public void testFetchRestaurantById(){
        // 1A -> Arrange
        RestaurentDTO rt=new RestaurentDTO(1, "Restaurant-1", "Gachbowli", "Hyderabad", "This is restauarant-1");
        RestaurentEntity re = RestaurentMapper.INSTANCE.mapRestaurentDTOToRestaurentEntity(rt);

        Mockito.when(restaurentRepo.findById(1)).thenReturn(Optional.of(re));

        //2A -> Act
        ResponseEntity<RestaurentDTO> restaurentDTOResponseEntity = restaurentService.fetchRestaurantById(1);

        // 3A -> Assess
        Assertions.assertEquals(HttpStatus.OK, restaurentDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(rt.getId(),restaurentDTOResponseEntity.getBody().getId());

        Mockito.verify(restaurentRepo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testFetchRestaurantByIdNonExistingId(){
        //1A -> Arrange
        Mockito.when(restaurentRepo.findById(1)).thenReturn(Optional.empty());

        // 2A -> Act
        ResponseEntity<RestaurentDTO> restaurentDTOResponseEntity = restaurentService.fetchRestaurantById(1);

        // 3A -> Assess
        Assertions.assertEquals(HttpStatus.NOT_FOUND, restaurentDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(null,restaurentDTOResponseEntity.getBody());

        Mockito.verify(restaurentRepo, Mockito.times(1)).findById(1);
    }
}
