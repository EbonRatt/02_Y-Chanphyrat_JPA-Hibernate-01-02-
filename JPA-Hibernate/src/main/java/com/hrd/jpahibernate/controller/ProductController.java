package com.hrd.jpahibernate.controller;

import com.hrd.jpahibernate.model.reponse.ItemResponse;
import com.hrd.jpahibernate.model.reponse.ProductResponse;
import com.hrd.jpahibernate.model.request.ProductRequest;
import com.hrd.jpahibernate.service.ProductService;
import com.hrd.jpahibernate.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Operation(summary = "Get all products (Paginated)", description = "Returns a paginated list of all products. Accepts page and size as query parameters.")
    public ResponseEntity<ApiResponse<ItemResponse>> getAllProducts(@RequestParam @Min(value = 1) @Schema(defaultValue = "1") Integer page, @Min(value = 1) @Schema(defaultValue = "10") @RequestParam Integer size){

        return ResponseEntity.ok().body(
                ApiResponse.<ItemResponse>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getAllProducts(page,size))
                        .build());
    }

    @GetMapping("/low-stack")
    @Operation(summary = "Get low stock products", description = "Returns a list of products with quantity less than the specified threshold.")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductLowStock(@RequestParam Integer quantity){
        return ResponseEntity.ok().body(
                ApiResponse.<List<ProductResponse>>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getProductLowStock(quantity))
                        .build());
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name", description = "Returns a list of products that contain the given name (case-insensitive partial match).")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProductByName(@RequestParam String name){
        return ResponseEntity.ok().body(
                ApiResponse.<List<ProductResponse>>builder()
                        .message("Products fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.searchProductByName(name))
                        .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetches a product using its unique ID. Returns 404 if not found.")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Product fetched successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.getProductById(id))
                        .build());
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Accepts a product request payload and creates a new product. Returns the created product.")
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Product created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(productService.addProduct(productRequest))
                        .build());
    }

    @PutMapping("{id}")
    @Operation(summary = "Update product by ID", description = "Updates an existing product with the given ID using the request body. Returns the updated product.")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest){
        return  ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Product updated successfully")
                        .status(HttpStatus.CREATED)
                        .payload(productService.updateProduct(productRequest,id))
                        .build());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete product by ID", description = "Deletes a product by its ID. Returns HTTP 200 if the product is successfully deleted.")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok().body(
                ApiResponse.<ProductResponse>builder()
                        .message("Products deleted successfully")
                        .status(HttpStatus.OK)
                        .payload(productService.deleteById(id))
                        .build());
    }

}
