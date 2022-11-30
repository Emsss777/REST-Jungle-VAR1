package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.data.entities.FamilyEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FamilyEntityToFamilyDTO implements Converter<FamilyEntity, FamilyDTO> {

    @Nullable
    @Override
    public FamilyDTO convert(FamilyEntity familyEntity) {

        final FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setId(familyEntity.getId());
        familyDTO.setName(familyEntity.getName());

        return familyDTO;
    }
}
