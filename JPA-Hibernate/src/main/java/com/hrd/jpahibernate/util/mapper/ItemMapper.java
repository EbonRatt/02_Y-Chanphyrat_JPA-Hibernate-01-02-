package com.hrd.jpahibernate.util.mapper;

import com.hrd.jpahibernate.model.entity.Product;
import com.hrd.jpahibernate.model.reponse.ItemResponse;
import com.hrd.jpahibernate.model.reponse.Pagination;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface ItemMapper {
    ItemResponse toItemResponse(List<Product> products, Pagination pagination);
}
