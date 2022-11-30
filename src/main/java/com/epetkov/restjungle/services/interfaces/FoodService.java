package com.epetkov.restjungle.services.interfaces;

import com.epetkov.restjungle.data.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface FoodService {

    ResponseEntity<FoodDTO> createNewFood(Integer id, String name);
}
