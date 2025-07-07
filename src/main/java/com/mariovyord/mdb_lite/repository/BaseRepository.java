package com.mariovyord.mdb_lite.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, K extends Serializable> extends JpaRepository<T, K> {
    
}
