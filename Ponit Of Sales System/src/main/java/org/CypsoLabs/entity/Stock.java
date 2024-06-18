package org.CypsoLabs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product id")
    private Product product;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Cart> cart;

    @Column(name = "Color",nullable = false)
    private  String color;

    @Column(name = "size",nullable = false)
    private String size;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "quantity",nullable = false)
    private int qty;


}
