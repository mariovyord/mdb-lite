package com.mariovyord.mdb_lite.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mariovyord.mdb_lite.service.BookService;

import de.dlh.lht.ti.api.BooksApi;
import de.dlh.lht.ti.model.BookCreateDto;
import de.dlh.lht.ti.model.BookDto;
import de.dlh.lht.ti.model.BookPageDto;
import de.dlh.lht.ti.model.BookPagingCriteria;
import de.dlh.lht.ti.model.BookQueryParams;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class  BookController implements BooksApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<BookPageDto> getBooks(BookQueryParams queryParams, BookPagingCriteria pagingCriteria) {
        BookPageDto bookPageDto = bookService.getBooks(queryParams, pagingCriteria);
        return ResponseEntity.ok(bookPageDto);
    }

    @Override
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookCreateDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return ResponseEntity.status(201).body(createdBook);
    }

    @Override
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}