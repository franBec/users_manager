package dev.pollito.users_manager.util;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {
  private PageUtil() {}

  public static @NotNull Pageable createPageable(int page, int size, @NotNull List<String> sort) {
    Sort combinedSort = Sort.unsorted();
    boolean hasIdSort = false;

    for (String sortField : sort) {
      String[] sortParams = sortField.split(":");
      Sort.Direction direction =
          (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1]))
              ? Sort.Direction.DESC
              : Sort.Direction.ASC;

      if ("id".equalsIgnoreCase(sortParams[0])) {
        hasIdSort = true;
      }

      combinedSort = combinedSort.and(Sort.by(direction, sortParams[0]));
    }

    if (!hasIdSort) {
      combinedSort = combinedSort.and(Sort.by(Sort.Direction.ASC, "id"));
    }

    return PageRequest.of(page, size, combinedSort);
  }
}
