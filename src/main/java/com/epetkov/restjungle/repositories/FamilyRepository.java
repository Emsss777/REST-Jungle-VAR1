package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, Integer> {

    FamilyEntity findFamilyById(Integer id);

    FamilyEntity findFamilyByName(String name);
}
