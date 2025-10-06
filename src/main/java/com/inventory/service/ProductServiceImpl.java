package com.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inventory.Entity.Product;
import com.inventory.Exception.ProductServiceException;
import com.inventory.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

  
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getStockQuantity() < 0) {
            throw new ProductServiceException(HttpStatus.BAD_REQUEST, "Stock quantity cannot be negative");
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductServiceException(HttpStatus.NOT_FOUND, "No products available in inventory");
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));

        if (product.getStockQuantity() < 0) {
            throw new ProductServiceException(HttpStatus.BAD_REQUEST, "Stock quantity cannot be negative");
        }

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setStockQuantity(product.getStockQuantity());
        existing.setLowStockThreshold(product.getLowStockThreshold());
        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductServiceException(HttpStatus.NOT_FOUND, "Cannot delete — product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product increaseStock(Long id, int quantity) {
        Product product = getProductById(id);
        if (quantity <= 0) {
            throw new ProductServiceException(HttpStatus.BAD_REQUEST, "Increase quantity must be greater than zero");
        }
        product.setStockQuantity(product.getStockQuantity() + quantity);
        return productRepository.save(product);
    }

    @Override
    public Product decreaseStock(Long id, int quantity) {
        Product product = getProductById(id);
        if (quantity <= 0) {
            throw new ProductServiceException(HttpStatus.BAD_REQUEST, "Decrease quantity must be greater than zero");
        }

        if (product.getStockQuantity() < quantity) {
            throw new ProductServiceException(HttpStatus.CONFLICT, "Insufficient stock! Available: "
                    + product.getStockQuantity() + ", Requested: " + quantity);
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = productRepository.findByStockQuantityLessThan(5);
        if (lowStockProducts.isEmpty()) {
            throw new ProductServiceException(HttpStatus.NOT_FOUND, "No low-stock products found");
        }
        return lowStockProducts;
    }
}
