package com.utp.domain.repository;

public interface SaveRepository <T>{
    T save(T entity);
}
