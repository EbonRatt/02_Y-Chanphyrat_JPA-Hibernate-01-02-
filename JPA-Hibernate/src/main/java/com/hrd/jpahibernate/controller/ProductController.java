package com.hrd.jpahibernate.controller;

import com.hrd.jpahibernate.model.reponse.ItemResponse;
import com.hrd.jpahibernate.model.reponse.ProductResponse;
import com.hrd.jpahibernate.model.request.ProductRequest;
import com.hrd.jpahibernate.service.ProductService;
import com.hrd.jpahibernate.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<ItemResponse>> getAllProducts(@RequestParam Integer page, @RequestParam Integer size){

        return ResponseEntity.ok().body(
                ApiResponse.<ItemResponse>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getAllProducts(page,size))
                        .build());
    }

    @GetMapping("/low-stack")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductLowStock(@RequestParam Integer quantity){
        return ResponseEntity.ok().body(
                ApiResponse.<List<ProductResponse>>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getProductLowStock(quantity))
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProductByName(@RequestParam String name){
        return ResponseEntity.ok().body(
                ApiResponse.<List<ProductResponse>>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.searchProductByName(name))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getProductById(id))
                        .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Product created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(productService.addProduct(productRequest))
                        .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductRequest productRequest){
        return  ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Product created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(productService.updateProduct(productRequest,id))
                        .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.deleteById(id))
                        .build());
    }

}
