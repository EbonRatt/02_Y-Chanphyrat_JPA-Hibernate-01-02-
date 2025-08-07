package com.hrd.jpahibernate.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "Can't Be Null")
    @Min(value = 150)
    private String name;

    @NotBlank(message = "Price Must Be Input")
    @NotNull(message = "Can't Be Null")
    @Positive(message = "Positive Number Only")
    private BigDecimal price;

    @NotBlank(message = "Quantity Must Be Input")
    @NotNull(message = "Can't Be Null")
    private Integer quantity;

}
