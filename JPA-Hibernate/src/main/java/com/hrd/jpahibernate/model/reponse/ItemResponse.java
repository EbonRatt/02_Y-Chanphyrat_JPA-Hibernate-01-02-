package com.hrd.jpahibernate.model.reponse;

import com.hrd.jpahibernate.model.entity.Product;

import java.util.List;

public record ItemResponse(
        List<Product> products,
        Pagination pagination
) {
}
