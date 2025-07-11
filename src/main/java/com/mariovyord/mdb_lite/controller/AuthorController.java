package com.mariovyord.mdb_lite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mariovyord.mdb_lite.service.AuthorService;

import de.dlh.lht.ti.api.AuthorsApi;
import de.dlh.lht.ti.model.AuthorDto;
import de.dlh.lht.ti.model.AuthorPageDto;
import de.dlh.lht.ti.model.AuthorPagingCriteria;
import de.dlh.lht.ti.model.AuthorQueryParams;
import de.dlh.lht.ti.model.BookDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class  AuthorController implements AuthorsApi {

    private final AuthorService authorService;

    @Override
    public ResponseEntity<AuthorPageDto> getAuthors(AuthorQueryParams queryParams, AuthorPagingCriteria pagingCriteria) {
        AuthorPageDto authorPageDto = authorService.getAuthors(queryParams, pagingCriteria);
        return ResponseEntity.ok(authorPageDto);
    }

    @Override
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        AuthorDto createdAuthor = authorService.createAuthor(authorDto);
        return ResponseEntity.status(201).body(createdAuthor);
    }
}