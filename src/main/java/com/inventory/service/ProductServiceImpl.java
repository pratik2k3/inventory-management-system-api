package com.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.Entity.Product;
import com.inventory.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	  private final ProductRepository productRepository;

	    // Constructor injection (good practice)
	    public ProductServiceImpl(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }
	
	
	
	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
                .orElse(null); 
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product updated = existing.get();
            updated.setName(product.getName());
            updated.setDescription(product.getDescription());
            updated.setStockQuantity(product.getStockQuantity());
            updated.setLowStockThreshold(product.getLowStockThreshold());
            return productRepository.save(updated);
        }
        return null; 
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product increaseStock(Long id, int quantity) {
		Product product = getProductById(id);
        if (product != null) {
            product.setStockQuantity(product.getStockQuantity() + quantity);
            return productRepository.save(product);
        }
        return null;
	}

	@Override
	public Product decreaseStock(Long id, int quantity) {
		 Product product = getProductById(id);
	        if (product != null) {
	            if (product.getStockQuantity() < quantity) {
	                throw new RuntimeException("Insufficient stock!"); 
	            }
	            product.setStockQuantity(product.getStockQuantity() - quantity);
	            return productRepository.save(product);
	        }
	        return null;
	}

	@Override
	public List<Product> getLowStockProducts() {
		
		return productRepository.findByStockQuantityLessThan(5);
	}

}
