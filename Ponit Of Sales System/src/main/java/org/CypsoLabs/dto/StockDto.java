package org.CypsoLabs.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.CypsoLabs.entity.Cart;
import org.CypsoLabs.entity.Product;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class StockDto {
    @Valid
    private Long id;
    @NotBlank(message = "colour is null")
    private  String color;
    @NotBlank(message = "size is null")
    private String size;
    @NotBlank(message = "price is null")
    private Double price;
    @NotBlank(message = "Quantity is null")
    private int qty;
    private Product product;

}
