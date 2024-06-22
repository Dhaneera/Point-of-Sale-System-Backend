package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.CartDto;
import org.CypsoLabs.dto.CategoryDto;
import org.CypsoLabs.dto.CustomerDto;
import org.CypsoLabs.entity.Cart;
import org.CypsoLabs.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart/api")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<String>addCart(@Valid @RequestBody CartDto cartDto){

        boolean isSaved= cartService.addCart(cartDto);
        if (isSaved){
            return ResponseEntity.status(HttpStatus.CREATED).body("Cart created successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Cart.");
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CartDto>getCartById(@PathVariable Long id){
        CartDto cartDto = cartService.getCartById(id);
        if (cartDto !=null)return ResponseEntity.ok(cartDto);
        else return ResponseEntity.notFound().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CartDto> updateCartById(@PathVariable Long id, @RequestBody CartDto cartDto){
        CartDto updateCartDto = cartService.updateCart(id, cartDto);
        if (updateCartDto!=null)return ResponseEntity.ok(updateCartDto);
        else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String>removeCart(@PathVariable Long id){
        boolean deleted = cartService.deleteCartById(id);
        if (deleted)return ResponseEntity.ok("Product deleted");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred when delete Product");
    }
}
