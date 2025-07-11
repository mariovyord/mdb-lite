package com.mariovyord.mdb_lite.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mariovyord.mdb_lite.entity.BookEntity;
import com.mariovyord.mdb_lite.util.Constants;

import de.dlh.lht.ti.model.BookDto;

@Mapper(
    componentModel = Constants.SPRING_MAPPING_MODEL,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface BookMapper {
  BookDto toDto(BookEntity bookEntity);
  BookEntity toEntity(BookDto bookDto);
}