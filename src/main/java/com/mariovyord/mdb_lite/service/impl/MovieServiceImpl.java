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
import com.mariovyord.mdb_lite.repository.MovieRepository;
import com.mariovyord.mdb_lite.service.MovieService;

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

    @Override
    public MoviePageDto getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        Integer pageNumber = pagingCriteria.getPageNumber() != null ? pagingCriteria.getPageNumber() : 0;
        Integer pageSize = pagingCriteria.getPageSize() != null && pagingCriteria.getPageSize() > 0 ? pagingCriteria.getPageSize() : 20;
        SortDirection sortDirection = pagingCriteria.getSortDirection() != null
            ? pagingCriteria.getSortDirection()
            : SortDirection.ASC;
        String sortColumn = pagingCriteria.getSortColumn() != null
            ? pagingCriteria.getSortColumn().toString().toLowerCase() // or map to correct field name
            : "title";

        Pageable pageable = PageRequest.of(
            pageNumber,
            pageSize,
            Sort.by(
                Sort.Direction.fromString(sortDirection.toString()),
                sortColumn
            )
        );

        Specification<MovieEntity> spec = (root, query, builder) -> null;
        String title = movieQueryParams.getTitle();
        if (title != null && !title.isEmpty()) {
            spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }

        
        Page<MovieEntity> page = movieRepository.findAll(spec, pageable);

        MoviePageDto pageDto = new MoviePageDto();
        pageDto.setContent(page.getContent().stream().map(movieMapper::toDto).toList());
        pageDto.setEmpty(page.getContent().isEmpty());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setFirst(page.isFirst());
        pageDto.setLast(page.isLast());
        pageDto.setNumber(page.getNumber());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setSize(page.getSize());

        de.dlh.lht.ti.model.Sort sortDto = new de.dlh.lht.ti.model.Sort();
        sortDto.setIsSorted(true);
        sortDto.setSortDirection(sortDirection);
        sortDto.setSortField(sortColumn);

        pageDto.setSort(sortDto);

        return pageDto;
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
