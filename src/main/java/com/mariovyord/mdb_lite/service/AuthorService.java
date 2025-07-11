package com.mariovyord.mdb_lite.service;

import de.dlh.lht.ti.model.AuthorDto;
import de.dlh.lht.ti.model.AuthorPageDto;
import de.dlh.lht.ti.model.AuthorPagingCriteria;
import de.dlh.lht.ti.model.AuthorQueryParams;

public interface AuthorService {
    AuthorPageDto getAuthors(AuthorQueryParams queryParams, AuthorPagingCriteria pagingCriteria);
    AuthorDto createAuthor(AuthorDto authorDto);
}
