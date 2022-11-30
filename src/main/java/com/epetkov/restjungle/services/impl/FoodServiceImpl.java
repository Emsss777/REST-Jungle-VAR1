package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.FoodDtoToFoodEntity;
import com.epetkov.restjungle.data.converters.FoodEntityToFoodDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.FoodEntity;
import com.epetkov.restjungle.repositories.FoodRepository;
import com.epetkov.restjungle.services.interfaces.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;

@Service("foodService")
@Transactional
public class FoodServiceImpl implements FoodService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FoodRepository foodRepository;
    private final FoodEntityToFoodDTO foodEntityToFoodDTO;
    private final FoodDtoToFoodEntity foodDtoToFoodEntity;

    public FoodServiceImpl(FoodRepository foodRepository,
                           FoodEntityToFoodDTO foodEntityToFoodDTO,
                           FoodDtoToFoodEntity foodDtoToFoodEntity) {

        this.foodRepository = foodRepository;
        this.foodEntityToFoodDTO = foodEntityToFoodDTO;
        this.foodDtoToFoodEntity = foodDtoToFoodEntity;
    }

    @Override
    public ResponseEntity<FoodDTO> createNewFood(Integer id, String name) {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(id);
        foodDTO.setName(name);

        FoodEntity detachedFood = foodDtoToFoodEntity.convert(foodDTO);
        try {

            // Check if this Food NOT Exists in the DATABASE;
            FoodEntity confirmFood = foodRepository.findFoodById(Objects.requireNonNull(detachedFood).getId());
            if (confirmFood == null) {

                FoodEntity savedFood = foodRepository.save(detachedFood);
                FoodDTO returnedFood = foodEntityToFoodDTO.convert(savedFood);

                LOG.info("Saved Food ID: " + savedFood.getId() + " --> " + "Name: " + savedFood.getName());
                return new ResponseEntity<>(returnedFood, HttpStatus.OK);
            }

        } catch (Exception ex) {

            LOG.error("There is Already a Food with that Name!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
