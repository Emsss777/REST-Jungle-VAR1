package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.AnimalEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends BaseRepository<AnimalEntity, Integer> {

}
