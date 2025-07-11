package com.mariovyord.mdb_lite.util;

import org.springframework.data.jpa.domain.Specification;
import com.mariovyord.mdb_lite.entity.MovieEntity;

public class MovieSpecificationBuilder {
    private  Specification<MovieEntity> spec = (root, query, builder) -> null;

    public MovieSpecificationBuilder withTitle(String title) {
        if (title != null && !title.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        return this;
    }

    public MovieSpecificationBuilder withAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
        }
        return this;
    }

    public Specification<MovieEntity> build() {
        return spec;
    }
}