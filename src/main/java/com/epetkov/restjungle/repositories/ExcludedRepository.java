package com.epetkov.restjungle.repositories;

import com.epetkov.restjungle.data.entities.ExcludedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcludedRepository extends JpaRepository<ExcludedEntity, Integer>  {

}
