package com.mariovyord.mdb_lite.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.entity.BookEntity;
import com.mariovyord.mdb_lite.util.Constants;

import de.dlh.lht.ti.model.BookCreateDto;
import de.dlh.lht.ti.model.BookDto;

@Mapper(
    componentModel = Constants.SPRING_MAPPING_MODEL,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface BookMapper {
  BookDto toDto(BookEntity bookEntity);

  @Mapping(target = "authors", source = "authors")
  BookEntity toEntity(BookCreateDto bookDto, List<AuthorEntity> authors);
}