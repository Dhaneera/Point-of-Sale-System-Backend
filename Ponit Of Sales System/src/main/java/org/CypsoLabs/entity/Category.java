package org.CypsoLabs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "categoryId")
    private  Long id;

    @Column(name = "category name",nullable = false)
    private  String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product>  products;
}
