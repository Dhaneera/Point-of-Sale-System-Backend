package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.CypsoLabs.config.Resource.ResourceNotFoundException;
import org.CypsoLabs.dto.CartDto;
import org.CypsoLabs.entity.Cart;
import org.CypsoLabs.entity.Stock;
import org.CypsoLabs.repository.CartRepository;
import org.CypsoLabs.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ObjectMapper objectMapper;


    @Autowired
     CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartDto> getAllCartDetails() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        Iterator<Cart> cartIterator = cartIterable.iterator();
        List<CartDto> cartDtos = new ArrayList<>();
        while(cartIterator.hasNext()){
            Cart cart = cartIterator.next();
            CartDto cartDto=objectMapper.convertValue(cart , CartDto.class);
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }

    @Override
    public CartDto updateCart(Long id, CartDto cartDto) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            cart.setQty(cartDto.getQty());
            cart.setStock(cartDto.getStock());
            cart.setTotal(cartDto.getProductTot());

            Cart updatedCart = cartRepository.save(cart);
            return convertCartToDto(updatedCart);
        } else {
            throw new ResourceNotFoundException("Cart not found for id: " + id);
        }

    }

    private CartDto convertCartToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .stock(cart.getStock())
                .qty(cart.getQty())
                .productTot(cart.getTotal())
                .build();
    }

    @Override
    public CartDto getCartById(long id) {
        Optional<Cart> cart=cartRepository.findById(id);
        if (cart.isPresent()){
            Cart cartGot=cart.get();
            CartDto cartDto=objectMapper.convertValue(cartGot,CartDto.class);
            cartDto.setStock(Stock.builder().id(cartGot.getId()).build());
            cartDto.setStock(cartGot.getStock());
            return cartDto;
        }
        return null;
    }

    @Override
    public boolean addCart(CartDto cartDto) {
        Cart cart=objectMapper.convertValue(cartDto, Cart.class);
        cart.setStock(Stock.builder().id(cartDto.getStock().getId()).build());
        Cart saved = cartRepository.save(cart);
        return saved.getId()!=null;
    }

    @Override
    public boolean deleteCartById(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }else{
            throw new ResourceNotFoundException("Cart info not available for this id to delete: "+id);
        }
    }

}
