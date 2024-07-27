package com.carbigdata.ms.domain.pagination;

import java.util.List;

public record PaginationResponse<T> (
    int page, 
    Long total,
    int limit,
    List<T> data){
}
