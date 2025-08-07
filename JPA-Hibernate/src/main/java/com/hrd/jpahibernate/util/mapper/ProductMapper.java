package com.hrd.jpahibernate.util.mapper;

import com.hrd.jpahibernate.model.entity.Product;
import com.hrd.jpahibernate.model.reponse.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductsResponse(List<Product> product);

}
