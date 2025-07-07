package com.mariovyord.mdb_lite.service.impl;

import org.springframework.stereotype.Service;

import com.mariovyord.mdb_lite.service.MovieService;

import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Override
    public MoviePageDto getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria) {
        return null;
    }
}
