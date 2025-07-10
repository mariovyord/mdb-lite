package com.mariovyord.mdb_lite.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.entity.MovieEntity;
import com.mariovyord.mdb_lite.mapper.MovieMapper;
import com.mariovyord.mdb_lite.repository.MovieRepository;
import com.mariovyord.mdb_lite.service.MovieService;

import de.dlh.lht.ti.model.MovieDto;
import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;
import lombok.AllArgsConstructor;
import lombok.val;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MoviePageDto getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        List<MovieEntity> movies = movieRepository.findAll(); 
        
        return null;
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
