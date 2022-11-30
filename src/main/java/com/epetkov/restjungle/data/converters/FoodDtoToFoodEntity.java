package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.FoodEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FoodDtoToFoodEntity implements Converter<FoodDTO, FoodEntity> {

    @Nullable
    @Override
    public FoodEntity convert(FoodDTO foodDTO) {

        final FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(foodDTO.getId());
        foodEntity.setName(foodDTO.getName());

        return foodEntity;
    }
}
