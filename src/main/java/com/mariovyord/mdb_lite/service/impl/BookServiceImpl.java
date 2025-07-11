package com.mariovyord.mdb_lite.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.entity.BookEntity;
import com.mariovyord.mdb_lite.mapper.BookMapper;
import com.mariovyord.mdb_lite.mapper.PageMapper;
import com.mariovyord.mdb_lite.repository.AuthorRepository;
import com.mariovyord.mdb_lite.repository.BookRepository;
import com.mariovyord.mdb_lite.service.BookService;
import com.mariovyord.mdb_lite.util.BookSpecificationBuilder;
import com.mariovyord.mdb_lite.util.PagingUtil;

import de.dlh.lht.ti.model.BookCreateDto;
import de.dlh.lht.ti.model.BookDto;
import de.dlh.lht.ti.model.BookPageDto;
import de.dlh.lht.ti.model.BookPagingCriteria;
import de.dlh.lht.ti.model.BookQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final PageMapper pageMapper;

    @Override
    public BookPageDto getBooks(BookQueryParams queryParams, BookPagingCriteria pagingCriteria) {
        Pageable pageable = PagingUtil.createPageable(
            pagingCriteria.getPageNumber(),
            pagingCriteria.getPageSize(),
            pagingCriteria.getSortDirection(),
            pagingCriteria.getSortColumnEnum().getValue(),
            "title" 
        );

        Specification<BookEntity> spec = new BookSpecificationBuilder()
            .withTitle(queryParams.getTitle())
            .build();
        
        Page<BookEntity> page = bookRepository.findAll(spec, pageable);

        return pageMapper.toBookPage(page);
    }

    @Override
    public BookDto createBook(BookCreateDto bookDto) {
        validateDto(bookDto);

        List<AuthorEntity> authors = getAuthorsFromDto(bookDto);

        BookEntity bookEntity = bookMapper.toEntity(bookDto, authors);
        bookEntity = bookRepository.save(bookEntity);
        
        return bookMapper.toDto(bookEntity);
    }

    @Override
    public void deleteBook(UUID bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }


    private void validateDto(BookCreateDto bookDto) {
        if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    private List<AuthorEntity> getAuthorsFromDto(BookCreateDto bookDto) {
        List<UUID> authorIds = bookDto.getAuthorIds();
        if (authorIds == null || authorIds.isEmpty()) {
            throw new IllegalArgumentException("Book must have at least one author");
        }

        List<AuthorEntity> authors = authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new IllegalArgumentException("Some authors not found");
        }

        return authors;
    }
}
