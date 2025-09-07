package com.utp.domain.repository;

import java.util.List;
import java.util.Optional;

public interface ReadRepository<T>{
    List<T> findAll();
    Optional<T> findById(Integer id);
}
