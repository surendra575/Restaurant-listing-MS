package com.surendra.restaurentListing.repo;

import com.surendra.restaurentListing.entity.RestaurentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurentRepo extends JpaRepository<RestaurentEntity,Integer> {
}
