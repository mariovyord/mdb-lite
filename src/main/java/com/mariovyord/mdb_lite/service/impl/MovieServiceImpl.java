package com.mariovyord.mdb_lite.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.MovieEntity;
import com.mariovyord.mdb_lite.mapper.MovieMapper;
import com.mariovyord.mdb_lite.mapper.PageMapper;
import com.mariovyord.mdb_lite.repository.MovieRepository;
import com.mariovyord.mdb_lite.service.MovieService;
import com.mariovyord.mdb_lite.util.PagingUtil;

import de.dlh.lht.ti.model.SortDirection;
import de.dlh.lht.ti.model.MovieDto;
import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final PageMapper pageMapper;

    @Override
    public MoviePageDto getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        Pageable pageable = PagingUtil.createPageable(
            pagingCriteria.getPageNumber(),
            pagingCriteria.getPageSize(),
            pagingCriteria.getSortDirection(),
            pagingCriteria.getSortColumn().getValue().toLowerCase(),
            "title" 
        );

        Specification<MovieEntity> spec = (root, query, builder) -> null;
        String title = movieQueryParams.getTitle();
        if (title != null && !title.isEmpty()) {
            spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }

        
        Page<MovieEntity> page = movieRepository.findAll(spec, pageable);

        return pageMapper.toMoviePage(page);
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        validateDto(movieDto);
        MovieEntity movieEntity = movieMapper.toEntity(movieDto);
        movieEntity = movieRepository.save(movieEntity);
        
        return movieMapper.toDto(movieEntity);
    }

    private void validateDto(MovieDto movieDto) {
        if (movieDto.getTitle() == null || movieDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
}
