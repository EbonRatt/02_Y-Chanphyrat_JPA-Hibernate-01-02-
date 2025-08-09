package com.hrd.jpahibernate.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Name Product Must Be Not Empty")
    @Size(max = 150)
    @Schema(
            defaultValue = "name"
    )
    private String name;

    @NotNull(message = "Price must be input")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be >= 0.00")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 0.00")
    private BigDecimal price;

    @NotNull(message = "Quantity must be input")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 9999, message = "Quantity must be at most 9999")
    @Schema(
            defaultValue = "1"
    )
    private Integer quantity;

}
