package com.mariovyord.mdb_lite.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mariovyord.mdb_lite.entity.MovieEntity;
import com.mariovyord.mdb_lite.util.Constants;

import de.dlh.lht.ti.model.MovieDto;

@Mapper(
    componentModel = Constants.SPRING_MAPPING_MODEL,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface MovieMapper {
  MovieDto toDto(MovieEntity movieEntity);
  MovieEntity toEntity(MovieDto movieDto);
}