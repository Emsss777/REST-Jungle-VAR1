package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer>  {

    FoodEntity findFoodById(Integer id);
}
