package com.mariovyord.mdb_lite.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.mariovyord.mdb_lite.entity.MovieEntity;

@Repository
public interface MovieRepository extends BaseRepository<MovieEntity, UUID> {
} 