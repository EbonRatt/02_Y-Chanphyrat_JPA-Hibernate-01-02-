package com.hrd.jpahibernate.model.reponse;

import java.math.BigDecimal;

public record ProductResponse(
        Long id, String name, BigDecimal price,Integer quantity
) {
}
