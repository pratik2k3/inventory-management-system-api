package com.inventory.service;

import java.util.List;

import com.inventory.Controller.Product;

public interface ProductService {
	Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    // Inventory management
    Product increaseStock(Long id, int quantity);

    Product decreaseStock(Long id, int quantity);

    // Bonus feature
    List<Product> getLowStockProducts();
		
}
