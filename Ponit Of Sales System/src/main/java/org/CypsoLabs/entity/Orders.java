package org.CypsoLabs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart id")
    private  Cart cart;

    @Column(nullable = false)
    private String address;

    @Column(name ="contact number" , nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "customer id")
    private String customer;

    @Column(name = "Order Total" , nullable = false)
    private  Long total;


}
