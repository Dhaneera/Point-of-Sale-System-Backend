package org.CypsoLabs.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdersDto {

    @Valid
    private Long id;

    @NotBlank(message = "Address is null")
    private String address;

    @NotBlank(message = "Contact is null")
    @Size(min = 10, message = "Contact missing digits")
    private String phone;

    private Double tot;


}
