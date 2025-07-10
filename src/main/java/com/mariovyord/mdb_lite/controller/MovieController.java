package com.mariovyord.mdb_lite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mariovyord.mdb_lite.service.MovieService;

import de.dlh.lht.ti.api.MoviesApi;
import de.dlh.lht.ti.model.MovieDto;
import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class  MovieController implements MoviesApi {

    private final MovieService movieService;

    @Override
    public ResponseEntity<MoviePageDto> getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        MoviePageDto moviePageDto = movieService.getMovies(movieQueryParams, pagingCriteria);
        return ResponseEntity.ok(moviePageDto);
    }

    @Override
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        MovieDto createdMovie = movieService.createMovie(movieDto);
        return ResponseEntity.status(201).body(createdMovie);
    }
}