package com.mariovyord.mdb_lite.util;

import de.dlh.lht.ti.model.SortDirection;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PagingUtil {

  private static final Integer DEFAULT_PAGE_SIZE = 20;
  private static final Integer DEFAULT_PAGE = 0;
  private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

  public static Pageable createPageable(
      Integer pageNumber,
      Integer pageSize,
      SortDirection sortDirection,
      String sortColumn,
      String defaultColumn) {

    Sort.Direction direction =
        sortDirection != null
            ? Sort.Direction.fromString(sortDirection.getValue())
            : DEFAULT_SORT_DIRECTION;

    return PageRequest.of(
        pageNumber != null && pageNumber > 0 ? pageNumber : DEFAULT_PAGE,
        pageSize != null && pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE,
        Sort.by(direction, sortColumn));
  }
}
