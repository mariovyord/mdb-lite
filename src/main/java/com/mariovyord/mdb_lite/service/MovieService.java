package com.mariovyord.mdb_lite.service;

import de.dlh.lht.ti.model.MoviePageDto;
import de.dlh.lht.ti.model.MoviePagingCriteria;
import de.dlh.lht.ti.model.MovieQueryParams;

public interface MovieService {
    MoviePageDto getMovies(MovieQueryParams movieQueryParams, MoviePagingCriteria pagingCriteria);
}
