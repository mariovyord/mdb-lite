package com.mariovyord.mdb_lite.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.util.Constants;

import de.dlh.lht.ti.model.AuthorDto;

@Mapper(
    componentModel = Constants.SPRING_MAPPING_MODEL,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface AuthorMapper {
  AuthorDto toDto(AuthorEntity authorEntity);
  AuthorEntity toEntity(AuthorDto authorDto);
}