package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

    AnimalEntity findAnimalByName(String name);
}
