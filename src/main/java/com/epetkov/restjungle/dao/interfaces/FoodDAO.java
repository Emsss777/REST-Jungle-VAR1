package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface FoodDAO extends BaseRepDAO<FoodDTO> {

    ResponseEntity<FoodDTO> createNewFood(FoodDTO foodDTO);

    ResponseEntity<Boolean> deleteFoodByName(String name);
}
