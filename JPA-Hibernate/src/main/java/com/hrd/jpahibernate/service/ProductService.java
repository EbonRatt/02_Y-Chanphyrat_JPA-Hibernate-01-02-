package com.hrd.jpahibernate.service;

import com.hrd.jpahibernate.model.reponse.ItemResponse;
import com.hrd.jpahibernate.model.reponse.ProductResponse;
import com.hrd.jpahibernate.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    ItemResponse getAllProducts(int pageNumber, int pageSize);
    ProductResponse getProductById(Long id);
    ProductResponse deleteById(Long id);
    List<ProductResponse> searchProductByName(String name);
    List<ProductResponse> getProductLowStock(Integer qty);
    ProductResponse updateProduct(ProductRequest product,Long id);
}
