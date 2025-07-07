package com.mariovyord.mdb_lite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import de.dlh.lht.ti.api.MoviesApi;
import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class  MovieController implements MoviesApi {

    @Override
    public ResponseEntity<MoviePageDto> getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        return ResponseEntity.ok(null);
    }
    
}