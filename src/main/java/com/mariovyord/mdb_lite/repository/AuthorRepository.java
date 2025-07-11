package com.mariovyord.mdb_lite.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mariovyord.mdb_lite.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID>, JpaSpecificationExecutor<AuthorEntity> {
} 