package com.mariovyord.mdb_lite.util;

import org.springframework.data.jpa.domain.Specification;
import com.mariovyord.mdb_lite.entity.BookEntity;

public class BookSpecificationBuilder {
    private  Specification<BookEntity> spec = (root, query, builder) -> null;

    public BookSpecificationBuilder withTitle(String title) {
        if (title != null && !title.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        return this;
    }

    public BookSpecificationBuilder withAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
        }
        return this;
    }

    public Specification<BookEntity> build() {
        return spec;
    }
}