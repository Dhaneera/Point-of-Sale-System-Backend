package org.CypsoLabs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customerId")
    private  Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private  String address;

    @Column(name = "contact number",nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Orders> orders;

}
