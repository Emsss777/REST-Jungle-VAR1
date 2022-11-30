package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.FoodEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends BaseRepository<FoodEntity, Integer>  {

}
