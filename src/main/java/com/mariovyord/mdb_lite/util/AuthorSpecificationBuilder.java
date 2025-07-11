package com.mariovyord.mdb_lite.util;

import org.springframework.data.jpa.domain.Specification;

import com.mariovyord.mdb_lite.entity.AuthorEntity;

public class AuthorSpecificationBuilder {
    private  Specification<AuthorEntity> spec = (root, query, builder) -> null;

    public AuthorSpecificationBuilder withFullName(String fullName) {
        if (fullName != null && !fullName.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
        }
        return this;
    }

    public Specification<AuthorEntity> build() {
        return spec;
    }
}