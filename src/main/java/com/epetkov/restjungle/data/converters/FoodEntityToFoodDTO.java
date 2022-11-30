package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.FoodEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FoodEntityToFoodDTO implements Converter<FoodEntity, FoodDTO> {

    @Nullable
    @Override
    public FoodDTO convert(FoodEntity foodEntity) {

        final FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(foodEntity.getId());
        foodDTO.setName(foodEntity.getName());

        return foodDTO;
    }
}
