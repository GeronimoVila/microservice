package com.ucc.product.repository;

import com.ucc.product.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // ********************* QUERY **********************************
    //OBTENER LOS PRODUCTOS ORDENADOS POR PRECIO DESCENDIENTE
    @Query(value = "SELECT * FROM product ORDER BY price DESC", nativeQuery = true)
    List<Product> findAllOrderByPriceDesc();
}
