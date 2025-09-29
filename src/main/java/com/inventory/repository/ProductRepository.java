package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	 List<Product> findByStockQuantityLessThan(int threshold);
	

}
