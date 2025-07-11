package com.mariovyord.mdb_lite.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mariovyord.mdb_lite.entity.AuthorEntity;
import com.mariovyord.mdb_lite.entity.BookEntity;
import com.mariovyord.mdb_lite.util.Constants;

import de.dlh.lht.ti.model.Sort;
import de.dlh.lht.ti.model.SortDirection;
import de.dlh.lht.ti.model.AuthorPageDto;
import de.dlh.lht.ti.model.BookPageDto;

@Mapper(
    componentModel = Constants.SPRING_MAPPING_MODEL,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    uses = {
      BookMapper.class,
    }
)
public interface PageMapper {
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "numberOfElements", source = "page.numberOfElements")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "empty", source = "page.empty")
    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "sort", source = "page.pageable", qualifiedByName = "mapSort")
    BookPageDto toBookPage(Page<BookEntity> page);

    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "numberOfElements", source = "page.numberOfElements")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "empty", source = "page.empty")
    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "sort", source = "page.pageable", qualifiedByName = "mapSort")
    AuthorPageDto toAuthorPage(Page<AuthorEntity> page);

    @Named("mapSort")
    default Sort mapSort(Pageable pageable) {
        Sort sortDto = new Sort();
        if (pageable.getSort().isSorted()) {
            sortDto.setIsSorted(true);
            org.springframework.data.domain.Sort sort = pageable.getSort();
            org.springframework.data.domain.Sort.Order order = sort.iterator().next();
            sortDto.setSortField(order.getProperty());
            sortDto.setSortDirection(
                order.getDirection().isAscending()
                    ? SortDirection.ASC
                    : SortDirection.DESC);
        } else {
            sortDto.setIsSorted(false);
        }

        return sortDto;
    }
}