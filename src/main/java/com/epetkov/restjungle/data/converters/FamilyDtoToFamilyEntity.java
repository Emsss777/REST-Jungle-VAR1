package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.data.entities.FamilyEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FamilyDtoToFamilyEntity implements Converter<FamilyDTO, FamilyEntity> {

    @Nullable
    @Override
    public FamilyEntity convert(FamilyDTO familyDTO) {

        final FamilyEntity familyEntity = new FamilyEntity();
        familyEntity.setId(familyDTO.getId());
        familyEntity.setName(familyDTO.getName());

        return familyEntity;
    }
}
