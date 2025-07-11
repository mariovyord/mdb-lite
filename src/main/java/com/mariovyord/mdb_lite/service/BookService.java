package com.mariovyord.mdb_lite.service;

import java.util.UUID;

import de.dlh.lht.ti.model.BookCreateDto;
import de.dlh.lht.ti.model.BookDto;
import de.dlh.lht.ti.model.BookPageDto;
import de.dlh.lht.ti.model.BookPagingCriteria;
import de.dlh.lht.ti.model.BookQueryParams;

public interface BookService {
    BookPageDto getBooks(BookQueryParams queryParams, BookPagingCriteria pagingCriteria);
    BookDto createBook(BookCreateDto bookDto);
    BookDto updateBook(UUID bookId, BookCreateDto bookDto);
    void deleteBook(UUID bookId);
}
