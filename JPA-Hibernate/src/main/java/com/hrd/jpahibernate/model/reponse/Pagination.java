package com.hrd.jpahibernate.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
    private Long totalElements;
    private Integer currentPage;
    private Integer pageSize;
    private Long totalPages;
}

