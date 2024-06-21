package org.CypsoLabs.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.CypsoLabs.entity.Cart;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class OrdersDto {

    @Valid
    private Long id;
    private String customerId ;
    private Cart cart;
    @NotBlank(message = "address is null")
    private String address;
    @NotBlank(message = "contact is null")
    @Size(min = 10,message = "contact missing digits")
    private String phone;
    private Double tot;
}
