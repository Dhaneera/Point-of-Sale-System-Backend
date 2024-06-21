package org.CypsoLabs.service;

import org.CypsoLabs.dto.CartDto;
import org.CypsoLabs.entity.Cart;

import java.util.List;

public interface CartService {

    List<CartDto> getAllCartDetails();

    CartDto updateCart(Long id, CartDto cartDto);

    CartDto getCartById(long id);

    boolean addCart(CartDto cartDto);

    boolean  deleteCartById(Long id);
}
