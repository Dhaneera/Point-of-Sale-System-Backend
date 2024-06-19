package org.CypsoLabs.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.CypsoLabs.entity.Category;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class ProductDto {
    @Valid
    private Long id;
    @NotBlank(message = "Product Name cannot be null")
    private String name;
    @NotBlank(message = "Description cannot be null")
    private  String desc;
    private double price;
    private Category category;

}
