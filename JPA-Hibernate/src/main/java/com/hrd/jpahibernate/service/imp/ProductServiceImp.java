package com.hrd.jpahibernate.service.imp;

import com.hrd.jpahibernate.model.entity.Product;
import com.hrd.jpahibernate.model.reponse.ItemResponse;
import com.hrd.jpahibernate.model.reponse.Pagination;
import com.hrd.jpahibernate.model.reponse.ProductResponse;
import com.hrd.jpahibernate.model.request.ProductRequest;
import com.hrd.jpahibernate.repository.CRUDRepo;
import com.hrd.jpahibernate.service.ProductService;
import com.hrd.jpahibernate.util.mapper.ItemMapper;
import com.hrd.jpahibernate.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp extends CRUDRepo implements ProductService{


    private final ProductMapper productMapper;
    private final ItemMapper itemMapper;

    @Override
    @Transactional
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        insertItem(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public ItemResponse getAllProducts(int pageNumber, int pageSize) {
        List<Product> productList = getAllItems(pageNumber,pageSize);
        Pagination pagination = getPagination(pageNumber,pageSize);
        return itemMapper.toItemResponse(productList,pagination);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productMapper.toProductResponse(findItem(id));
    }

    @Override
    @Transactional
    public ProductResponse deleteById(Long id) {
        return productMapper.toProductResponse(deleteItem(id));
    }

    @Override
    public List<ProductResponse> searchProductByName(String name) {
        return productMapper.toProductsResponse(getItemByName(name));
    }

    @Override
    public List<ProductResponse> getProductLowStock(Integer qty) {
        return productMapper.toProductsResponse(getItemLowStock(qty));
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest product, Long id) {
        return productMapper.toProductResponse(updateItem(product,id));
    }
}
