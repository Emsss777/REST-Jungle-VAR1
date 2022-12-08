package com.epetkov.restjungle.dao.interfaces;

import org.springframework.http.ResponseEntity;

public interface BaseRepDAO<T> {

    ResponseEntity<T> getOneByID(Integer id);

    ResponseEntity<T> getOneByName(String name);
}
