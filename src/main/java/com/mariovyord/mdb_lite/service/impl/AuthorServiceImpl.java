package com.mariovyord.mdb_lite.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.mapper.AuthorMapper;
import com.mariovyord.mdb_lite.mapper.PageMapper;
import com.mariovyord.mdb_lite.repository.AuthorRepository;
import com.mariovyord.mdb_lite.service.AuthorService;
import com.mariovyord.mdb_lite.util.AuthorSpecificationBuilder;
import com.mariovyord.mdb_lite.util.PagingUtil;

import de.dlh.lht.ti.model.AuthorDto;
import de.dlh.lht.ti.model.AuthorPageDto;
import de.dlh.lht.ti.model.AuthorPagingCriteria;
import de.dlh.lht.ti.model.AuthorQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final PageMapper pageMapper;

    @Override
    public AuthorPageDto getAuthors(AuthorQueryParams queryParams, AuthorPagingCriteria pagingCriteria) {
        Pageable pageable = PagingUtil.createPageable(
            pagingCriteria.getPageNumber(),
            pagingCriteria.getPageSize(),
            pagingCriteria.getSortDirection(),
            pagingCriteria.getSortColumnEnum().getValue(),
            "fullName" 
        );

        Specification<AuthorEntity> spec = new AuthorSpecificationBuilder()
            .withFullName(queryParams.getFullName())
            .build();
        
        Page<AuthorEntity> page = authorRepository.findAll(spec, pageable);

        return pageMapper.toAuthorPage(page);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        validateDto(authorDto);
        AuthorEntity authorEntity = authorMapper.toEntity(authorDto);
        authorEntity = authorRepository.save(authorEntity);
        
        return authorMapper.toDto(authorEntity);
    }

    @Override
    public AuthorDto updateAuthor(UUID authorId, AuthorDto authorDto) {
        validateDto(authorDto);

        AuthorEntity authorEntity = authorRepository.findById(authorId)
            .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));

        authorEntity.setFullName(authorDto.getFullName());

        authorEntity = authorRepository.save(authorEntity); 
        
        return authorMapper.toDto(authorEntity);
    }

    @Override
    public void deleteAuthor(UUID authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author not found with id: " + authorId);
        }
        authorRepository.deleteById(authorId);
    }   

    private void validateDto(AuthorDto authorDto) {
        if (authorDto.getFullName() == null || authorDto.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
    }
}
