package org.CypsoLabs.repository;


import org.CypsoLabs.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
//    List<Stock> findBySizeAndColorAndProductId(String size, String color, Long productId);


    List<Stock> findBySizeAndColorAndProductId(String size, String color, Long productId);

//    @Query("SELECT s FROM Stock s WHERE s.size = :size AND s.color = :color AND s.product.id = :productId")
//    List<Stock> findBySizeAndColorAndProductId(@Param("size") String size, @Param("color") String color, @Param("productId") Long productId);

}
