package org.CypsoLabs.repository;

import org.CypsoLabs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> findByCategoryName(String categoryName);
    Product getByName(String name);
}
