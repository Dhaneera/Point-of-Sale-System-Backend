package org.CypsoLabs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product id")
    private Long id;

    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "Price",nullable = false)
    private double price;

    @Column(name = "Description",nullable = false)
    private String desc;

    @ManyToOne
    @JoinColumn(name = "category id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;



}
