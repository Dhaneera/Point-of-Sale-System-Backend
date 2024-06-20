package org.CypsoLabs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "productId")
    private Long id;


    private String name;
    private  double price;
    @Column(name = "total_description",length = 999999)

    private String desc;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnore
    private  Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Stock> stocks;

}
