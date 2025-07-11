package com.mariovyord.mdb_lite.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.mapper.PageMapper;
import com.mariovyord.mdb_lite.repository.AuthorRepository;
import com.mariovyord.mdb_lite.service.AuthorService;
import com.mariovyord.mdb_lite.util.AuthorSpecificationBuilder;
import com.mariovyord.mdb_lite.util.PagingUtil;

import de.dlh.lht.ti.model.AuthorPageDto;
import de.dlh.lht.ti.model.AuthorPagingCriteria;
import de.dlh.lht.ti.model.AuthorQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
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
}
