package com.mariovyord.mdb_lite.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.BookEntity;
import com.mariovyord.mdb_lite.mapper.BookMapper;
import com.mariovyord.mdb_lite.mapper.PageMapper;
import com.mariovyord.mdb_lite.repository.BookRepository;
import com.mariovyord.mdb_lite.service.BookService;
import com.mariovyord.mdb_lite.util.BookSpecificationBuilder;
import com.mariovyord.mdb_lite.util.PagingUtil;

import de.dlh.lht.ti.model.BookDto;
import de.dlh.lht.ti.model.BookPageDto;
import de.dlh.lht.ti.model.BookPagingCriteria;
import de.dlh.lht.ti.model.BookQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
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
    public BookDto createBook(BookDto bookDto) {
        validateDto(bookDto);
        BookEntity bookEntity = bookMapper.toEntity(bookDto);
        bookEntity = bookRepository.save(bookEntity);
        
        return bookMapper.toDto(bookEntity);
    }

    private void validateDto(BookDto bookDto) {
        if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
}
